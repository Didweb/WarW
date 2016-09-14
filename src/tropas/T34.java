package tropas;

import java.awt.Image;

public class T34 extends Tanque {

    
    
    
    public T34(int posicionX, int posicionY, String bando, int altoVentana){
	super(posicionX, posicionY, bando, altoVentana);
	
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
