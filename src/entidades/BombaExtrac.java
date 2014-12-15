/**
 * 
 */
package entidades;

/**
 * @author juan
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
	 * @param retardo
	 */
	public BombaExtrac(int retardo) {
		super(retardo);
	}

	/* (sin Javadoc)
	 * @see juego.Bomba#accion()
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
