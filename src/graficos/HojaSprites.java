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
	private BufferedImage imagen, recorte;
	
	private int[][] imgTapete;

	
	private TileImg nivel;
	private int recX = 0;
	private int recY = 0;
	private int[][][] listaData;
	
	private int anchoTil;
	private int altoTil;
	private int[][] coordLogicasIso;
	 
	public HojaSprites(int anchoTil, int altoTil,int batalla){
		nivel = new TileImg();
		nivel.MapaImg(batalla);
		
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
    

    		int xisoM=0;
    		int yisoM=0;

			for(int ordenIso=0;ordenIso<listaData.length;ordenIso++){
				
				xisoM=coordLogicasIso[ordenIso][0];
	    		yisoM=coordLogicasIso[ordenIso][1];
					
				recX = listaData[ordenIso][xisoM][yisoM]*anchoTil;	

				recorte = (imagen.getSubimage(recX, recY, anchoTil, altoTil));
			            
				imagenes[ordenIso][xisoM][yisoM] = recorte; 

			}

	}	
	
	
	
	public int[][][] getListaData() {
	    return listaData;
	}


	
}
