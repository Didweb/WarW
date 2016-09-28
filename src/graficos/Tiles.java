package graficos;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class Tiles {


	protected int tilesPorlado = 0;
	protected int tilesPoralto = 0;
	private int tilesTotal;
	
	protected int[] planoInter;
	protected int[] planoBruto;
	protected int[][][] tileData;
	
	protected int[][] tileDataPeligro;
	protected int[][] tileDataDificultad;
	protected int[][] tileDataPenetracion;
	
	protected int[][][] TileDataNivel; 
	protected int[][] guiaIso2D;
	
	private int celdaPosicionX;
	private int celdaPosicionY;
	
	private int[][] coordLogicas;
	private int[][] coordLogicasPix;
	private int[][] coordLogicasIso;
	private int[] linkCoorIsoInit;
	private int ancho_tile;
	private int alto_tile;
	private int buscaLoX;
	private int buscaLoY;
	
	
	public  Tiles(int[] valueSizes){
		
		ancho_tile = valueSizes[2];
		alto_tile = valueSizes[3]; 
		
	}
	

	public void inicaimosTiles(int ancho_mapa){
		
		
		tilesPorlado = ancho_mapa/ancho_tile;
		tilesPoralto = ancho_mapa/alto_tile;
		tilesTotal = tilesPorlado*tilesPorlado;
		coordLogicas();
		
		planoInter = new int[tilesPorlado*tilesPorlado];
		planoBruto = new int[tilesPorlado*tilesPorlado];
		TileDataNivel = new int[tilesPorlado*tilesPorlado][tilesPorlado][tilesPorlado];
		
		Iso2D iso2D = new Iso2D(planoInter, getANCHO_TILE(), getALTO_TILE(), 1024/8, (1024/2)*-1);
		iso2D.calcularIso();
		
		guiaIso2D = iso2D.getRes();
		coordLogicasPix();
		
		
	}

	
	public int getTilesPorlado() {
		return tilesPorlado;
	}


	public int getTilesPoralto() {
		return tilesPoralto;
	}


	public void setTileDataPeligro(int[][] tileDataPeligro) {
		this.tileDataPeligro = tileDataPeligro;
	}


	public void setTileDataDificultad(int[][] tileDataDificultad) {
		this.tileDataDificultad = tileDataDificultad;
	}
	
	

	public int buscaCelda(int buscaX, int buscaY, int[][] listado){
		int dudax = buscaX;
		int duday = buscaY;
		
		for (int v=0; v< tileData.length;v++){
			
			for (int vv=0; vv<tileData[v].length;vv++){
				
				
				if (tileData[v][vv][0]<=dudax 
						&& tileData[v][vv][2]>=dudax 
						&&  tileData[v][vv][1]<=duday 
						&& tileData[v][vv][3]>=duday){
					
					celdaPosicionX = v;
					celdaPosicionY = vv;
				}
			}
			
		}
		
		return listado[celdaPosicionX][celdaPosicionY];
		
	}
	

	
	public void iniciarCoor(){
		
		int x = 0;
		int y = 0;
		int xx = ancho_tile;
		int yy = alto_tile;
		int zz = 0;
		int filas = 0;
		
		for(int z = 0; z < tilesTotal-1; z++ ){
					
			if(zz == tilesPorlado-1){
			x += ancho_tile;
			y = 0;
			xx+=ancho_tile;
			yy=alto_tile;
			zz=0;
			filas++;
				
			} else {
				y+=ancho_tile;
				yy+=alto_tile;
				zz++;
			}
			tileData[filas][zz][0]=x;
			tileData[filas][zz][1]=y;
			tileData[filas][zz][2]=xx;
			tileData[filas][zz][3]=yy;
			
			
		}

		
	}
	
	public int[][] coordLogicas(){
		
		coordLogicas = new int[tilesTotal][2];
		
		int y=0;
		int x=0;
		for(int ntile=0;ntile<tilesTotal;ntile++){
			
			if(x==tilesPorlado){
				x=0;
			y++;			
				
				}
			coordLogicas[ntile][0]=x;
			coordLogicas[ntile][1]=y;
			//System.out.println("coorL ["+ntile+"][0] = "+x);
			//System.out.println("coorL ["+ntile+"][1] = "+y);
			x++;
		}
		
		return coordLogicas;
		
	}
	
	
	
	public int[][] getCoordLogicasPix() {
		return coordLogicasPix;
	}



	



	public int[][] coordLogicasPix(){
		coordLogicasPix = new int[tilesTotal][2];
		linkCoorIsoInit = new int[tilesTotal];
		
		int laxPix = 0;
		int layPix = 0;
		int casilla = 0;
		
		for(int ntile=0;ntile<tilesTotal;ntile++){
			
			casilla = guiaIso2D[ntile][0];
			laxPix = guiaIso2D[casilla][1];
			layPix = guiaIso2D[casilla][2];
			

			coordLogicasPix[casilla][0]=laxPix;
			coordLogicasPix[casilla][1]=layPix;
			
			linkCoorIsoInit[guiaIso2D[ntile][0]] = ntile;
					
			//System.out.println("crLogPix ["+casilla+"][0] = "+coordLogicasPix[casilla][0]);
			//System.out.println("crLogPix ["+casilla+"][1] = "+coordLogicasPix[casilla][1]);
					
		}
		
		return coordLogicasPix;
	}
	
	
	
	public int[] getLinkCoord() {
		return linkCoorIsoInit;
	}






	public int[][][] montarBimapas(int[][] arrayPasado){
	
		
		coordLogicasIso = new int[tilesTotal][2];
		int[][][] res = new int[tilesTotal][tilesPorlado][tilesPorlado];

		
		
		int laCasilla;
		int xLogica;
		int yLogica;
		
		for (int camino=0;camino<tilesTotal;camino++) {
			
			laCasilla = planoInter[arrayPasado[camino][0]];
			
			xLogica = coordLogicas[arrayPasado[camino][0]][0];
			yLogica = coordLogicas[arrayPasado[camino][0]][1];
			res[camino][xLogica][yLogica] = laCasilla;
			
			coordLogicasIso[camino][0]=xLogica;
			coordLogicasIso[camino][1]=yLogica;
			
			
		}
		
		return res;
		
	}
	
	
	
	public int[][] montarMapsData(int[][] arrayPasado){
		
		int zz = 0;
		int filas = 0;
		int arrastre=0;
		for(int z = 0; z < planoInter.length-1; z++ ){
					
			if(zz == tilesPorlado){
		
				zz=0;
				filas++;
				
				} 
			arrayPasado[filas][zz] = planoInter[arrastre];
			arrastre++;
			zz++;
			
		}
		arrayPasado[filas][zz] = planoInter[arrastre];
		return arrayPasado;
		
	}

	
	
	protected void cargarMapa(String ruta) {
		
		try {
			BufferedImage imagen = ImageIO.read(new FileInputStream(ruta));
			System.out.println("Ruta cargada:"+ruta);
			int ancho = imagen.getWidth();
			int alto = imagen.getHeight();
			System.out.println(ancho +" "+alto+" "+ruta);

			imagen.getRGB(0, 0, ancho, alto, planoBruto, 0, ancho);
			
			} catch (IOException e) {
				System.out.println(" Imagen no encontrada ");
				e.printStackTrace();
			}
	}
	
	
	
	public void setTileDataPenetracion(int[][] tileDataPenetracion) {
		this.tileDataPenetracion = tileDataPenetracion;
	}


	
	
	
	
	
	
	protected void generarMapa(){
		for (int i = 0; i < planoInter.length; i++){
			
			switch(planoBruto[i]){
			
			// Blanco: Bajo
			case 0xffffffff:
				planoInter[i] = 0;
				continue;
			
			// Amarillo: Poco
			case 0xffffff00:
				planoInter[i] = 1;
				continue;
			
			// Naranja: Medio
			case 0xffff6600:
				planoInter[i] = 2;
				continue;	
			
			// Rojo: Alto
			case 0xffff0000:
				planoInter[i] = 3;
				continue;
		
			
			default:
				planoInter[i] = 0;
			}
			
			}
	}
	
	
	
	protected void generarMapaImg(){
		for (int i = 0; i < planoInter.length; i++){
			
			switch(planoBruto[i]){
			
			// cesped
			case 0xff088c0b:
				planoInter[i] = 0;
				continue;
			
			// camino_de
			case 0xffac6614:
				planoInter[i] = 1;
				continue;
			
			// camino_iz
			case 0xffe8aa60:
				planoInter[i] = 2;
				continue;	
			
			// curva_de
			case 0xff613d13:
				planoInter[i] = 3;
				continue;
				
			// curva_iz
			case 0xff331e05:
				planoInter[i] = 4;
				continue;
					
			// puente_de
			case 0xff126c6f:
				planoInter[i] = 5;
				continue;
				
			// puente_iz
			case 0xff044749:
				planoInter[i] = 6;
				continue;	
				
			// rio_de
			case 0xff076063:
				planoInter[i] = 7;
				continue;	
					
			// rio_iz case 0xff03adb3:
			case 0xff03adb3:
				planoInter[i] = 8;
				continue;	
			
			default:
				planoInter[i] = 0;
			}
			
			}
	}
	
	
	
	public int getANCHO_TILE() {
		return ancho_tile;
	}
	
	
	
	public int getALTO_TILE() {
		return alto_tile;
	}
	
	

	public int[][] getCoordLogicas() {
		return coordLogicas;
	}
	
	
	
	public int[][] getCoordLogicasIso() {
		return coordLogicasIso;
	}
	
	
	public int[][] getTileDataPeligro() {
	    return tileDataPeligro;
	}

	
	
	public void setAncho_tile(int ancho_tile) {
	    this.ancho_tile = ancho_tile;
	}



	public void setAlto_tile(int alto_tile) {
	    this.alto_tile = alto_tile;
	}
	
	public int[][] getGuiaIso2D() {
		return guiaIso2D;
	}
	
	public int[][][] getTileDataNivel() {
		return TileDataNivel;
	}
	
	public void dameData(int[][] arrayContenido){
		
		for(int g=0;g<arrayContenido.length;g++){
		for(int gg=0;gg<arrayContenido[g].length;gg++){
		System.out.println(arrayContenido.length + " | elementos :[ "+g+" ] [ "+gg+" ] "+arrayContenido[g][gg]);
		}
		
	}
	}


	public int[][] getTileDataPenetracion() {
		return tileDataPenetracion;
	}
	
	
	// Busca coordenadas Logicas apartir de una posicion X e Y en pixeles.
	public void buscaLogicas(int posicionX, int posicionY,int scrollX , int scrollY){
		

		int casillax;
		int casillay;
		int nCasilla;
		
		int posxLogica;
		int posyLogica;
		

		
		for(int co=0; co<getCoordLogicasPix().length;co++){
			
			nCasilla = getGuiaIso2D()[co][0];
			casillax = getGuiaIso2D()[co][1]-scrollX;
			casillay = getGuiaIso2D()[co][2]-(scrollY-(getALTO_TILE()*2));
			
			
			
			posxLogica = getCoordLogicas()[nCasilla][0];
			posyLogica = getCoordLogicas()[nCasilla][1];
	
			if (casillax < posicionX 
				&& casillax+getANCHO_TILE() > posicionX  
				&& casillay< posicionY 
				&& casillay+getALTO_TILE() > posicionY ){
			
				
				buscaLoX = getCoordLogicas()[nCasilla][0];
				buscaLoY = getCoordLogicas()[nCasilla][1]-1;

				break;
			}
		}
	}

	
	
	public int[][] planoDistancia(int logicaX, int logicaY){
		
		int[][] resDestino = new int[tilesPorlado][tilesPoralto];
		
		int xx=0;
		int yy=0;
		int value =0;
		for (int x=0; x<tilesTotal; x++){
			
			if(xx==tilesPorlado-1){
				xx=0;
				yy++;
			}
			if (xx==logicaX && yy==logicaY){
				value=0;} 
			else {
				value=1;}
			
			resDestino[xx][yy]=value;
		
		System.out.println("("+logicaX+"-"+logicaY+")  "+xx+","+yy+" : "+resDestino[xx][yy]);	
		xx++;	
		}
		
		
		return resDestino;
	}

	public int getBuscaLoX() {
		return buscaLoX;
	}


	public int getBuscaLoY() {
		return buscaLoY;
	}
	
}
