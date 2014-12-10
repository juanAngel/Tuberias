/**
 * 
 */
package entidades;

import juego.Entidad;

/**
 * @author juan
 *
 */
public abstract class Bomba extends Entidad {
	private int retardo = 0;
	private int turnoActual = 0;
	
	public Bomba() {
		this(0);
	}
	public  Bomba(int retardo) {
		this.retardo = retardo;
	}
	/**
	 * @return el retardo
	 */
	public int getRetardo() {
		return retardo;
	}
	/**
	 * @param retardo el retardo a establecer
	 */
	public void setRetardo(int retardo) {
		this.retardo = retardo;
	}
	@Override
	public void turno() {
		// TODO Apéndice de método generado automáticamente
		turnoActual = (turnoActual+1)%retardo;
		if (turnoActual == 0) {
			turnoBomba();
		}
	}
	protected abstract void turnoBomba();

}
