package motor;





import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;


import control.Controles;
import control.Developer;
import graficos.Pantalla;
import tropas.Tanque;



public class Juego  extends JFrame implements Runnable{
 
	/**
	* 
     	*/
    	private static final long serialVersionUID = 2997131868080903015L;
    	
    	
    	
	private final static int ANCHO_VENTANA = 1024;
	private final static int ALTO_VENTANA = 1024;
	
	
	
	private final static int CENTRO_ANCHO_VENTANA =(ANCHO_VENTANA/2)*-1;
	private final static int CENTRO_ALTO_VENTANA =(ALTO_VENTANA/2)*-1;
	
	private final static int ANCHO_TILE = 64;
	private final static int ALTO_TILE= 32;
	
	private final static int[] VALORES_SIZES = {
											ANCHO_VENTANA, 
											ALTO_VENTANA, 
											ANCHO_TILE, 
											ALTO_TILE, 
											CENTRO_ANCHO_VENTANA, 
											CENTRO_ALTO_VENTANA};
	
	private static boolean juegoActivo = false;

	private static Controles controles;
	private static Developer developer;
	
	
	private static Niveles juegoNivel;
	
	private static Thread thread;
	private static Pantalla pantalla;
	
	private static String  CONTADOR_APS = "";
	private static String  CONTADOR_FPS = "";
	
	private static int aps=0;
	private static int fps = 0;
	
	private static int x = CENTRO_ANCHO_VENTANA;
	private static int y = CENTRO_ALTO_VENTANA;
	
	private static int batalla = 1;


	
	
	private Juego() {
	    developer = new Developer();
		controles = new Controles();
		
		juegoNivel = new Niveles(VALORES_SIZES);
		juegoNivel.iniciarNivel(batalla);
		
		
		addKeyListener(controles);
		
		pantalla = new Pantalla(VALORES_SIZES,juegoNivel.getHojaNivel());
		

		
		pantalla.actores(juegoNivel, controles, developer);
		
		controles.initActores(juegoNivel.getPlayers(), juegoNivel.getHojaNivel());
		
		addMouseListener(controles);
		
		setSize(VALORES_SIZES[0],VALORES_SIZES[1]);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		setFocusable(true);
		
		add(pantalla);
		
		setVisible(true);
		
		
	}
	
	public static void main(String[] args)  {
		
	    
	    
		Juego juego = new Juego();
		juego.iniciar();

	}
	
	
	
	
	
	private synchronized void iniciar(){
		
		juegoActivo = true;
		

		thread = new Thread(this, "Graficos");
		thread.start();
	}
	

	
	private void actualizar(){		
		
		controles.actualizar(pantalla.getScrollX(),pantalla.getScrollY());
		controles.posicionRaton(pantalla);
		
		for (int xt=0;xt<juegoNivel.getPlayers().length;xt++){
			juegoNivel.getPlayers()[xt].selecciona();
		}
		
		
		
		if(controles.centrar){
			x = CENTRO_ANCHO_VENTANA;
			y = CENTRO_ALTO_VENTANA;
		}
		
		
		if(controles.arriba && x>((ALTO_VENTANA*-1)/2)){
			x-=controles.getVelocidad();
		    

		}
		
		if(controles.abajo && x<(ALTO_VENTANA/4)){
			x+=controles.getVelocidad();
		}
		
		if(controles.derecha && y<(ANCHO_VENTANA)/2){
			y+=controles.getVelocidad();

		}
		
		if(controles.izquierda && y>((ANCHO_VENTANA*-1))){
			y-=controles.getVelocidad();

		}
		

		
		
		
	
		
		if(controles.salir){
			System.exit(0);
		}
		
		if(controles.dev){
		    	
			developer.setDevActivo();
			
		}
		
		pantalla.actualizar(y, x);
		aps ++;
	}
	
	private void mostrar(){
		
		repaint();
		
		fps ++;
	}
	
	
	
	@Override
	public void run() {
		final int NS_POR_SEGUNDO = 1000000000;
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
		
		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime(); 
		
		double  timepoTranscurrido;
		double delta = 0;
		
		
		
		while(juegoActivo){
			final long inicioBucle = System.nanoTime();
			
			timepoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion =  inicioBucle;
			
			delta += timepoTranscurrido / NS_POR_ACTUALIZACION;
			
			while (delta >= 1) {
				actualizar();
				delta--;
				
			}
			mostrar();
			
			if (System.nanoTime()-referenciaContador > NS_POR_SEGUNDO){
				setTitle("WWII Juego 0.1"+" | APS: "+CONTADOR_APS+" | "+CONTADOR_FPS);
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
			
			
		}
		
	}



}
