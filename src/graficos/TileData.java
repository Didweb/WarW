package graficos;

public class TileData extends Tiles{

	
    
    public TileData(){
	setAncho_tile(64);
	setAlto_tile(32);
	
	inicaimosTiles();
    }
    
	
	public void montarNivel(){

		
		
		
		cargarMapa("recursos/niveles/1/planos/peligro.png");
		generarMapa();
		montarMapsData(tileDataPeligro);
		//dameData(tileDataPeligro);
		
		
		cargarMapa("recursos/niveles/1/planos/dificultad.png");
		generarMapa();
		montarMapsData(tileDataDificultad);
		//dameData(tileDataDificultad);
		
		cargarMapa("recursos/niveles/1/planos/penetracion.png");
		generarMapa();
		montarMapsData(tileDataPenetracion);
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
