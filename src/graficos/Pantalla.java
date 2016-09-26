package graficos;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import control.Controles;
import control.Developer;
import menus.MenuGamer;
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
				
				developer.developerMapa(g, xiT, xlogica,   ylogica, xcel, ycel,  scrollX, scrollY, anchoTile, altoTile,tiles,play);
				}
			
		}
		
		
		menuGamer.menuGamerBatalla(g);
		
		
		if(developer.isDevActivo()){
			developer.developerSet(g, controles, scrollX , scrollY, play);
			}
		
		
		tanquesTapete(g);
		
		
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
	
			for(int t=0;t<play.length-1;t++){
				
				
				if(tiles.getTileDataPenetracion()[ylogica][xlogica]==0){
					play[t].setColocado(false);
					//g.setColor(Color.RED);
					play[t].errorT(g);
				} else {g.setColor(Color.BLACK);
					play[t].setColocado(true);}
				
				
				
				if(play[t].getPosicionXLogica()>0 
						&& play[t].getPosicionYLogica()>0){
					
					if(play[t].getPosicionXLogica()==xlogica && play[t].getPosicionYLogica()==ylogica){
						
						
						g.drawString("Pen:"+tiles.getTileDataPenetracion()[ylogica][xlogica]+"("+xlogica+","+ylogica+")", xcel-scrollX+15, ycel-(scrollY-(altoTile*2)));
						g.drawString(""+play[t].getId(), xcel-scrollX, ycel-(scrollY-(altoTile*2)));	
						g.fillRect(xcel-scrollX, ycel-(scrollY-(altoTile*2)), anchoTile, altoTile);	
						
						
						if(play[t].isMenuContextual()){
							
							g.setColor(Color.BLACK);
							g.fillRoundRect(xcel-scrollX+80,ycel-(scrollY-(altoTile*2)) , 180, 200, 10, 10);
							
							int sal = 0;
							
							for (int o=0;o<play[t].getActictudLista().length;o++){
								
								g.setColor(Color.GRAY);
								
								if(o == play[t].getActitud()){
									g.setColor(Color.RED);
									} else {g.setColor(Color.GRAY);}
								
								g.drawString(o+". "+play[t].getActictudLista()[o], (xcel-scrollX+80)+5, (ycel-(scrollY-(altoTile*2)))+10+sal);
								sal+=15;
							}
						}
					
					}	
				}
				

				
			}
			
			

		}
		
	}
	
	
	
	
	
	
	
	
	
}
