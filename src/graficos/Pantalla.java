package graficos;


import java.awt.Graphics;
import javax.swing.JPanel;



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
	

	
	public Pantalla(int ANCHO_VENTANA,int ALTO_VENTANA) {
		
		hojaNivel = new HojaSprites();
		hojaNivel.pantallaNivel();
		
		scrollX = ANCHO_VENTANA/2;
		scrollY = ALTO_VENTANA/2;
	
	}
	
	public void actualizar(int x, int y){
		scrollX = x;
		scrollY = y;
	}
	


	public void paintComponent(Graphics g){
		super.paintComponent(g);

		
		for(int x=0;x<hojaNivel.getImagenes().length;x++){
			
			
			
			for(int y=0;y<hojaNivel.getImagenes().length;y++){
				
				relativaX = (64*x)-scrollX;
				relativaY = (64*y)-scrollY;
				
				
				
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
