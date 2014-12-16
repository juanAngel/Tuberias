/**
 * 
 */
package entidades;
/**
* Esta clase representa al objeto Bomba Extractora del juego.
* En su turno extrae agua.
* 
* @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
* 
*/

public class BombaExtrac extends Bomba {
	/**
	 * 
	 */
	public BombaExtrac() {
		super();
	}

	/**
	 * @param retardo Retardo de Extraccion
	 */
	public BombaExtrac(int retardo) {
		super(retardo);
	}

	/* (sin Javadoc)
	 * @see juego.Bomba#accion()
	 * Extrae agua de la Celda
	 */
	@Override
	protected void turnoBomba() {
		getCelda().extraerAgua();
	}

	@Override
	public String getImagen() {
		return "extractor";
	}

}
