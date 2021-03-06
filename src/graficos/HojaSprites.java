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
	 
	public HojaSprites(int[] valueSizes,int batalla,int ancho_mapa){
		nivel = new TileImg(valueSizes,ancho_mapa);
		nivel.MapaImg(batalla);
		
		imgTapete = nivel.getGuiaIso2D();
		
		listaData = nivel.getTileDataNivel();
		coordLogicasIso = nivel.getCoordLogicasIso();
		
		imagenes =  new Image[listaData.length][32][32];
		this.anchoTil = valueSizes[2];
		this.altoTil = valueSizes[3];
		
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
	
	
	
	public void pantallaNivel(int batalla) {
		// Imagen el mapeo del terreno
		 File f = new File("recursos/sprites/niveles/"+batalla+"/mapa_"+batalla+".gif");
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
