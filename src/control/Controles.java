package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import graficos.HojaSprites;
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

	public void dameCuadro(int valorX, int valorY){
	    
	    mapa.getImagenes();
	    int relativaX;
	    int relativaY;
	    
	    for(int y=0;y<mapa.getImagenes().length;y++){
		
		
		
		for(int x=0;x<mapa.getImagenes().length;x++){
		    
		    relativaX = (x-y)*(60/2)-scrollX;
		    relativaY = (x+y)*(32/2)-scrollY;
		    if(relativaX==valorX && relativaY==valorY){
			System.out.println("cuadro = ["+x+"]["+y+"]");
		    } else {System.out.println("cuadro = ["+(valorX % scrollX)+"]["+valorY+"]");}
		    
		    
		}
		}
	    
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
	    	scrollX = e.getX();
		scrollY = e.getY();
		
		actualRatonY = e.getY();
		actualRatonX = e.getX();
		
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
	   
	    int xp;
	    int yp;
	    
	    
		
		for (Tanque t: tanquesPlay){
		    
		    xp = t.getPosicionX();
		    yp = t.getPosicionY();
		    
		    int logicaMousX = ((e.getX())-scrollX)*60;
		    int logicaMousY = ((e.getY())-scrollY)*32;
		    
		    int PosEnRejX = e.getX();
		    int PosEnRejY = e.getY();
		    
		    dameCuadro(PosEnRejX, PosEnRejY);
		    
		    System.out.println(PosEnRejX+" < Casillas> "+PosEnRejY); 
		   // System.out.println(PosEnRejX+" <-> "+PosEnRejY+" || Click MOUSE:  "+"("+e.getX()+" / s: "+scrollX+")"+logicaMousX+" ----- "+"("+e.getY()+" / s: "+scrollY+")"+logicaMousY); 
		  //System.out.println(t.getId()+" --- > "+xp+" ----- "+yp);
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
			    //System.out.println("VEN AQUI : "+e.getX()+" "+e.getY());
			    
				 //t.setPosicionX(e.getX()+Rx);
				 //t.setPosicionY(e.getY()+Ry);
				 
			    
			    
			    
				 t.setDestinoX(e.getX()+scrollX);
				 t.setDestinoY(e.getY()+scrollY);
				 
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
	    
	    this.scrollX = Rx;
	    this.scrollY = Ry;
	}

	public int getRx() {
	    return scrollX;
	}

	public int getRy() {
	    return scrollY;
	}




	

	

}