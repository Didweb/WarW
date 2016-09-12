package graficos;

public class TileData extends Tiles{

	
    
    public TileData(){
	setAncho_tile(64);
	setAlto_tile(32);
	
	inicaimosTiles();
    }
    
	
	public void montarNivel(){

		
		
		
		cargarMapa("recursos/plano_1_peligro.png");
		generarMapa();
		montarBimapas(tileDataPeligro);
		//dameData(tileDataPeligro);
		
		
		cargarMapa("recursos/plano_1_dificultad.png");
		generarMapa();
		montarBimapas(tileDataDificultad);
		//dameData(tileDataDificultad);
		
		cargarMapa("recursos/plano_1_penetracion.png");
		generarMapa();
		montarBimapas(tileDataPenetracion);
		//dameData(tileDataPenetracion);
		
		

		
		
		
	}
	
	public void iniciarMapeo(){
		

		
		planoInter = new int[tilesPorlado*tilesPorlado];
		planoBruto = new int[tilesPorlado*tilesPorlado];
		tileData = new int[tilesPorlado][tilesPorlado][4];
		tileDataPeligro = new int[tilesPorlado][tilesPorlado];
		tileDataDificultad = new int[tilesPorlado][tilesPorlado];
		tileDataPenetracion = new int[tilesPorlado][tilesPorlado];
		
	}
	
}
