package estructura;


/**
 * Esta clase representa los componentes basicos de las tuberias, como las posiciones
 * en un tablero de ajedrez.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

public class CeldaSegura extends Celda {
	
	// CONSTRUCTORES
	

	public CeldaSegura() {
		super(0);
	}
	
	// METODOS OBJECT
	
	@Override
	public CeldaSegura clone() {
		return new CeldaSegura();
	}
	/* (sin Javadoc)
	 * @see estructura.Celda#getImagen()
	 */
	@Override
	public String getImagen() {
		// TODO Apéndice de método generado automáticamente
		return "celda-segura";
	}
}
