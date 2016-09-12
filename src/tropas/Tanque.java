package tropas;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class Tanque extends Ejercito implements mover{

    private int posicionX;
    private int posicionY;
    
    private boolean estaVivo =  true;
    
    private boolean enMovimineto = false;
    
    private int vida = 100;
    private int blindaje;
    private int municion;
    private int velocidadDisparo;
    private int velocidad;
    private int punteria;
    private int movilidad;
    private String modelo;
    

    
    public Tanque(int posicionX, int posicionY, String bando){
	
	setBando(bando);
	this.posicionX =  posicionX;
	this.posicionY = posicionY;
	
	
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
