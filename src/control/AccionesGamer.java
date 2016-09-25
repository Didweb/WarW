package control;

import tropas.Tanque;

public class AccionesGamer {

	private Tanque[] tanquesPlay;
	private int scrollX;
	private int scrollY;

	public AccionesGamer(Tanque[] tanquesPlay, int scrollX, int scrollY){
		this.tanquesPlay = tanquesPlay;
		this.scrollX = scrollX;
		this.scrollY = scrollY;
	}
	
	public void seleccionarTanuqes(int eventX, int eventY){
		
		int baseAlto = tanquesPlay[0].getDatsSel()[1];
		
		int newCol=0;
		int ncol=1;
		int rondas=0;
		int baseRondas = (baseAlto+60);
		int saltosy=0;
		
		for (int xt=0;xt<tanquesPlay.length-1;xt++){
			
			
			if (rondas==3){
				rondas=0;
				
				if(ncol==1){
					newCol=100;
					} else {
					newCol=100*ncol;
					}
				ncol++;
				
				baseRondas=(baseAlto+60);
			}
			
			
			 int x=tanquesPlay[xt].getDatsSel()[0]+newCol;

			 int y=baseRondas;
			 int xanc=x+tanquesPlay[xt].getDatsSel()[2];
			 int yalt=y+tanquesPlay[xt].getDatsSel()[3];
			 rondas++;
				saltosy=tanquesPlay[xt].getDatsSel()[4];
				baseRondas+=saltosy;
			 
			 
			  
			 if(eventX > x && eventX < xanc
				 && eventY > y && eventY < yalt){
			   
				 tanquesPlay[xt].setSelccionado(true);
			 } else {
				 
				 
				 if (tanquesPlay[xt].isSelccionado() == true 
					 && eventY < tanquesPlay[xt].getDatsSel()[1]){
					 
					 // Durante la preparación de  la partida.
					 // Determinamos que tipo de acción debe hacer segun la actitud del tanque.
					 
					 if(tanquesPlay[xt].getActitud()==7){
					 tanquesPlay[xt].setPosEnTablero(eventX, eventY,scrollX,scrollY);
					 }
					 
				 } else {
					 tanquesPlay[xt].setSelccionado(false); 
				 }
				 
			 }
			
			
		}
		
	}
	
	
	public void cierraContextoT(){
		
		for (int xt=0;xt<tanquesPlay.length-1;xt++){
			
			if(tanquesPlay[xt].isMenuContextual() && tanquesPlay[xt].isSelccionado()){
				tanquesPlay[xt].setMenuContextual(false);
			}
		}
	}
	
	
	public void menuContextoT(){
		
		for (int xt=0;xt<tanquesPlay.length-1;xt++){
			
			//System.out.println(xt);
			if (tanquesPlay[xt].isSelccionado()){
				
				if(tanquesPlay[xt].isMenuContextual()){
					tanquesPlay[xt].setMenuContextual(false);
				} else {
					
					tanquesPlay[xt].setMenuContextual(true);
					
				}
				
			}
			
		}
	}
	
	
	
}
