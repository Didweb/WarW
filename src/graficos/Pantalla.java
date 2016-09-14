package graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import control.Controles;
import control.Developer;
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
	
	private int anchoV;
	private int altoV;
	
	
	private Tanque[] play;
	private Tanque enemigo;
	private Controles controles;
	private Developer developer;

	
	public Pantalla(int ANCHO_VENTANA,int ALTO_VENTANA, int anchoTile, int altoTile) {
		
		hojaNivel = new HojaSprites(66,32);
		hojaNivel.pantallaNivel();
		
		scrollX = ANCHO_VENTANA/2;
		scrollY = ALTO_VENTANA/2;
		
		anchoV = ANCHO_VENTANA;
		altoV = ALTO_VENTANA;
		
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

	public void actores(Tanque[] play, Tanque enemigo,Controles controles, Developer developer){
	    
	    this.play = play;
	    this.enemigo = enemigo;
	    this.controles = controles;
	    this.developer = developer;
	    
	    
	    
	}
	
	public void paint(Graphics g){
		
	    BufferedImage tan_amigo = null;
	    BufferedImage tan_enemigo = null;
	    
	    BufferedImage img_amigos = null;
	    BufferedImage img_icono_tanque = null;
		
		
	    
	    
		try {
		    img_amigos = ImageIO.read(new File("recursos/sovieticos.gif"));
		    img_icono_tanque = ImageIO.read(new File("recursos/Tank-T-34_mini.gif"));
		    tan_amigo = ImageIO.read(new File("recursos/tanque_amigo.gif"));
		    tan_enemigo = ImageIO.read(new File("recursos/tanque_enemigo.gif"));
		    
		    
		   
		    
		} catch (IOException e) {
		}
		
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
		

		
		
		int altoCuadro = 150;
		
		g.setColor(Color.BLUE);
		for(Tanque playT: play){
		    
		    int movX=playT.getPosicionX();
		    int movY=playT.getPosicionY();
		    controles.getPosRelativaTanque(scrollX,scrollY);
		    
		    if (playT.isSelccionado()){
			g.setColor(Color.GREEN);
			g.drawOval((movX-10)-scrollX, (movY+25)-scrollY, 80, 40);
			
			
			
		    }
		    
		    if (playT.isEnMovimineto()==true &&  (playT.getDestinoX()!= movX || playT.getDestinoY()!= movY)){
			g.setColor(Color.RED);
			g.fillOval((playT.getDestinoX())-scrollX, (playT.getDestinoY())-scrollY, 20, 15);
		    }
			
			//g.fillRect(movX-scrollX, movY-scrollY, 60, 60); 
			g.drawImage(tan_amigo, movX-scrollX, movY-scrollY, this);
		   
			if(developer.isDevActivo()){
			    g.setColor(Color.BLACK);
			    g.drawString("Mov: "+playT.isEnMovimineto(),(movX + 60)-scrollX, (movY+60)-scrollY);
			    g.drawString("Sel: "+playT.isSelccionado(),(movX + 60)-scrollX, (movY+50)-scrollY);
			    g.drawString("Des : "+playT.getDestinoX()+" "+playT.getDestinoY(),(movX + 60)-scrollX, (movY+40)-scrollY);
			    g.drawString("x: "+(playT.getPosicionX())+" y:"+(playT.getPosicionY()), playT.getPosicionX()-scrollX, playT.getPosicionY()-scrollY);
			}
		    
		      
		    
		}

		
		
		
		
		g.drawImage(tan_enemigo, enemigo.getPosicionX()-scrollX, enemigo.getPosicionY()-scrollY, this);
		
		// Cuadro panel de control
		g.setColor(Color.BLACK);
		g.fillRect(0, altoV-altoCuadro, anchoV, altoCuadro);
		
		
		g.drawImage(img_amigos, 4, altoV-altoCuadro+4, this);
		    
	
		
		
		
		g.setFont(new Font("Arial", Font.PLAIN, 11)); 
		g.setColor(Color.gray);
		g.drawString(play[0].getBando(), 34, altoV-altoCuadro+15);
		
		int desplaza = 0;
		
		for(Tanque playT: play){
		
		if(playT.isEstaVivo()){
		    g.setColor(Color.GREEN);
		   
		} else {
		    g.setColor(Color.RED);
		}
		 g.fillOval(145+desplaza, altoV-altoCuadro+4, 5, 5);
		 
		

		g.setColor(Color.DARK_GRAY);
		
		g.setFont(new Font("Arial", Font.PLAIN, 12)); 
		g.drawString(playT.getModelo()+" - "+playT.getId(), 155+desplaza, altoV-altoCuadro+15);
		
		g.setFont(new Font("Arial", Font.PLAIN, 9));
		g.drawString("Vida", 103+desplaza, altoV-altoCuadro+44);
		
		g.drawString("Munición", 103+desplaza, altoV-altoCuadro+64);
		
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(103+desplaza, altoV-altoCuadro+48, 100, 3);
		
		
		g.fillRect(103+desplaza, altoV-altoCuadro+68, 100, 3);
		
		g.setColor(Color.GREEN);
		g.fillRect(103+desplaza, altoV-altoCuadro+48, playT.getVida(), 3);
		
		
		g.fillRect(103+desplaza, altoV-altoCuadro+68, playT.getMunicion(), 3);
		
		
		// logo tanque + cuadro deco datos
		g.setColor(Color.DARK_GRAY);
		g.fillRoundRect(100+desplaza, altoV-altoCuadro+2, 40, 18, 5, 5);
		
		if (playT.isSelccionado()) {
		    g.setColor(Color.GREEN);
		    
		}
		    
		g.drawRoundRect(100+desplaza, altoV-altoCuadro+2, 125, altoCuadro-35, 5, 5);
		g.drawImage(img_icono_tanque, 102+desplaza, altoV-altoCuadro+4, this);
		
		desplaza+=140;
		
//		if(playT.getId()==0){g.setColor(Color.RED);} else if(playT.getId()==1){g.setColor(Color.yellow);} else {g.setColor(Color.green);}
//		
//		g.fillRect(playT.getAreaSel()[0], playT.getAreaSel()[1], 120, 100);
//		System.out.println("Id "+playT.getId()+": "+playT.getAreaSel()[0]+" "+ playT.getAreaSel()[1]);
//		
		
		
		
		
		}
		
		
		
		
	
	
		
		
		// Developer
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, anchoV, 40);
		g.setColor(Color.WHITE);
		g.drawString("Raton x: "+controles.getRx()+" y:"+controles.getRy(), 20,15);
	}
	
	

	
}
