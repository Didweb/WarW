package graficos;

	

public class TileImg extends Tiles{

	private int[][] TileDataNivel; 
	
	public int[][] getTileDataNivel() {
		return TileDataNivel;
	}

	public void MapaImg(){
		planoInter = new int[tilesPorlado*tilesPorlado];
		planoBruto = new int[tilesPorlado*tilesPorlado];
		TileDataNivel = new int[tilesPorlado][tilesPorlado];
		System.out.println("Long array: "+TileDataNivel.length);
		cargarMapa("recursos/mini_nivel1.png");
		generarMapaImg();
		montarBimapas(TileDataNivel);
		//dameData(TileDataNivel);
	}
	
	
}
