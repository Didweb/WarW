package graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import control.Controles;
import control.Developer;
import control.MenuGamer;
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


	private int xcel;
	private int ycel;
	private int xlogica;
	private int ylogica;




	private int[][] imgTapete;


	private MenuGamer menuGamer;
	
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
	    
	    this.menuGamer = new MenuGamer(valueSizes,play);
	    
	    
	}
	
	public void paint(Graphics g){
		



		
		for(int xiT=0; xiT < imgTapete.length; xiT++){
			
			
			xcel = imgTapete[xiT][1];
			ycel = imgTapete[xiT][2];
			
			xlogica = hojaNivel.getCoordLogicas()[xiT][0];
			ylogica = hojaNivel.getCoordLogicas()[xiT][1];
			
	        
			
			
			g.drawImage(hojaNivel.getImagenes()[xiT][xlogica][ylogica], xcel-scrollX, ycel-(scrollY-(altoTile*2)), this);
			
			

			if(developer.isDevActivo()){
				developer.developerMapa(g, xiT, xlogica,   ylogica, xcel, ycel,  scrollX, scrollY, anchoTile, altoTile);
				}
			
		}
		
		
		menuGamer.menuGamerBatalla(g);
		
		
		if(developer.isDevActivo()){
			developer.developerSet(g, controles, scrollX , scrollY, play);
			}
		
		
		
		
		
	}

	public int getScrollX() {
	    return scrollX;
	}

	public int getScrollY() {
	    return scrollY;
	}

	
	
	public void tanquesTapete(Graphics g){
		
		for(int xiT=0; xiT < imgTapete.length; xiT++){
			
			
			xcel = imgTapete[xiT][1];
			ycel = imgTapete[xiT][2];
			
			xlogica = hojaNivel.getCoordLogicas()[xiT][0];
			ylogica = hojaNivel.getCoordLogicas()[xiT][1];
	
			g.fillRect(xcel-scrollX, ycel-(scrollY-(altoTile*2)), anchoTile, altoTile);
			

		}
		
	}
	
	
	
	
	
	
	
	
	
}
