package control;

import java.awt.MouseInfo;
import java.awt.Point;
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
		this.scrollX = Rx;
		this.scrollY = Ry;
		
		
	
		
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
		anteriorRatonX=e.getX();
		anteriorRatonY=e.getY();
		
		int baseAlto = tanquesPlay[0].getDatsSel()[1];
		
		int newCol=0;
		int ncol=1;
		int rondas=0;
		int baseRondas = (baseAlto+60);
		int saltosy=0;
		
		for (int xt=0;xt<tanquesPlay.length-1;xt++){
			
			
			if (rondas==3){
				rondas=0;
				
				if(ncol==1){
					newCol=100;
					} else {
					newCol=100*ncol;
					}
				ncol++;
				
				baseRondas=(baseAlto+60);
			}
			
			
			 int x=tanquesPlay[xt].getDatsSel()[0]+newCol;

			 int y=baseRondas;
			 int xanc=x+tanquesPlay[xt].getDatsSel()[2];
			 int yalt=y+tanquesPlay[xt].getDatsSel()[3];
			 rondas++;
				saltosy=tanquesPlay[xt].getDatsSel()[4];
				baseRondas+=saltosy;
			 
			 
			  
			 if(e.getX() > x && e.getX() < xanc
				 && e.getY() > y && e.getY() < yalt){
			   
				 tanquesPlay[xt].setSelccionado(true);
			 } else {
				 
				 if (tanquesPlay[xt].isSelccionado() == true 
					 && e.getY() < tanquesPlay[xt].getDatsSel()[1]){
					 
					 tanquesPlay[xt].setPosEnTablero(e.getX(), e.getY(),scrollX,scrollY);
					 
				 } else {
					 tanquesPlay[xt].setSelccionado(false); 
				 }
				 
			 }
			
			
		}
		
		

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