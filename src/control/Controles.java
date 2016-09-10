package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public final class Controles implements KeyListener{

	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];
	
	public boolean salir;
	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;
	public boolean centrar;
	
	private boolean centrado = false;
	
	private int velocidad=1;
	
	
	
	// Parametros de objetos 
	private int x=0;
	private int y=0;
	


	public void actualizar(){

		
		salir = teclas[KeyEvent.VK_ESCAPE];
		arriba = teclas[KeyEvent.VK_UP];
		abajo = teclas[KeyEvent.VK_DOWN];
		izquierda = teclas[KeyEvent.VK_LEFT];
		derecha = teclas[KeyEvent.VK_RIGHT];
		centrar = teclas[KeyEvent.VK_C];

		
		
		
	}

	
	public int getXpos() {
		return x;
	}

	public int getYpos() {
		return y;
	}

	public int getVelocidad() {
		return velocidad;
	}
	
	public boolean getCentrando() {
		return centrado;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
		
		if(centrar){
			centrado = true;
			x = 512;
			y = 512;
		}
		
		if(arriba){
			x+=getVelocidad();

		}
		
		if(abajo){
			x-=getVelocidad();

		}
		
		if(derecha){
			y+=getVelocidad();

		}
		
		if(izquierda){
			y-=getVelocidad();

		}
		
		
	}



	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
		System.out.println("R"+e.getKeyCode());
	}

	

	

}