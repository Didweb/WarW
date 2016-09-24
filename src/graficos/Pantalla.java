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
		



		
		for(int xiT=0; xiT < imgTapete.length; xiT++){
			
			
			xcel = imgTapete[xiT][1];
			ycel = imgTapete[xiT][2];
			
			xlogica = hojaNivel.getCoordLogicas()[xiT][0];
			ylogica = hojaNivel.getCoordLogicas()[xiT][1];
	
			
			g.drawImage(hojaNivel.getImagenes()[xiT][xlogica][ylogica], xcel-scrollX, ycel-(scrollY-(altoTile*2)), this);
			//g.drawString(xlogica+","+ylogica, xcel-scrollX, ycel-(scrollY-(altoTile*2)));
			g.drawString(xcel-scrollX+","+(ycel-(scrollY-(altoTile*2))), xcel-scrollX+15, ycel-(scrollY-(altoTile*2))+15 );
		}
		
		
		menuGame(g);
		
		
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

	
	
	public void tanquesTapete(Graphics g){
		
		for(int xiT=0; xiT < imgTapete.length; xiT++){
			
			
			xcel = imgTapete[xiT][1];
			ycel = imgTapete[xiT][2];
			
			xlogica = hojaNivel.getCoordLogicas()[xiT][0];
			ylogica = hojaNivel.getCoordLogicas()[xiT][1];
	
			g.fillRect(xcel-scrollX, ycel-(scrollY-(altoTile*2)), anchoTile, altoTile);
			

		}
		
	}
	
	
	public void menuGame(Graphics g){
		
		int baseAlto = play[0].getDatsSel()[1]; //valueSizes[1]-(valueSizes[1]/6);
		g.setColor(Color.BLACK);
		g.fillRect(0, baseAlto, valueSizes[0], valueSizes[1]/6);
		
		g.setColor(Color.GRAY);
		g.drawString(""+play[0].getBando(), 10, baseAlto+15);
		
		
		int newCol=0;
		int ncol=1;
		int rondas=0;
		int baseRondas = (baseAlto+30);
		int saltosy=0;
		
		for(int x=0; x<play.length-1; x++){
			
			
			
			if(play[x].isEstaVivo()){
				
				g.setColor(Color.GREEN);
				if(play[x].isSelccionado()){
					g.setColor(Color.WHITE); 
					}
				} else {
					g.setColor(Color.RED);
					}
				
				if (rondas==3){
					rondas=0;
					
					if(ncol==1){
						newCol=100;
					} else {
						newCol=100*ncol;
						}
					ncol++;
					
					baseRondas=(baseAlto+30);
				}
				
				g.drawRect(play[x].getDatsSel()[0]+newCol, 
						baseRondas, // play[x].getDatsSel()[1]-play[x].getDatsSel()[3], 
						play[x].getDatsSel()[2], 
						play[x].getDatsSel()[3]);
				rondas++;
				saltosy=play[x].getDatsSel()[4];
				baseRondas+=saltosy;
				
			}
			
		
		
	}
	
	
	public void developerSet(Graphics g){
		
		Font fuenteDev=new Font("Monospaced", Font.PLAIN, 12);
        
		
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, valueSizes[0], 100);
		g.setColor(Color.GRAY);
		g.setFont(fuenteDev);
		
		// Controles basicos
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
		
		// controles dinamicos de posicion
		g.setColor(Color.GRAY);
		g.drawString("scrollX", 220, 15);
		g.drawString("scrollY", 220, 30);
		g.drawString("Raton", 220, 45);
		
		g.setColor(Color.WHITE);
		g.drawString(""+scrollX, 280, 15);
		g.drawString(""+scrollY, 280, 30);
		
		
		g.drawString(controles.getActualRatonX()+" . "+controles.getActualRatonY(), 280, 45);
		
		// controles tanques
		int espa = 15;
		for(int x=0; x<play.length-1; x++){
			g.setColor(Color.GRAY);
			g.drawString("    X  -   Y   | xL - yL", 420, 15);
			if(play[x].isSelccionado()){
				g.setColor(Color.YELLOW);
			} else { g.setColor(Color.GRAY); }
			
			g.drawString("Mod."+play[x].getId()+" "+play[x].getModelo(), 340, 17+espa);
			
			g.setColor(Color.WHITE);
			g.drawString(" "+play[x].getPosicionX()+" - "+play[x].getPosicionY(), 420, 17+espa);
			g.drawString("| "+play[x].getPosicionXLogica()+" - "+play[x].getPosicionYLogica(), 510, 17+espa);
			
			espa+=17;
		}
		
		
	}
	
}
