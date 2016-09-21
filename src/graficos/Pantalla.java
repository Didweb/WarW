package graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.Controles;
import control.Developer;
import tropas.Tanque;



public class Pantalla extends JPanel {

	/**
	 * 
	 */
    
    
	private static final long serialVersionUID = 1L;

	
	
	
	
	
	private int scrollX;
	private int scrollY;
	
	private int relativaX;
	private int relativaY;
	
	
	private int anchoTile;
	private int altoTile;
	
	private int anchoV;
	private int altoV;
	
	
	private Tanque[] play;
	private Tanque[] enemigo;
	private Controles controles;
	private Developer developer;
	private TileData tiles;
	private HojaSprites hojaNivel;
	
	
	
	private int[][] imgTapete;
	private int [][] coordLogicasIso;

	
	public Pantalla(int ANCHO_VENTANA,int ALTO_VENTANA, int anchoTile, int altoTile, HojaSprites hojaNivel) {
		
		
		this.hojaNivel = hojaNivel;
		hojaNivel.pantallaNivel();
		imgTapete = hojaNivel.getImgTapete();
		coordLogicasIso = hojaNivel.getCoordLogicas();
		
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

	public void actores(Tanque[] play, Tanque[] enemigo,Controles controles, Developer developer, TileData tiles){
	    
	    this.play = play;
	    this.enemigo = enemigo;
	    this.controles = controles;
	    this.developer = developer;
	    this.tiles = tiles;
	    
	    
	}
	
	public void paint(Graphics g){
		
	    BufferedImage tan_amigo = null;
	    BufferedImage tan_enemigo = null;
	    
	    BufferedImage img_amigos = null;
	    BufferedImage img_icono_tanque = null;
		
		
	    
	    
		try {
		    img_amigos = ImageIO.read(new File("recursos/sovieticos.gif"));
		    img_icono_tanque = ImageIO.read(new File("recursos/Tank-T-34_mini.gif"));
		    tan_amigo = ImageIO.read(new File("recursos/t34.gif"));
		    tan_enemigo = ImageIO.read(new File("recursos/tanque_enemigo.gif"));
		    
		    
		   
		    
		} catch (IOException e) {
		}
	
		
		
		int xcel;
		int ycel;
		int xlogica;
		int ylogica;
		
		
		for(int xiT=0;xiT<imgTapete.length;xiT++){
			
			
			xcel = imgTapete[xiT][1];
			ycel = imgTapete[xiT][2];
			
			xlogica = coordLogicasIso[xiT][0];
			ylogica = coordLogicasIso[xiT][1];
	
			
			g.drawImage(hojaNivel.getImagenes()[xiT][xlogica][ylogica], xcel-scrollX, ycel-(scrollY-(altoTile*2)), this);
			//g.drawRect(xcel-scrollX, ycel-(scrollY-(altoTile*2)), 64, 32);
			//g.drawString(xlogica+"|"+ylogica+" ["+xiT+"] ", xcel-scrollX+10, ycel-(scrollY-(altoTile*2))+15);
			
		
		
		}
		
		
		
		
		int altoCuadro = 150;
		int movX;
		int movY;
		g.setFont(new Font("Arial", Font.PLAIN, 12));
		g.setColor(Color.BLUE);
		for(Tanque playT: play){
		    
		    movX=playT.getPosicionX();
		    movY=playT.getPosicionY();
		    controles.getPosRelativaTanque(scrollX,scrollY);
		    
		    relativaX = (movX-movY)*(anchoTile/2)-scrollX;
		    relativaY = (movX+movY)*(altoTile/2)-scrollY;	
		   
		    
		    if (playT.isSelccionado()){
			g.setColor(Color.GREEN);
			g.drawOval(relativaX-8, relativaY, 80, 40);
			
			
			
		    }
		    
		    if (playT.isEnMovimineto()==true &&  (playT.getDestinoX()!= movX || playT.getDestinoY()!= movY)){
			g.setColor(Color.RED);
			g.fillOval(relativaX, relativaY, 20, 15);
		    }
			
			
		  
		  	g.drawImage(tan_amigo, relativaX, relativaY-25, this);
		   
			if(developer.isDevActivo()){
			    g.setColor(Color.RED);
			    
			    g.drawString("Tdif: "+playT.isSelccionado(),relativaX+60, relativaY+10);
			    g.drawString("Tpen: "+playT.isSelccionado(),relativaX+60, relativaY+20);
			    
			    int peligro=tiles.buscaCelda(relativaX+60,relativaY+30,tiles.getTileDataPeligro());
			   
			    g.drawString("Tpel: "+peligro,relativaX+60 , relativaY+30);
			    g.drawString("Des : "+playT.getDestinoX()+" "+playT.getDestinoY(),relativaX+60, relativaY+40);
			    g.drawString("Sel: "+playT.isSelccionado(),relativaX+60, relativaY+50);
			    g.drawString("Mov: "+playT.isEnMovimineto(),relativaX+60,relativaY+60);
			    
			    
			    g.drawString("x: "+(playT.getPosicionX())+" y:"+(playT.getPosicionY()), relativaX, relativaY);
			
			    
			}
		    
		      
		    
		}

		
		for(Tanque enemigoT: enemigo){
		    
		   movX=enemigoT.getPosicionX();
		   movY=enemigoT.getPosicionY();
		    
		    relativaX = (movX-movY)*(anchoTile/2)-scrollX;
		    relativaY = (movX+movY)*(altoTile/2)-scrollY;
		    
		    g.drawImage(tan_enemigo, relativaX, relativaY, this);  
		}
		
		
		
		
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
		
		g.drawString("Deposito Combustible", 103+desplaza, altoV-altoCuadro+84);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(103+desplaza, altoV-altoCuadro+48, 100, 3);
		
		
		g.fillRect(103+desplaza, altoV-altoCuadro+68, 100, 3);
		
		g.setColor(Color.GREEN);
		g.fillRect(103+desplaza, altoV-altoCuadro+48, playT.getVida(), 3);
		
		
		g.fillRect(103+desplaza, altoV-altoCuadro+68, playT.getMunicion(), 3);
		
		g.fillRect(103+desplaza, altoV-altoCuadro+88, playT.getDepoCombustible(), 3);
		
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
		if(developer.isDevActivo()){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, anchoV, 40);
		g.setColor(Color.WHITE);
		g.drawString("Raton x: "+controles.getActualRatonX()+" y:"+controles.getActualRatonY(), 20,5);
		
		int desplaya=0;
		for(Tanque playT: play){
		    g.drawString("Des : x "+playT.getDestinoX()+"  y "+playT.getDestinoY(),20, 15+desplaya);
		    desplaya+=10;
		}
		
		}
	}

	public int getScrollX() {
	    return scrollX;
	}

	public int getScrollY() {
	    return scrollY;
	}
	
	

	
}
