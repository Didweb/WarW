package motor;

import graficos.HojaSprites;
import graficos.TileData;
import tropas.T34;
import tropas.Tanque;

public class Niveles {

	
	private int [] valueSizes;
	
	private int[][] coordLogicas;
	private int[][] coordLogicasPix;
	private int[][] coordLogicasIso;
	
	// N enemigos y tanques de cada partida.
	private int[] nPlayers = {5,4,5};
	private int[] nEnemigos = {3,4,5};
	
	private Tanque[] players;
	private Tanque[] enemigos;
	
	private HojaSprites hojaNivel;
	
	
	
	private TileData planoData;
	
	private int batalla;
	private int[][] imgTapete;
	
	
	public Niveles(int[] VALORES_SIZES){
		this.valueSizes = VALORES_SIZES;
	}
	
	public void iniciarNivel(int batalla){
		
		this.batalla = batalla;
		
		// Datas del plano
		planoData = new TileData(valueSizes,2048);
		planoData.iniciarMapeo();
		planoData.iniciarCoor();
		planoData.montarNivel();
		
		// hojas graficas tiles 
		hojaNivel = new HojaSprites(valueSizes,batalla,2048);
		hojaNivel.pantallaNivel(batalla);
		imgTapete = hojaNivel.getImgTapete();
		coordLogicasIso = hojaNivel.getCoordLogicas();
		
		
		// Creamos e iniciamos los tanques de la partida tipo Players
		players = new Tanque[nPlayers[batalla-1]+1];
		for(int tx=0; tx<nPlayers[batalla-1]; tx++){
		
			players[tx] = new T34("Sovietico",planoData,valueSizes);
			
			}
		
		
		// Creamos e iniciamos los tanques de la partida tipo Enemigos
		enemigos = new Tanque[nPlayers[batalla-1]];
		for(int tx=0; tx<nPlayers[batalla-1]; tx++){
			
			enemigos[tx] = new T34("Aleman",planoData,valueSizes);
			
			}
		

		
	}

	public HojaSprites getHojaNivel() {
		return hojaNivel;
	}

	public Tanque[] getPlayers() {
		return players;
	}

	public int[][] getImgTapete() {
		return imgTapete;
	}

	public Tanque[] getEnemigos() {
		return enemigos;
	}

	public TileData getPlanoData() {
		return planoData;
	}
	
	
}
