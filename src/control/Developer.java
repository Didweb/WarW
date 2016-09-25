package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tropas.Tanque;

public class Developer {

	
	private int[] valueSizes;

	public Developer(int[] valueSizes){
		
		this.valueSizes = valueSizes;
		
	}
	
    private boolean devActivo=true;

    public boolean isDevActivo() {
        return devActivo;
    }

    public void setDevActivo() {
        if(devActivo){
            this.devActivo = false; 
        } else {
            this.devActivo = true; 
        }
	
    }
    
    
    
    public void developerMapa(Graphics g,int xiT, 
			int xlogica, 
			int ylogica,
			int xcel,
			int ycel,
			int scrollX,
			int scrollY,
			int anchoTile,
			int altoTile){


			Font fuenteDev=new Font("Monospaced", Font.PLAIN, 9);
			g.setFont(fuenteDev);
			if(xiT >0 && xiT%2==0){
			g.setColor(Color.blue);
			} else {
			g.setColor(Color.DARK_GRAY);
			}
			
			g.drawString(xlogica+","+ylogica, xcel-scrollX+10, ycel-(scrollY-(altoTile*2))+10);
			g.drawString(xcel-scrollX+","+(ycel-(scrollY-(altoTile*2))), xcel-scrollX+20, ycel-(scrollY-(altoTile*2))+20 );
			g.drawRect(xcel-scrollX, ycel-(scrollY-(altoTile*2)), anchoTile, altoTile);
			
			
			}
    
    
public void developerSet(Graphics g, Controles controles, int scrollX, int scrollY, Tanque[] play){
		
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
		int actitud;
		for(int x=0; x<play.length-1; x++){
			g.setColor(Color.GRAY);
			g.drawString("  X  -   Y   | xL - yL", 420, 15);
			if(play[x].isSelccionado()){
				g.setColor(Color.YELLOW);
			} else { g.setColor(Color.GRAY); }
			
			g.drawString("Mod."+play[x].getId()+" "+play[x].getModelo(), 340, 17+espa);
			
			g.setColor(Color.WHITE);
			g.drawString(" "+play[x].getPosicionX()+" - "+play[x].getPosicionY(), 420, 17+espa);
			g.drawString("| "+play[x].getPosicionXLogica()+" - "+play[x].getPosicionYLogica(), 510, 17+espa);
			
			
			actitud = play[x].getActitud();
			g.drawString("| "+play[x].getActictudLista()[actitud], 610, 17+espa);
			espa+=17;
		}
		
		
	}
    
}
