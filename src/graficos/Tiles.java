package graficos;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class Tiles {

	private final int ANCHO_MAPA = 2048;
	private final int ANCHO_TILE = 64;
	
	
	protected int tilesPorlado = 0;
	private int tilesTotal;
	
	protected int[] planoInter;
	protected int[] planoBruto;
	protected int[][][] tileData;
	protected int[][] tileDataPeligro;
	protected int[][] tileDataDificultad;
	protected int[][] tileDataPenetracion;
	private int celdaPosicionX;
	private int celdaPosicionY;
	
	public Tiles(){
		
		tilesPorlado = ANCHO_MAPA/ANCHO_TILE;
		tilesTotal = tilesPorlado*tilesPorlado;
		
		
	}
	
	public void buscaCelda(int buscaX, int buscaY){
		int dudax = buscaX;
		int duday = buscaY;
		
		for (int v=0; v< tileData.length;v++){
			
			for (int vv=0; vv<tileData[v].length;vv++){
				
				
				
				//System.out.println("Valor "+v+" "+tileData[v][vv][0]+">"+duda0+" "+v+""+tileData[v][vv][1]+" < "+duda1+" ");
				if (tileData[v][vv][0]<=dudax && tileData[v][vv][2]>=dudax &&  tileData[v][vv][1]<=duday && tileData[v][vv][3]>=duday){
					
					//System.out.println("Valor "+dudax+" y "+duday+" esta en "+v+" "+vv);
					celdaPosicionX = v;
					celdaPosicionY = vv;
				}
			}
			
		}
		
		
		System.out.println("Posicion de celda: "+celdaPosicionX+" "+celdaPosicionY);
	}
	
	

	
	public void iniciarCoor(){
		
		int x = 0;
		int y = 0;
		int xx = ANCHO_TILE;
		int yy = ANCHO_TILE;
		int zz = 0;
		int filas = 0;
		
		for(int z = 0; z < tilesTotal-1; z++ ){
					
			if(zz == tilesPorlado-1){
			x += ANCHO_TILE;
			y = 0;
			xx+=ANCHO_TILE;
			yy=ANCHO_TILE;
			zz=0;
			filas++;
				
			} else {
				y+=ANCHO_TILE;
				yy+=ANCHO_TILE;
				zz++;
			}
			tileData[filas][zz][0]=x;
			tileData[filas][zz][1]=y;
			tileData[filas][zz][2]=xx;
			tileData[filas][zz][3]=yy;
			
			
		}
//		for(int g=0;g<tileData.length;g++){
//			for(int gg=0;gg<tileData[g].length;gg++){
//			System.out.println("n"+tileData.length+" elementos :[ "+g+" "+gg+"] "+tileData[g][gg][0]);
//				System.out.println("n elementos 1: "+tileData[g][gg][1]);
//				System.out.println("n elementos 2: "+tileData[g][gg][2]);
//				System.out.println("n elementos 3: "+tileData[g][gg][3]);
//			}
//			
//		}
		
	}
	
	public int[][] montarBimapas(int[][] arrayPasado){
	
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
			
			int ancho = imagen.getWidth();
			int alto = imagen.getHeight();
			
			imagen.getRGB(0, 0, ancho, alto, planoBruto, 0, ancho);
			
			} catch (IOException e) {
				System.out.println(" Imagen no encontrada ");
				e.printStackTrace();
			}
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
			
			// verde
			case 0xff088c0b:
				planoInter[i] = 0;
				continue;
			
			// Camnio verti
			case 0xffac6614:
				planoInter[i] = 1;
				continue;
			
			// camnio horiz
			case 0xffe8aa60:
				planoInter[i] = 2;
				continue;	
			
			// Camnio esquina
			case 0xff613d13:
				planoInter[i] = 3;
				continue;
				
			// Rio horizontal
			case 0xff03adb3:
				planoInter[i] = 4;
				continue;
					
			// Puente rio horizontal
			case 0xff126c6f:
				planoInter[i] = 5;
				continue;
			
			default:
				planoInter[i] = 0;
			}
			
			}
	}
	
	
	public int getANCHO_TILE() {
		return ANCHO_TILE;
	}

	public void dameData(int[][] arrayContenido){
		
		for(int g=0;g<arrayContenido.length;g++){
		for(int gg=0;gg<arrayContenido[g].length;gg++){
		System.out.println(arrayContenido.length + " | elementos :[ "+g+" "+gg+"] "+arrayContenido[g][gg]);
		}
		
	}
	}
	
}
