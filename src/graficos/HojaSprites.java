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
		
		 File f = new File("recursos/HojaSprites_nivel1.gif");
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
				
				desgranarHoja(listaData[x][y],ale);
				
				//System.out.println("recX - recY :  "+recX+" - "+recY);
				//System.out.println("W y H : "+imagen.getWidth()+" "+imagen.getHeight()+" x+ W: "+(64+recX)+" y+ H: "+(64+recY));
				
				 recorte = (imagen.getSubimage(recX, recY, 64, 64));
		            
				imagenes[x][y] = recorte; 
				
				
			}
			
		}
		
		
		
		
	}

	
	private  void desgranarHoja( int valor,int ale){
		
		
		switch(valor){
		
		case 0:
			recX=64*ale;
			recY=0;
			break;
			
		case 1:
			recX=192;
			recY=64;
			break;
			
		case 2:
			recX=256;
			recY=64;
			break;
			
		case 3:
			recX=128;
			recY=64;
			break;
			
		case 4:
			recX=0;
			recY=64;
			break;	
			
		case 5:
			recX=64;
			recY=64;
			break;	
			
		case 6:
			recX=256;
			recY=0;
			break;	
			default:
				recX=0;
				recY=0;
			
		}
		

	}
}
