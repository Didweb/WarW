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
	private Image[][] imagenes;
	private int[] imagenes2;
	private BufferedImage imagen, recorte;
	private Image[] recorte2;
	

	
	
	private TileImg nivel;
	private int recX = 0;
	private int recY = 0;
	private int[][] listaData;
	
	private int anchoTil;
	private int altoTil;
	 
	public HojaSprites(int anchoTil, int altoTil){
		nivel = new TileImg();
		nivel.MapaImg();
		
		listaData = nivel.getTileDataNivel();
		
		imagenes =  new Image[listaData.length][listaData.length];
		this.anchoTil = anchoTil;
		this.altoTil = altoTil;
		
	}
	

	public Image[][] getImagenes() {
		return imagenes;
	}
	
	
	
	public int[] getImagenes2() {
		return imagenes2;
	}
	
	public void pantallaNivel2() {
		
		 File f = new File("recursos/HojaSpriteIso_Mapa1.gif");
    try {
			imagen = ImageIO.read(f);
		} catch (IOException e) {
		    e.printStackTrace();
		}
    
		

	
		for(int x=0; x<listaData.length; x++){
			for(int y=0; y<listaData.length; y++){
				
			    recX = listaData[x][y]*anchoTil;
				
			    recorte = (imagen.getSubimage(recX, recY, anchoTil, altoTil));
		            
			    imagenes2[x] = x;  
			}
			
		}
		
	}	
	
	
	public void pantallaNivel() {
		
		 File f = new File("recursos/HojaSpriteIso_Mapa1.gif");
         try {
			imagen = ImageIO.read(f);
		} catch (IOException e) {
		    e.printStackTrace();
		}
         
		
     
	
		for(int x=0; x<listaData.length; x++){
			for(int y=0; y<listaData.length; y++){
				
			    recX = listaData[x][y]*anchoTil;
				
			    recorte = (imagen.getSubimage(recX, recY, anchoTil, altoTil));
		            
			    imagenes[x][y] = recorte; 
			}
			
		}
		
	}

	
	public int[][] getListaData() {
	    return listaData;
	}


	
}
