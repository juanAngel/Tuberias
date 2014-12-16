/**
 * 
 */
package entidades;

import juego.Entidad;

/**
 * Esta clase representa a la clase bomba del juego.
 * De ella heredarán otras clases bomba como la 
 * BombaExtrac, Bomba Inyec...
 * 
 * @author Juan Ángel Sánchez López - Tomás Gómez Castilla 
 * 
 */
public abstract class Bomba extends Entidad {
	private int retardo = 0;
	private int turnoActual = 0;
	
	/**
	 * 
	 */
	public Bomba() {
		this(0);
	}
	/**
	 * Constructor
	 * @param retardo de bombeo
	 */
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
	/* (sin Javadoc)
	 * @see juego.Entidad#turno()
	 */
	@Override
	public void turno() {
		// TODO Apéndice de método generado automáticamente
		turnoActual = (turnoActual+1)%(retardo+1);
		if (turnoActual == 0) {
			turnoBomba();
		}
	}
	/**
	 * Ejecuta la accion de la bmba
	 */
	protected abstract void turnoBomba();

}
