package graficos;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class Tiles {

	private final int ANCHO_MAPA = 2048;
	private final int ANCHO_TILE = 64;
	
	
	private int tilesPorlado = 0;
	private int tilesTotal;
	
	private int[] planoInter;
	private int[] planoBruto;
	private int[][][] tileData;
	private int[][] tileDataPeligro;
	private int[][] tileDataDificultad;
	private int[][] tileDataPenetracion;
	
	public Tiles(){
		
		tilesPorlado = ANCHO_MAPA/ANCHO_TILE;
		tilesTotal = tilesPorlado*tilesPorlado;
		
		
	}
	
	public void iniciarMapeo(){
		
		
		
		planoInter = new int[tilesPorlado*tilesPorlado];
		planoBruto = new int[tilesPorlado*tilesPorlado];
		tileData = new int[tilesPorlado][tilesPorlado][4];
		tileDataPeligro = new int[tilesPorlado][tilesPorlado];
		tileDataDificultad = new int[tilesPorlado][tilesPorlado];
		tileDataPenetracion = new int[tilesPorlado][tilesPorlado];
		
	}
	
	public void iniciarCoor(){
		
		int x = 0;
		int y = 0;
		int xx = ANCHO_TILE;
		int yy = ANCHO_TILE;
		int xxLastre = ANCHO_TILE;
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
		for(int g=0;g<tileData.length;g++){
			for(int gg=0;gg<tileData[g].length;gg++){
				System.out.println("n elementos :[ "+g+" "+gg+"] "+tileData[g][gg][0]);
				System.out.println("n elementos : "+tileData[g][gg][1]);
				System.out.println("n elementos : "+tileData[g][gg][2]);
				System.out.println("n elementos : "+tileData[g][gg][3]);
			}
			
		}
		
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
	
	
	public void montarNivel(){
		
		cargarMapa("recursos/plano_1_peligro.png");
		generarMapa();
		montarBimapas(tileDataPeligro);
		
		cargarMapa("recursos/plano_1_dificultad.png");
		generarMapa();
		montarBimapas(tileDataDificultad);
		
		cargarMapa("recursos/plano_1_penetracion.png");
		generarMapa();
		montarBimapas(tileDataPenetracion);
		
		

		
		
		
	}
	
	
	private void cargarMapa(String ruta) {
		
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
	
	
	private void generarMapa(){
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
	
}
