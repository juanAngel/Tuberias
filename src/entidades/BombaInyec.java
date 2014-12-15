/**
 * 
 */
package entidades;

/**
 * @author juan
 *
 */
public class BombaInyec extends Bomba {

	/**
	 * 
	 */
	public BombaInyec() {
		super();
		// TODO Apéndice de constructor generado automáticamente
	}

	/**
	 * @param retardo
	 */
	public BombaInyec(int retardo) {
		super(retardo);
		// TODO Apéndice de constructor generado automáticamente
	}

	/* (sin Javadoc)
	 * @see juego.Bomba#accion()
	 */
	@Override
	protected void turnoBomba() {
		getCelda().agregarAgua();
	}

	@Override
	public String getImagen() {
		// TODO Apéndice de método generado automáticamente
		return "inyector";
	}

}
