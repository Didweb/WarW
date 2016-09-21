package tropas;

import java.awt.Image;

public class T34 extends Tanque {

    
    
    
    public T34(int posicionXLogica, int posicionYLogica, String bando, int altoVentana){
	super(posicionXLogica, posicionYLogica, bando, altoVentana);
	
	modelo = "T-34";
	
	velocidad = 50;
	punteria = 80;
	movilidad = 70;
	capaCombustible = 50;
	blindaje = 67;
	municion = 47;
	velocidadDisparo = 15;
	
    }



}
