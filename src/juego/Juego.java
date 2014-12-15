/**
 * 
 */
package juego;

import tuberias.vista.Pantalla;

/**
 * @author juan
 *
 */
public class Juego {

	/**
	 * 
	 */
	public Juego() {
		// TODO Apéndice de constructor generado automáticamente
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Partida partida = new Partida();
		Pantalla pantalla = new Pantalla(partida);
		partida.setPantalla(pantalla);
		
		
		
		//pantalla.abrirDialogo("hola", "mudo");

	}

}
