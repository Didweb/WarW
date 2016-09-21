package graficos;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HojaSprites  extends JPanel{


	/**
	 * 
	 */
    private static final long serialVersionUID = 2591034934263085790L;
	private Image[][][] imagenes;
	private int[] imagenes2;
	private BufferedImage imagen, recorte;
	private Image[] recorte2;
	
	private int[][] imgTapete;
	

	
	
	private TileImg nivel;
	private int recX = 0;
	private int recY = 0;
	private int[][][] listaData;
	
	private int anchoTil;
	private int altoTil;
	private int[][] coordLogicasIso;
	 
	public HojaSprites(int anchoTil, int altoTil){
		nivel = new TileImg();
		nivel.MapaImg();
		
		imgTapete = nivel.getGuiaIso2D();
		
		listaData = nivel.getTileDataNivel();
		coordLogicasIso = nivel.getCoordLogicasIso();
		
		imagenes =  new Image[listaData.length][32][32];
		this.anchoTil = anchoTil;
		this.altoTil = altoTil;
		
	}
	

	public int[][] getCoordLogicas() {
		return coordLogicasIso;
	}


	public int[][] getImgTapete() {
		return imgTapete;
	}


//	public Image[][] getImagenesxxx() {
//		return imagenesxxx;
//	}
	
	
	
	public Image[][][] getImagenes() {
		return imagenes;
	}
	
	public void pantallaNivel() {
		// Imagen el mapeo del terreno
		 File f = new File("recursos/HojaSpriteIso_Mapa1.gif");
    try {
			imagen = ImageIO.read(f);
		} catch (IOException e) {
		    e.printStackTrace();
		}
    
		/*
		 * Creamos un array con el numero de celda + coordenadas y un valor que es la imagen de
		 * cada tipo de terreno. El tipo de terreno se extrae del recorte.
		 */

		for (int pp=0; pp<coordLogicasIso.length;pp++){
			
			//System.out.println("coorLo ["+pp+"][0]->"+coordLogicas[pp][0]);
			//System.out.println("coorLo ["+pp+"][1]->"+coordLogicas[pp][1]);
		}
		
    		int xisoM=0;
    		int yisoM=0;
    		
    	
    	
    		
    		
			for(int ordenIso=0;ordenIso<listaData.length;ordenIso++){
				
				xisoM=coordLogicasIso[ordenIso][0];
	    		yisoM=coordLogicasIso[ordenIso][1];
					
				recX = listaData[ordenIso][xisoM][yisoM]*anchoTil;	
				
				System.out.println("["+ordenIso+"]["+xisoM+"]["+yisoM+"] ->"+listaData[ordenIso][xisoM][yisoM]+" * recX = "+recX);
				
				recorte = (imagen.getSubimage(recX, recY, anchoTil, altoTil));
			            
				imagenes[ordenIso][xisoM][yisoM] = recorte; 
					
				
			    
				
			    
			   
			}
			
		
		
	}	
	
	
//	public void pantallaNivelxxxx() {
//		
//		 File f = new File("recursos/HojaSpriteIso_Mapa1.gif");
//         try {
//			imagen = ImageIO.read(f);
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//         
//		
//     
//	
//		for(int x=0; x<listaData.length; x++){
//			for(int y=0; y<listaData.length; y++){
//				
//			    recX = listaData[x][y]*anchoTil;
//				
//			    recorte = (imagen.getSubimage(recX, recY, anchoTil, altoTil));
//		            
//			    imagenes[x][y] = recorte; 
//			}
//			
//		}
//		
//	}

	
	public int[][][] getListaData() {
	    return listaData;
	}


	
}
