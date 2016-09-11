package motor;



import java.awt.Color;

import javax.swing.JFrame;

import control.Controles;
import graficos.Pantalla;
import graficos.TileData;

public class Juego  extends JFrame implements Runnable{

	/**
	* 
     	*/
    	private static final long serialVersionUID = 2997131868080903015L;
    	
	private final static int ANCHO_VENTANA = 1024;
	private final static int ALTO_VENTANA = 1024;
	
	private final static int ANCHO_TILE = 66;
	private final static int ALTO_TILE= 32;
	
	private static boolean juegoActivo = false;

	private static Controles controles;
	private static TileData tiles;
	
	private static Thread thread;
	private static Pantalla pantalla;
	
	private static String  CONTADOR_APS = "";
	private static String  CONTADOR_FPS = "";
	
	private static int aps=0;
	private static int fps = 0;
	
	private static int x = ANCHO_VENTANA/2 ;
	private static int y = ALTO_VENTANA/2;
	
	
	private Juego() {
		controles = new Controles();
		addKeyListener(controles);
		
		pantalla = new Pantalla(ANCHO_VENTANA,ALTO_VENTANA,ANCHO_TILE,ALTO_TILE);
		

		
		
		setSize(ANCHO_VENTANA,ALTO_VENTANA);
		setFocusable(true);
		
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
		
		tiles = new TileData();
		tiles.iniciarMapeo();
		tiles.iniciarCoor();
		tiles.montarNivel();
		//tiles.buscaCelda(2000,1390);
		
		
		
		
		
		thread = new Thread(this, "Graficos");
		thread.start();
	}
	

	
	private void actualizar(){		
		
		controles.actualizar();

		
		if(controles.centrar){
			x = 512;
			y = 512;
		}
		
		
		if(controles.arriba && x>((ALTO_VENTANA*-1)/6)){
			x-=controles.getVelocidad();
		    

		}
		
		if(controles.abajo && x<(ALTO_VENTANA/6)+ALTO_VENTANA){
			x+=controles.getVelocidad();

		}
		
		if(controles.derecha && y<(ANCHO_VENTANA/6)+ANCHO_VENTANA){
			y+=controles.getVelocidad();

		}
		
		if(controles.izquierda && y>((ANCHO_VENTANA*-1)/6)){
			y-=controles.getVelocidad();

		}
		
		if(controles.salir){
			System.exit(0);
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
