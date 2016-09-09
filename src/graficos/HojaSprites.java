package graficos;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HojaSprites  extends JPanel{
	private Image[] imagenes;
	private BufferedImage imagen, recorte;
	

	private Graphics g;
	
	private TileImg nivel;
	private int recX = 0;
	private int recY = 0;
	
	
	 
	public HojaSprites(){
		nivel = new TileImg();
		nivel.MapaImg();
		
	}
	
	public void pantallaNivel() {
		

		
		
		 File f = new File("recursos/HojaSprites_nivel1.png");
         try {
			imagen = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
        int largo=0;
        int ale;
		int[][] listaData = nivel.getTileDataNivel();
		
		imagenes =  new Image[listaData.length*listaData.length];
		for(int x=0; x<listaData.length; x++){
			
			for(int y=0; y<listaData[x].length; y++){
				
				//System.out.println("Valor "+x+" "+y+" = "+listaData[x][y]);
				ale = ThreadLocalRandom.current().nextInt(0, 3 + 1);
				//System.out.println("x y y :  "+x+" - "+y+" "+listaData[x][y]+" => ale: "+ale);
				
				desgranarHoja(listaData[x][y],ale);
				
				//System.out.println("recX y recY :  "+recX+" - "+recY);
				//System.out.println("W y H : "+imagen.getWidth()+" "+imagen.getHeight()+" x+ W: "+(64+recX)+" y+ H: "+(64+recY));
				
				 recorte = (imagen.getSubimage(recX, recY, 64, 64));
		            
				imagenes[largo] = recorte; 
				largo++;
				
			}
			
		}
		
		
		
	}
	


	public Image[] getImagenes() {
		return imagenes;
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
			
		}
		

	}
}
