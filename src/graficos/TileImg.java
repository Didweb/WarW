package graficos;

	

public class TileImg extends Tiles{

	private int[][][] TileDataNivel; 
	private int[][] guiaIso2D;
	
	
	
	
	public int[][][] getTileDataNivel() {
		return TileDataNivel;
	}

	
	public void MapaImg(){
		setAncho_tile(64);
		setAlto_tile(32);
		inicaimosTiles();
	    
	    
		planoInter = new int[tilesPorlado*tilesPorlado];
		planoBruto = new int[tilesPorlado*tilesPorlado];
		TileDataNivel = new int[tilesPorlado*tilesPorlado][tilesPorlado][tilesPorlado];
		

		cargarMapa("recursos/mini_nivel1.gif");
		generarMapaImg();
		

		
		Iso2D iso2D = new Iso2D(planoInter, getANCHO_TILE(), getALTO_TILE(), 1024/8, (1024/2)*-1);
		iso2D.calcularIso();
		
		guiaIso2D = iso2D.getRes();
		
		TileDataNivel = montarBimapas(guiaIso2D);

		

	}

	
	public int[][] getGuiaIso2D() {
		return guiaIso2D;
	}
	
	
}
