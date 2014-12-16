/**
 * 
 */
package entidades;

import estructura.Llave;

/**
 * Esta clase representa a los enemigos locos del juego.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */
public class EnemigoLoco extends Enemigo {

	/**
	 * 
	 */
	public EnemigoLoco() {}

	/* (sin Javadoc)
	 * @see entidades.Enemigo#movimiento()
	 */
	@Override
	protected void movimiento() {
		moverAleatorio();
	}

	/* (sin Javadoc)
	 * @see entidades.Enemigo#accion()
	 */
	@Override
	protected void accion() {
		if (getCelda() instanceof Llave) {
			Llave llave = (Llave) getCelda();
			llave.conmutarLlave();
		}
	}

	@Override
	public String getImagen() {
		// TODO Apéndice de método generado automáticamente
		return "enemigo-azul";
	}

}
