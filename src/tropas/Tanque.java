package tropas;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tanque extends Ejercito {

    private int posicionXLogica;
    private int posicionYLogica;
    
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

    


    // Areas seleccion de panel
    private int inicioArea=100;
    private int areaAncho=120;
    private int[] areaSel = new int[4];
    

    public Tanque(int posicionXLogica, int posicionYLogica, String bando, int altoV){
	
	setBando(bando);
	this.posicionXLogica = posicionXLogica;
	this.posicionYLogica= posicionYLogica;
	actitud = actictudLista[0];
	
	id = controlId;
	controlId++;
	
	
	int areaSelxCal =  areaAncho*id;
	
	if (areaSelxCal==0) {
	    areaSelxCal = inicioArea;
	} else {
	    areaSelxCal = (areaAncho*id)+areaAncho;}
	
	
	
	int areaSely = altoV-150;
	
	areaSel[0]= areaSelxCal;
	areaSel[1]= areaSely;
	areaSel[2]= areaSelxCal+120;
	areaSel[3]= altoV;
	
	
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
    
    public void img(String ruta){
	BufferedImage bufferImage = null;
	try {
	    bufferImage = ImageIO.read(new File("recursos/t34.gif"));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	Image imgTanque = (Image)bufferImage;
	       
	
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
