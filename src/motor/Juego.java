package motor;

import graficos.Tiles;

public class Juego {

	private static Tiles tiles;
	
	public static void main(String[] args) {
		
		tiles = new Tiles();
		tiles.iniciarMapeo();
		tiles.iniciarCoor();
		

	}

}
