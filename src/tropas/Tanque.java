package tropas;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graficos.TileData;



public class Tanque extends Ejercito {

    private int posicionXLogica;
    private int posicionYLogica;
    
    private int posicionX;
    private int posicionY;
    
	protected int anchoTile;
	protected int altoTile;
    
	protected TileData tileData;
	
    private int destinoXLogica;
    private int destinoYLogica;
    
    private boolean estaVivo =  true;
    private boolean selccionado = false;
    
    
    private String actitud;
    
    private String[] actictudLista = {"Reposo", 
                	    "Mantener Posición", 
                	    "Cubrir", 
                	    "Defender Objetivo", 
                	    "Ataque", 
                	    "Avance Pasivo", 
                	    "Avance Activo"};
    protected String modelo;
    private Image imgTanque;
    


    private static int controlId=0;
    private int id;


    private boolean enMovimineto = false;
    
    private int vida = 74;
    private int depoCombustible = 100;
    
    protected int blindaje;


    protected int municion;
    protected int velocidadDisparo;
    protected int velocidad;
    protected int punteria;
    protected int movilidad;
    protected int capaCombustible;
    protected int casilla;

    


    // Areas seleccion de panel
    private int inicioArea=100;
    private int areaAncho=120;
    private int[] areaSel = new int[4];
	private int scrollX;
	private int scrollY;
    

    public Tanque(String bando,  int[] ancalt){
	
	setBando(bando);
	actitud = actictudLista[0];
	
	id = controlId;
	controlId++;
	
	
	
	scrollX = ancalt[0]/2;
	scrollY = ancalt[1]/2;
	actPosicion();
	
	int areaSelxCal =  areaAncho*id;
	
	if (areaSelxCal==0) {
	    areaSelxCal = inicioArea;
	} else {
	    areaSelxCal = (areaAncho*id)+areaAncho;}
	
	
	

	
	
    }
    

    public void actPosicion(){
    	//int[][] posicionesLogicasPix = tileData.getCoordLogicasPix();
    	System.out.println(casilla);
    	int puente = tileData.getLinkCoord()[casilla];
    	int logicPixX = tileData.getCoordLogicasPix()[puente][0];
    	int logicPixY = tileData.getCoordLogicasPix()[puente][1];
    	
    	
	    posicionX = (logicPixX+altoTile/2)-scrollX;
	    posicionY = (logicPixY+anchoTile/2)-scrollY;	
    	
	    //System.out.println("posicionX: "+logicPixX+" . posicionY: "+logicPixY);
	    
    }

    
    public int getPosicionX() {
		return posicionX;
	}


	public int getPosicionY() {
		return posicionY;
	}


	public void actMovimiento(){
	
	
    
	if(enMovimineto){
		
		if(posicionXLogica != destinoXLogica  ){
		    
		    if(posicionXLogica < destinoXLogica){
			posicionXLogica++;
			
		    } else if(posicionXLogica > destinoXLogica){
			posicionXLogica--;
			
		    }
		   
		    
		    if(posicionYLogica < destinoYLogica){
			posicionYLogica++;
			
		    } else if( posicionYLogica > destinoYLogica){
			posicionYLogica--;
			
		    }
		    
		}
		
	}
    }
    


    public int getDestinoXLogica() {
        return destinoXLogica;
    }

    public void setDestinoXLogica(int destinoXLogica) {
        this.destinoXLogica = destinoXLogica;
    }

    public int getDestinoYLogica() {
        return destinoYLogica;
    }

    public void setDestinoYLogica(int destinoYLogica) {
        this.destinoYLogica = destinoYLogica;
    }

    

    public int[] getAreaSel() {
        return areaSel;
    }




    public boolean isEnMovimineto() {
        return enMovimineto;
    }

    public void setEnMovimineto(boolean enMovimineto) {
        this.enMovimineto = enMovimineto;
    }

    public int getId() {
        return id;
    }




    public boolean isSelccionado() {
        return selccionado;
    }

    public void setSelccionado(boolean selccionado) {
        this.selccionado = selccionado;
    }
    
    

    public int getVida() {
        return vida;
    }


    public int getBlindaje() {
        return blindaje;
    }


    public int getMunicion() {
        return municion;
    }



    public String getModelo() {
        return modelo;
    }


    public boolean isEstaVivo() {
        return estaVivo;
    }



    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }
    
    public String getActitud() {
        return actitud;
    }



    public void setActitud(String actitud) {
        this.actitud = actitud;
    }



    public int getPosicionXLogica() {
        return posicionXLogica;
    }

    public void setPosicionXLogica(int posicionXLogica) {
        this.posicionXLogica = posicionXLogica;
    }

    public void setPosicionYLogica(int posicionYLogica) {
        this.posicionYLogica = posicionYLogica;
    }

    public int getPosicionYLogica() {
        return posicionYLogica;
    }
    
    public int getCapaCombustible() {
        return capaCombustible;
    }
    public int getDepoCombustible() {
        return depoCombustible;
    }
    
    public Image getImg() {
        return imgTanque;
    }
    


   
    
    
    public void seleccionaTanque(int mouseX, int mouseY){
	
	 if  ((mouseX > posicionXLogica)  && (mouseX < (posicionXLogica + 60)) && 
		 (mouseY > posicionYLogica)  && (mouseY < (posicionYLogica + 60))
		 
	    ||
	    (mouseX > areaSel[0])  && (mouseX < areaSel[2]) && 
		 (mouseY > areaSel[1])  && (mouseY < areaSel[3])
		 ) 
	    
	    { 
	
			if (selccionado){
			    setSelccionado(true); 
			} else {
			    setSelccionado(false);
			}

    } else {
	
	if (selccionado== true &&  mouseY < areaSel[1] ){
	    
		 
	    
	    
	    
		// t.setDestinoX(mouseX+scrollX);
		 // t.setDestinoY(mouseY+scrollY);
		 
		 setEnMovimineto(true);
		 
	   
	   
	} 
    }
    }
    
    
}
