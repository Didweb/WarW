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
import motor.Niveles;
import tropas.Tanque;



public class Pantalla extends JPanel {

	
    
	private static final long serialVersionUID = 1L;

	
	
	
	
	
	private int scrollX;
	private int scrollY;
	
	private int relativaX;
	private int relativaY;
	
	
	private int anchoTile;
	private int altoTile;
	

	
	
	private Tanque[] play;
	private Tanque[] enemigo;
	private Controles controles;
	private Developer developer;
	private TileData tiles;
	private HojaSprites hojaNivel;
	
	
	
	
	private int [][] coordLogicasIso;

	private int[] valueSizes;






	private int[][] imgTapete;
	
	public Pantalla(int[] valueSizes, HojaSprites hojaNivel) {
		
		this.valueSizes = valueSizes;
		
		scrollX = valueSizes[4];
		scrollY = valueSizes[5];
		
		
		this.anchoTile = valueSizes[2];
		this.altoTile = valueSizes[3];
		
	
	}
	
	public void actualizar(int x, int y){
		scrollX = x;
		scrollY = y;

		
	}
	
	public void update(Graphics g){
	    paint(g);        
	    
	}

	public void actores(Niveles elnivel, Controles controles, Developer developer){
	    
		
		
	    this.play = elnivel.getPlayers();
	    this.enemigo = elnivel.getEnemigos();
	    this.controles = controles;
	    this.developer = developer;
	    this.tiles = elnivel.getPlanoData();
	    this.imgTapete = elnivel.getImgTapete();
	    this.hojaNivel = elnivel.getHojaNivel();
	    
	}
	
	public void paint(Graphics g){
		

		int xcel;
		int ycel;
		int xlogica;
		int ylogica;

		System.out.println(imgTapete[0][1]+"|"+imgTapete[0][2]);
		for(int xiT=0; xiT < imgTapete.length; xiT++){
			
			
			xcel = imgTapete[xiT][1];
			ycel = imgTapete[xiT][2];
			
			xlogica = hojaNivel.getCoordLogicas()[xiT][0];
			ylogica = hojaNivel.getCoordLogicas()[xiT][1];
	
			
			g.drawImage(hojaNivel.getImagenes()[xiT][xlogica][ylogica], xcel-scrollX, ycel-(scrollY-(altoTile*2)), this);

		}
		
		
		
		if(developer.isDevActivo()){
			developerSet(g);
			}
		
		
		
	}

	public int getScrollX() {
	    return scrollX;
	}

	public int getScrollY() {
	    return scrollY;
	}
	
	
	public void developerSet(Graphics g){
		
		Font fuenteDev=new Font("Monospaced", Font.PLAIN, 12);
        
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, valueSizes[0], 100);
		g.setColor(Color.GRAY);
		g.setFont(fuenteDev);
		
		g.drawString("ANCHO_VENTANA", 10, 15);
		g.drawString("ALTO_VENTANA", 10, 30);
		g.drawString("ANCHO_TILE", 10, 45);
		g.drawString("ALTO_TILE", 10, 60);
		g.drawString("CENTRO_ANCHO_VENTANA", 10, 75);
		g.drawString("CENTRO_ALTO_VENTANA", 10, 90);
		
		
		g.setColor(Color.WHITE);
		g.drawString(""+valueSizes[0], 110, 15);
		g.drawString(""+valueSizes[1], 110, 30);
		g.drawString(""+valueSizes[2], 110, 45);
		g.drawString(""+valueSizes[3], 110, 60);
		g.drawString(""+valueSizes[4], 160, 75);
		g.drawString(""+valueSizes[5], 160, 90);

		
		
		g.setColor(Color.BLACK);
		g.drawString(valueSizes[4]+"."+valueSizes[5], valueSizes[4], valueSizes[5]);
	}
	
}
