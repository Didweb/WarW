package graficos;

	

public class TileImg extends Tiles{


	
	
	
	public TileImg(int[] valueSizes,int ancho_mapa){
		super(valueSizes);
		setAncho_tile(valueSizes[2]);
		setAlto_tile(valueSizes[3]);
		inicaimosTiles(ancho_mapa);
		
	}
	
	public void MapaImg(int batalla){
		
		cargarMapa("recursos/niveles/"+batalla+"/mapa/"+batalla+".gif");
		generarMapaImg();
		
		TileDataNivel = montarBimapas(guiaIso2D);

		

	}

	

	
}
