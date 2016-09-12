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
	private BufferedImage imagen, recorte;
	

	
	
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
	
	
	public void pantallaNivel() {
		
		 File f = new File("recursos/HojaSpriteIso_Mapa1.gif");
         try {
			imagen = ImageIO.read(f);
		} catch (IOException e) {

			e.printStackTrace();
		}
         
		
     
	
		for(int x=0; x<listaData.length; x++){
			for(int y=0; y<listaData.length; y++){
				
				//System.out.println("Valor "+x+" "+y+" = "+listaData[x][y]);
			
				//System.out.println("x y y :  "+x+" - "+y+" "+listaData[x][y]+" => ale: "+ale);
				
				//desgranarHoja(listaData[x][y],ale);
				recX = listaData[x][y]*anchoTil;
				System.out.println("recX - recY :  "+recX+" --> "+listaData[x][y]+" * "+anchoTil);
				//System.out.println("W y H : "+imagen.getWidth()+" "+imagen.getHeight()+" x+ W: "+(64+recX)+" y+ H: "+(64+recY));
				
				 recorte = (imagen.getSubimage(recX, recY, anchoTil, altoTil));
		            
				imagenes[x][y] = recorte; 
				
				
			}
			
		}
		
		
		
		
	}

	
	public int[][] getListaData() {
	    return listaData;
	}


	
}
