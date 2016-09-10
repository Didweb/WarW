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
	
	private int relativaX;
	private int relativaY;
	
	private int MaxY;
	private int MaxX;
	
	public Pantalla(int ANCHO_VENTANA,int ALTO_VENTANA) {
		
		hojaNivel = new HojaSprites();
		hojaNivel.pantallaNivel();
		
		anchoVTils = ANCHO_VENTANA;
		altoVTils  = ALTO_VENTANA;
		anchoVPix = ANCHO_VENTANA*64;
		altoVPix  = ALTO_VENTANA*64;
		scrollX = ANCHO_VENTANA/2;
		scrollY = ALTO_VENTANA/2;
		int relativaX = 0;
		int relativaY = 0;
		
		
		
		
	}
	
	public void actualizar(int x, int y,boolean centrado){
		
		if (centrado){
			scrollX = x;
			scrollY = y;
		} else {
			scrollX += x;
			scrollY += y;
					
		}
		
	}
	


	public void paintComponent(Graphics g){
		super.paintComponent(g);

		
		for(int x=0;x<hojaNivel.getImagenes().length;x++){
			
			
			
			for(int y=0;y<hojaNivel.getImagenes().length;y++){
				
				relativaX = (64*x)-scrollX;
				relativaY = (64*y)-scrollY;
				
				
				
				System.out.println("relaX: "+relativaX+" relaY: "+relativaY+" --->>>>>>>>>>>>>>>> "+hojaNivel.getImagenes().length);
				
				g.drawImage(hojaNivel.getImagenes()[y][x], relativaX, relativaY, this);
					
					//g.setFont(new Font("Arial", Font.PLAIN, 9));
					//g.setColor(Color.BLACK);
					//g.drawString(" T: "+anchoVTils,relativaX,(relativaY)+10);
					//g.drawString(" P: "+anchoVPix,relativaX,(relativaY)+25);
					//g.drawString(" xT: "+(64*x),relativaX,(relativaY)+40);
					//g.drawString(" yT: "+(64*y)+"",relativaX,(relativaY)+50);
					//g.drawString(" Sx: "+scrollX+" Sy:"+scrollY,relativaX,(relativaY)+60);
					//g.drawRect(relativaX, relativaY, 64, 64);
					
					
					
				
			}
			
		}
		
	}
}
