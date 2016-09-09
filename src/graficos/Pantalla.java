package graficos;


import java.awt.Dimension;
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
	public Pantalla() {
		
		hojaNivel = new HojaSprites();
		hojaNivel.pantallaNivel();
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		
		
		
		for(int x=0;x<32;x++){
			
			for(int y=0;y<32;y++){
				
				if(x+y>0){
				g.drawImage(hojaNivel.getImagenes()[x], 0, 0, this);
				//copyArea(int x, int y, int width, int height, int dx, int dy)
				g.copyArea(0, 0, 64, 64, 64*x, 64*y);
				}
			}
			
		}
		
	}
}
