package tropas;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import control.Controles;
import graficos.TileData;



public class Tanque extends Ejercito {

    private int posicionXLogica=0;
    private int posicionYLogica=0;
    
    private int posicionX;
    private int posicionY;
    
	protected int anchoTile;
	protected int altoTile;
    
	BufferedImage imagenIcono;
	
	protected TileData tileData;
	
	private boolean colocado = false;
	

	private int destinoXLogica;
    private int destinoYLogica;
    
    private int[][] planoDestino;
    






	private boolean estaVivo =  true;
    private boolean selccionado = false;
    
    // Posicion de selector del menu x,y,ancho,alto,espacio Rotacion,margen
    private int[] datsSel = new int[6];
    
    
    
    private boolean menuContextual = false;



    private int actitud = 7;
    
	private String[] actictudLista = {"Reposo", 
                	    "Mantener Posición", 
                	    "Cubrir", 
                	    "Defender Objetivo", 
                	    "Ataque", 
                	    "Avance Pasivo", 
                	    "Avance Activo",
                	    "Posicionar"};
    protected String modelo;
    private Image imgTanque;
    
    private boolean sinDestion = true;









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
	
	id = controlId;
	controlId++;
	
	
	
	scrollX = ancalt[0]/2;
	scrollY = ancalt[1]/2;
	
	// Posicion de selector del menu 0:x, 1:y, 2:ancho,3:alto, 4:espacio rotacion, 5:margen superior inicial
	datsSel[0] = 15;
	datsSel[1] = (ancalt[1]-(ancalt[1]/6));
	datsSel[2] = 34;
	datsSel[3] = 20;
	datsSel[4] = 40;
	datsSel[5] = 40;
	

	
	planoDestino = new int[2048/ancalt[2]][2048/ancalt[3]];
	System.out.println("total "+planoDestino.length);

	
	
    }
	public boolean isSinDestion() {
		return sinDestion;
	}


	public void setSinDestion(boolean sinDestion) {
		this.sinDestion = sinDestion;
	}
    
    public void setPlanoDestino(int[][] planoDestino) {
		this.planoDestino = planoDestino;
	}
    
    public int[][] getPlanoDestino() {
		return planoDestino;
	}


	public void errorT(Graphics g){
    	g.setColor(Color.RED); 
    }
    
    public boolean isColocado() {
		return colocado;
	}

    public boolean isSinDestino() {
		return sinDestion;
	}

	public void setSinDestino(boolean creadoDestino) {
		this.sinDestion = creadoDestino;
	}

	public void setColocado(boolean colocado) {
		this.colocado = colocado;
	}
    
    public String[] getActictudLista() {
		return actictudLista;
	}

	public boolean isMenuContextual() {
		return menuContextual;
	}



	public void setMenuContextual(boolean menuContextual) {
		this.menuContextual = menuContextual;
	}
    



    
    public int[] getDatsSel() {
		return datsSel;
	}





	public int getPosicionX() {
		return posicionX;
	}


	public int getPosicionY() {
		return posicionY;
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

    public void icono(String imgIco){
    	
   	 File f = new File("recursos/"+imgIco);
	    try {
	    	imagenIcono = ImageIO.read(f);
			} catch (IOException e) {
			    e.printStackTrace();
			}
   	
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
    
    public int getActitud() {
        return actitud;
    }



    public void setActitud(int valor) {
        
    	if(isMenuContextual()){
			
    		this.actitud = valor; 
		}
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
    
	public BufferedImage getImagenIcono() {
		
		return imagenIcono;
	}


	
	
	
	public void setPosEnTablero(int posicionX, int posicionY,int scrollX , int scrollY){
		
		
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		
		tileData.buscaLogicas(posicionX ,posicionY ,scrollX  ,scrollY);
		
		posicionXLogica = tileData.getBuscaLoX();
		posicionYLogica = tileData.getBuscaLoY();
		
		
				
	}







}
