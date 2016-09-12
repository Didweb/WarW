package graficos;

	

public class TileImg extends Tiles{

	private int[][] TileDataNivel; 
	
	public int[][] getTileDataNivel() {
		return TileDataNivel;
	}

	public void MapaImg(){
		setAncho_tile(64);
		setAlto_tile(32);
		inicaimosTiles();
	    
	    
		planoInter = new int[tilesPorlado*tilesPorlado];
		planoBruto = new int[tilesPorlado*tilesPorlado];
		TileDataNivel = new int[tilesPorlado][tilesPorlado];

		//System.out.println("Long array: "+TileDataNivel.length+" | total tildes: "+(tilesPorlado*tilesPorlado));
		cargarMapa("recursos/mini_nivel1.gif");
		generarMapaImg();
		montarBimapas(TileDataNivel);
		//dameData(TileDataNivel);
	}
	
	
}
