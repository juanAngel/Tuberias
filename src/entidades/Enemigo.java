package entidades;

/**
 * @author Juan Ángel Sánchez López - Tomas Gómez Castilla
 *
 */
public abstract class Enemigo extends EntidadViva {

	
	/** Mueve y realiza la Accion del enemigo
	 * @see entidades.EntidadViva#turnoViva()
	 */
	@Override
	protected void turnoViva() {
		movimiento();
		accion();
	}
	/**
	 * Realiza el movimiento del enemigo
	 */
	protected abstract void movimiento();

	/**
	 * Realiza la accion del Eemigo
	 */
	protected abstract void accion();
	

}
