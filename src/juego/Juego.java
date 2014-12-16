/**
 * 
 */
package juego;

import tuberias.vista.Pantalla;

/**
 * Clase principal del juego
 * @author Juan Ángel Sánchez López  - Tomás Gómez Castilla 
 * 
 */
public class Juego {

	/**
	 * Constructor
	 */
	public Juego() {
	}

	/**
	 * Punto de entrada para empezar el juego
	 * @param args
	 */
	public static void main(String[] args) {
		Partida partida = new Partida();
		Pantalla pantalla = new Pantalla(partida);
		partida.setPantalla(pantalla);

	}

}
