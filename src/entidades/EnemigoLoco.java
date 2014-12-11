/**
 * 
 */
package entidades;

import estructura.Llave;

/**
 * @author juan
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

}
