package graficos;

public class TileData extends Tiles{

	
    
    public TileData(int[] valueSizes,int ancho_mapa){
    	super(valueSizes);
	setAncho_tile(64);
	setAlto_tile(32);
	
	inicaimosTiles(ancho_mapa);
    }
    
	
	public void montarNivel(){

		
		
		
		cargarMapa("recursos/niveles/1/planos/peligro.png");
		generarMapa();
		setTileDataPeligro(montarMapsData(tileDataPeligro));
		//dameData(tileDataPeligro);
		
		
		cargarMapa("recursos/niveles/1/planos/dificultad.png");
		generarMapa();
		setTileDataDificultad(montarMapsData(tileDataDificultad));
		//dameData(tileDataDificultad);
		
		cargarMapa("recursos/niveles/1/planos/penetracion.png");
		generarMapa();
		setTileDataPenetracion(montarMapsData(tileDataPenetracion));
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
