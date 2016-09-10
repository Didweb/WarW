package graficos;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pantalla extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image imagen;
	private HojaSprites hojaNivel;
	
	private int scrollX;
	private int scrollY;
	private int anchoVTils;
	private int altoVTils;
	private int anchoVPix;
	private int altoVPix;
	
	private int InicioSpx;
	private int FinSpx;
	
	public Pantalla(int ANCHO_VENTANA,int ALTO_VENTANA) {
		
		hojaNivel = new HojaSprites();
		hojaNivel.pantallaNivel();
		anchoVTils = ANCHO_VENTANA;
		altoVTils  = ALTO_VENTANA;
		anchoVPix = ANCHO_VENTANA*64;
		altoVPix  = ALTO_VENTANA*64;
		scrollX = ANCHO_VENTANA/2;
		scrollY = ALTO_VENTANA/2;
		
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		InicioSpx = 0;
		FinSpx = 0;
		
		//plot (x, y) = ((ANCHO_TILE * x) – scroll.x, (ALTO_TILE * y) – scroll.y)
		int relativaX = 0;
		int relativaY = 0;
		for(int x=0;x<32;x++){
			
			for(int y=0;y<32;y++){
				
				//if(x+y>0){
				
				
				g.drawImage(hojaNivel.getImagenes()[x], 0, 0, this);
				//copyArea(int x, int y, int width, int height, int dx, int dy)
				
				relativaX = (64*x)-scrollX;
				relativaY = (64*y)-scrollY;
					
					//g.copyArea(0, 0, 64, 64, 64*x, 64*y);
					g.copyArea(0, 0, 64, 64, relativaX, relativaY);
					
					
					g.setFont(new Font("Arial", Font.PLAIN, 9));
					g.setColor(Color.BLACK);
					g.drawString(" T: "+anchoVTils,relativaX,(relativaY)+10);
					g.drawString(" P: "+anchoVPix,relativaX,(relativaY)+25);
					g.drawString(" xT: "+(64*x),relativaX,(relativaY)+40);
					g.drawString(" yT: "+(64*y)+"",relativaX,(relativaY)+50);
					g.drawString(" Sx: "+scrollX,relativaX,(relativaY)+60);
					g.drawRect(relativaX, relativaY, 64, 64);
					
					
					FinSpx+=64;
					InicioSpx+=64;
				
			}
			
		}
		
	}
}
