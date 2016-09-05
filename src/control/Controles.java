package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public final class Controles implements KeyListener, MouseMotionListener{

	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];
	
	public boolean salir;
	
	
	
	// Parametros de objetos 
	private int x;
	private int y;
	private int ancho;
	private int alto;
	
	// parametros del raton
	private boolean arrastrando = false;
	private int anteriorRatonX;
	private int anteriorRatonY;
	private int actualRatonX;
	private int actualRatonY;
	



	public int getActualRatonX() {
		return actualRatonX;
	}

	public int getActualRatonY() {
		return actualRatonY;
	}

	public void actualizar(int x, int y, int ancho, int alto){

		
		salir = teclas[KeyEvent.VK_ESCAPE];
		
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		
		
		
	}

	
	private boolean estDentro(MouseEvent e){
		

		
		if ( (e.getX() > x)  && (e.getX() < (x + ancho)) && 
			 (e.getY() > y)  && (e.getY() < (y + alto))){ 
			System.out.println("ESTOY");
				 return true;
			 } else {
				 return false;
			 }	 
			 
	}
	
	
	
	
	public int getRX() {
		return x;
	}

	public int getRY() {
		return y;
	}

	public int getXRaton() {
		return anteriorRatonX;
	}

	public int getYRaton() {
		return anteriorRatonY;
	}



	public boolean isArrastrando() {
		return arrastrando;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!arrastrando){
			if(estDentro(e)){
				anteriorRatonX = e.getX();
				anteriorRatonY = e.getY();
				
				arrastrando = true;
			}
		} else {
			x = (x + e.getX()) - anteriorRatonX;
			y = (y + e.getY()) - anteriorRatonY;
			
			anteriorRatonX = e.getX();
			anteriorRatonY = e.getY();
		}
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		actualRatonX = e.getX();
		actualRatonY = e.getY();
		arrastrando = false;
		
	}

	

}