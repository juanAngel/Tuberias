/**
 * 
 */
package entidades;

/**
 * Esta clase representa a la bomba de inyeccion del juego.
 * 
 * En vez de extraer agua la inserta.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
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
	 * @param retardo de inyeccion
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

	/* (sin Javadoc)
	 * @see tuberias.vista.Dibujable#getImagen()
	 */
	@Override
	public String getImagen() {
		// TODO Apéndice de método generado automáticamente
		return "inyector";
	}

}
