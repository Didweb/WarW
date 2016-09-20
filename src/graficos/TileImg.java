package graficos;

	

public class TileImg extends Tiles{

	private int[][][] TileDataNivel; 
	private int[][] guiaIso2D;
	private int[][][] listaCeldas;
	
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

		//System.out.println("Long array: "+TileDataNivel.length+" | total tildes: "+(tilesPorlado*tilesPorlado));
		cargarMapa("recursos/mini_nivel1.gif");
		generarMapaImg();
		
		
		Iso2D iso2D = new Iso2D(planoInter, 64, 32, 1024/8, (1024/2)*-1);
		iso2D.calcularIso();
		
		guiaIso2D = iso2D.getRes();
		
		TileDataNivel = montarBimapas(guiaIso2D);
		
		for(int x=0;x< guiaIso2D.length;x++){
			
			System.out.println(" guiaIso2D ["+x+"] = "+guiaIso2D[x][0]+" x:"+guiaIso2D[x][1]+" y:"+guiaIso2D[x][2]+" ");
			
		}
		
//		for(int c=0;c< listaCeldas.length;c++){
//			
//			for(int x=0;x<listaCeldas[c].length;x++){
//				
//				for(int y=0;y<listaCeldas[c][x].length;y++){
//					
//					System.out.println(" ListaCelda["+c+"]["+x+"]["+y+"] =  "+listaCeldas[c][x][y]);
//				}
//			}
//			
//			
//		}
		
		//dameData(TileDataNivel);
	}

	public int[][] getGuiaIso2D() {
		return guiaIso2D;
	}
	
	
}
