package motor;

import control.Controles;
import graficos.Tiles;

public class Juego  implements Runnable{

	private static boolean juegoActivo = false;

	private static Controles controles;
	private static Tiles tiles;
	
	private static Thread thread;
	
	private static String  CONTADOR_APS = "";
	private static String  CONTADOR_FPS = "";
	
	private static int aps=0;
	private static int fps = 0;
	
	
	private Juego(){
		controles = new Controles();
		
	}
	
	public static void main(String[] args) {
		
		
		Juego juego = new Juego();
		juego.iniciar();

	}
	
	
	
	
	
	private synchronized void iniciar(){
		juegoActivo = true;
		
		tiles = new Tiles();
		tiles.iniciarMapeo();
		tiles.iniciarCoor();
		tiles.montarNivel();
		
		
		thread = new Thread(this, "Graficos");
		thread.start();
	}
	

	
	private void actualizar(){		
		
		if(controles.salir){
			System.exit(0);
		}
		aps ++;
	}
	
	private void mostrar(){
		
		
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
				CONTADOR_APS = "APS: " + aps;
				CONTADOR_FPS = "FPS: " + fps;
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
			
			
		}
		
	}

}
