package tropas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Tanque extends Ejercito implements mover{

    private int posicionX;
    private int posicionY;
    
    private boolean estaVivo =  true;
    



    private boolean enMovimineto = false;
    
    private int vida = 74;
    private int blindaje = 100;
    private int municion = 25;
    private int velocidadDisparo;
    private int velocidad;
    private int punteria;
    private int movilidad;
    private int combustible = 100;
    
   

    public int getVida() {
        return vida;
    }


    public int getBlindaje() {
        return blindaje;
    }


    public int getMunicion() {
        return municion;
    }


    public int getCombustible() {
        return combustible;
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


    private String actitud;
    
    private String[] actictudLista = {"Reposo", 
                	    "Mantener Posición", 
                	    "Cubrir", 
                	    "Defender Objetivo", 
                	    "Ataque", 
                	    "Avance Pasivo", 
                	    "Avance Activo"};
    private String modelo;
    

    
    public Tanque(int posicionX, int posicionY, String bando, String modelo){
	
	setBando(bando);
	this.modelo = modelo;
	this.posicionX =  posicionX;
	this.posicionY = posicionY;
	actitud = actictudLista[0];
	
	
    }


    
    public String getActitud() {
        return actitud;
    }



    public void setActitud(String actitud) {
        this.actitud = actitud;
    }



    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getPosicionY() {
        return posicionY;
    }
    

    
}
