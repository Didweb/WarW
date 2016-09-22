package graficos;

	

public class TileImg extends Tiles{


	
	
	
	public TileImg(){
		
		setAncho_tile(64);
		setAlto_tile(32);
		inicaimosTiles();
		
	}
	
	public void MapaImg(int batalla){
		
		cargarMapa("recursos/niveles/"+batalla+".gif");
		generarMapaImg();
		
		TileDataNivel = montarBimapas(guiaIso2D);

		

	}

	

	
}
