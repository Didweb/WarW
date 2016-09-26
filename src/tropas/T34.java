package tropas;



import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import graficos.TileData;

public class T34 extends Tanque {

    
    
   




	private BufferedImage imagen = null;


	public BufferedImage getImagen() {
		return imagen;
	}


	public T34(String bando,  TileData tileData, int[] ancalt){
	super(bando,  ancalt);
	
	modelo = "T-34";
	
	velocidad = 50;
	punteria = 80;
	movilidad = 70;
	capaCombustible = 50;
	blindaje = 67;
	municion = 47;
	velocidadDisparo = 15;
	anchoTile = 60;
	altoTile = 60;
	this.tileData = tileData;
	icono("Tank-T-34_mini.gif");
	imagenT("t34.gif");
    }

	
	
    


}
