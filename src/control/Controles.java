package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
	
	public boolean centrado = false;
	
	private int velocidad=8;
	
	private int actualRatonX;
	private int actualRatonY;
	
	private int Rx=0;
	private int Ry=0;
	
	private Tanque[] tanquesPlay;
	
	public void initActores(Tanque[] play){
	    
	    tanquesPlay = play;
	}

	public void actualizar(){

		
		salir = teclas[KeyEvent.VK_ESCAPE];
		arriba = teclas[KeyEvent.VK_UP];
		abajo = teclas[KeyEvent.VK_DOWN];
		izquierda = teclas[KeyEvent.VK_LEFT];
		derecha = teclas[KeyEvent.VK_RIGHT];
		centrar = teclas[KeyEvent.VK_C];
		
	
		
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
	    	Rx = e.getX();
		Ry = e.getY();
		
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	   
	    int xp;
		int yp;
		
		for (Tanque t: tanquesPlay){
		    
		    xp = t.getPosicionX()-Rx;
		    yp = t.getPosicionY()-Ry;
		    
		  
		    
		    if  ((e.getX() > xp)  && (e.getX() < (xp + 60)) && 
				 (e.getY() > yp)  && (e.getY() < (yp + 60))
				 
			    ||
			    (e.getX() > t.getAreaSel()[0])  && (e.getX() < t.getAreaSel()[2]) && 
				 (e.getY() > t.getAreaSel()[1])  && (e.getY() < t.getAreaSel()[3])
				 ) 
			    
			    { 
			
					if (!t.isSelccionado()){
					    t.setSelccionado(true); 
					} else {
					    t.setSelccionado(false);
					}
	
		    } else {
			
			if (t.isSelccionado()== true &&  e.getY() < t.getAreaSel()[1] ){
			    System.out.println("VEN AQUI : "+e.getX()+" "+e.getY());
			    
				 //t.setPosicionX(e.getX()+Rx);
				 //t.setPosicionY(e.getY()+Ry);
				 
				 t.setDestinoX(e.getX()+Rx);
				 t.setDestinoY(e.getY()+Ry);
				 
				 t.setEnMovimineto(true);
				 
			   
			   
			} 
		    }
		    
		    
		    
		
		}
	    
	}


	@Override
	public void mousePressed(MouseEvent e) {
		
		
		
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
	  //  System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	 
	    
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	
	    
	}


	@Override
	public void mouseExited(MouseEvent e) {
	    // TODO Auto-generated method stub
	 
	    
	}

	public void getPosRelativaTanque(int Rx,int Ry){
	    
	    this.Rx = Rx;
	    this.Ry = Ry;
	}

	public int getRx() {
	    return Rx;
	}

	public int getRy() {
	    return Ry;
	}




	

	

}