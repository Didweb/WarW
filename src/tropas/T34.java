package tropas;



import graficos.TileData;

public class T34 extends Tanque {

    
    
    
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
	anchoTile = 64;
	altoTile = 32;
	this.tileData = tileData;
	
	
    }



}
