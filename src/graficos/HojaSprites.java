package graficos;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.ThreadLocalRandom;

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
	
	 
	public HojaSprites(){
		nivel = new TileImg();
		nivel.MapaImg();
		
		listaData = nivel.getTileDataNivel();
		
		imagenes =  new Image[listaData.length][listaData.length];
		
		
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
         
		
        int ale;
	
		for(int x=0; x<listaData.length; x++){
			for(int y=0; y<listaData.length; y++){
				
				//System.out.println("Valor "+x+" "+y+" = "+listaData[x][y]);
				ale = ThreadLocalRandom.current().nextInt(0, 3 + 1);
				//System.out.println("x y y :  "+x+" - "+y+" "+listaData[x][y]+" => ale: "+ale);
				
				//desgranarHoja(listaData[x][y],ale);
				recX = listaData[x][y]*nivel.getANCHO_TILE();
				//System.out.println("recX - recY :  "+recX+" - "+recY);
				//System.out.println("W y H : "+imagen.getWidth()+" "+imagen.getHeight()+" x+ W: "+(64+recX)+" y+ H: "+(64+recY));
				
				 recorte = (imagen.getSubimage(recX, recY, nivel.getANCHO_TILE(), nivel.getALTO_TILE()));
		            
				imagenes[x][y] = recorte; 
				
				
			}
			
		}
		
		
		
		
	}

	
	public int[][] getListaData() {
	    return listaData;
	}


	private  void desgranarHoja( int valor,int ale){
		
		
		switch(valor){
		
		case 0:
			recX=0;
			recY=0;
			break;
			
		case 1:
			recX=64;
			recY=0;
			break;
			
		case 2:
			recX=132;
			recY=0;
			break;
			
		case 3:
			recX=198;
			recY=0;
			break;
			
		case 4:
			recX=540;
			recY=0;
			break;	
			
		case 5:
			recX=648;
			recY=0;
			break;	
			
		case 6:
			recX=864;
			recY=0;
			break;	
		case 7:
			recX=864;
			recY=0;
			break;
		case 8:
			recX=972;
			recY=0;
			break;
			
		default:
			recX=0;
			recY=0;
			
		}
		

	}
}
