package control;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import graficos.HojaSprites;
import graficos.Pantalla;
import tropas.Tanque;

public final class Controles implements KeyListener,MouseListener{




	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];
	
	public boolean salir;
	public boolean arriba;
	public boolean abajo;
	public boolean izquierda;
	public boolean derecha;
	public boolean centrar;
	public boolean dev;
	
	public boolean centrado = false;
	
	private int velocidad=8;
	
	private int actualRatonX;
	private int actualRatonY;
	
	private int scrollX=0;
	private int scrollY=0;
	
	private Tanque[] tanquesPlay;
	private HojaSprites mapa;
	private int anteriorRatonX;
	private int anteriorRatonY;
	private AccionesGamer accionesGamer;
	
	// Teclas actitud
	public boolean reposo;
	public boolean mantenerPosicion;
	public boolean cubrir;
	public boolean defenderObjetivo;
	public boolean ataque;
	public boolean avancePasivo;
	public boolean avanceActivo;
	public boolean posicionar;
	
	// Teclas developer
	public boolean devMaCo;
	public boolean devMaPe;
	


	public void initActores(Tanque[] play,HojaSprites mapa){
	    
	    tanquesPlay = play;
	    this.mapa = mapa;
	}

	public void actualizar(int Rx, int Ry){

		
		salir = teclas[KeyEvent.VK_ESCAPE];
		arriba = teclas[KeyEvent.VK_UP];
		abajo = teclas[KeyEvent.VK_DOWN];
		izquierda = teclas[KeyEvent.VK_LEFT];
		derecha = teclas[KeyEvent.VK_RIGHT];
		centrar = teclas[KeyEvent.VK_C];
		
		dev = teclas[KeyEvent.VK_D];
		devMaCo = teclas[KeyEvent.VK_M];
		devMaPe = teclas[KeyEvent.VK_P];
		
		
		reposo = teclas[KeyEvent.VK_0];
		mantenerPosicion = teclas[KeyEvent.VK_1];
		cubrir = teclas[KeyEvent.VK_2];
		defenderObjetivo = teclas[KeyEvent.VK_3];
		ataque = teclas[KeyEvent.VK_4];
		avancePasivo = teclas[KeyEvent.VK_5];
		avanceActivo = teclas[KeyEvent.VK_6];
		posicionar = teclas[KeyEvent.VK_7];
		
		this.scrollX = Rx;
		this.scrollY = Ry;
		accionesGamer = new AccionesGamer(tanquesPlay ,scrollX ,scrollY);
		
		

	
		
	}

	public void posicionRaton(Pantalla pantalla){
		
		
		actualRatonX=anteriorRatonX;
		actualRatonY=anteriorRatonY;
		
	}
	
	public void dameCuadro(int valorX, int valorY){
	
	    
	}


	public int getVelocidad() {
		return velocidad;
	}
	public int getActualRatonX() {
	        return actualRatonX;
	    }
	 

	    public int getActualRatonY() {
	        return actualRatonY;
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

	public boolean isCentrado() {
		return centrado;
	}



	
	public void mouseMoved(MouseEvent e) {
	
		
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// Posicion de selector del menu 0:x, 1:y, 2:ancho,3:alto, 4:espacio rotacion, 5:margen superior inicial
		
		
		   if((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
			   // Boton IZQUIERDO
			   accionesGamer.cierraContextoT();
			   accionesGamer.seleccionarTanuqes(e.getX(),e.getY());
			   
			   
		   } else {
			   // Boton DERECHO
			   accionesGamer.menuContextoT();
			   
			   
		   }
			
		anteriorRatonX=e.getX();
		anteriorRatonY=e.getY();
		
		
		
		

	}


	@Override
	public void mousePressed(MouseEvent e) {
		

		
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {

	    
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		

	}


	@Override
	public void mouseExited(MouseEvent e) {
	 
	    
	}



	public int getRx() {
	    return scrollX;
	}

	public int getRy() {
	    return scrollY;
	}




	

	

}