package graficos;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import tropas.Tanque;



public class Pantalla extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private HojaSprites hojaNivel;
	
	
	private int scrollX;
	private int scrollY;
	
	private int relativaX;
	private int relativaY;
	
	
	private int anchoTile;
	private int altoTile;
	
	private Tanque play;
	private Tanque enemigo;

	
	public Pantalla(int ANCHO_VENTANA,int ALTO_VENTANA, int anchoTile, int altoTile) {
		
		hojaNivel = new HojaSprites(66,32);
		hojaNivel.pantallaNivel();
		
		scrollX = ANCHO_VENTANA/2;
		scrollY = ALTO_VENTANA/2;
		
		
		this.anchoTile = anchoTile;
		this.altoTile = altoTile;
		
	
	}
	
	public void actualizar(int x, int y){
		scrollX = x;
		scrollY = y;

		
	}
	
	public void update(Graphics g){
	    paint(g);        
	    
	}

	public void actores(Tanque play, Tanque enemigo){
	    
	    this.play = play;
	    this.enemigo = enemigo;
	    
	    
	    
	}
	
	public void paint(Graphics g){
		
		  
		
		for(int x=0;x<hojaNivel.getImagenes().length;x++){
			
			
			
			for(int y=0;y<hojaNivel.getImagenes().length;y++){

				
				relativaX = (x-y)*(anchoTile/2)-scrollX;
				relativaY = (x+y)*(altoTile/2)-scrollY;			
				
				
				
				g.drawImage(hojaNivel.getImagenes()[y][x], relativaX, relativaY, this);
					
					//g.setFont(new Font("Arial", Font.PLAIN, 9));
					//g.setColor(Color.BLACK);
					//g.drawString(" T: "+anchoVTils,relativaX,(relativaY)+10);
					//g.drawString(" P: "+anchoVPix,relativaX,(relativaY)+25);
					//g.drawString(" xT: "+(64*x),relativaX,(relativaY)+40);
					//g.drawString(" yT: "+(64*y)+"",relativaX,(relativaY)+50);
					//g.drawString(" Sx: "+scrollX+" Sy:"+scrollY,relativaX,(relativaY)+60);
					//g.drawRect(relativaX, relativaY, 64, 32);
					
					
					
				
			}
			
		}
		g.setColor(Color.BLUE);
		g.fillRect(play.getPosicionX()-scrollX, play.getPosicionY()-scrollY, 60, 60);
		
		g.setColor(Color.RED);
		g.fillRect(enemigo.getPosicionX()-scrollX, enemigo.getPosicionY()-scrollY, 60, 60);
		
	}
}
