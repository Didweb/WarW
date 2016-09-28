package control;


import graficos.Dijkstra;
import graficos.TileData;
import tropas.Tanque;

public class AccionesGamer {

	private Tanque[] tanquesPlay;
	private int scrollX;
	private int scrollY;
	
	private int ratonX;
	private int ratonY;
	private TileData tileData;

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
	
	
	
	public void cazaCoordenada(int ratonX, int ratonY, int scrollX, int scrollY, TileData tileData){
		
		this.ratonX = ratonX;
		this.ratonY = ratonY;
		this.tileData = tileData;
		
		int idT = -1;
		int actitudT = 1000;
		
		for(int x=0; x<tanquesPlay.length-1;x++){
			
			if(tanquesPlay[x].isSelccionado()){
				idT = tanquesPlay[x].getId();
				actitudT = tanquesPlay[idT].getActitud();
			}
		}
		
		if(idT>-1){
			switch (actitudT){
			
				case 5:
					if(tanquesPlay[idT].isSinDestino()){
						coorDestino(scrollX,scrollY);
						
						Dijkstra Dij = new Dijkstra(tileData.getTilesPorlado(), tileData.getBuscaLoY(),tileData.getBuscaLoX());
						Dij.iniTodo();
						Dij.crear(tileData.getTileDataPenetracion());
						
						
						System.out.println("CoorDesLogicas:"+tileData.getBuscaLoX()+" , "+tileData.getBuscaLoY());
						tanquesPlay[idT].setPlanoDestino(Dij.getMapaDi());
						
						
					}
					break;
				case 6:
					if(tanquesPlay[idT].isSinDestino()){
					// -------------- <<<>>>>> crear plano destino
						}
					break;
				default:
					break;
			
			}
		}
		
		
	}
	
	
	public void coorDestino(int scrollX, int scrollY){
		
		
		tileData.buscaLogicas(ratonX ,ratonY ,scrollX ,scrollY);
		int destLoX = tileData.getBuscaLoX();
		int destLoY = tileData.getBuscaLoY();
		
	}
	
}
