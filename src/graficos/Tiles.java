package graficos;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public  class Tiles {

	private final int ANCHO_MAPA = 2048;
	private final int ALTO_MAPA = 2048;
	private final int ANCHO_TILE = 64;
	
	private int[] planoInter;
	private int[] planoBruto;
	private int[][] tileData;
	
	private void iniciarMapeo(){
		
		int tilesPorlado = (ANCHO_MAPA*ALTO_MAPA)/(ANCHO_TILE*ANCHO_TILE);
		
		planoInter = new int[tilesPorlado*tilesPorlado];
		planoBruto = new int[tilesPorlado*tilesPorlado];
		tileData = new int[tilesPorlado][tilesPorlado];
		
		
	}
	
	private void cargarMapa(String ruta) {
		
		try {
			BufferedImage imagen = ImageIO.read(new FileInputStream(ruta));
			
			int ancho = imagen.getWidth();
			int alto = imagen.getHeight();
			
			imagen.getRGB(0, 0, ancho, alto, planoBruto, 0, ancho);
			
			} catch (IOException e) {
				System.out.println("Imagen no encontrada ");
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
