package control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import tropas.Tanque;

public class MenuGamer {

	
	private int[] valueSizes;
	private Tanque[] play;



	public MenuGamer(int[] valueSizes, Tanque[] play){
		
		
		this.valueSizes = valueSizes;
		this.play = play;
	}
	
	
	
	public void menuGamerBatalla(Graphics g){
		
		Font fuenteDev=new Font("Monospaced", Font.PLAIN, 12);
		
		g.setFont(fuenteDev);
		
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
}
