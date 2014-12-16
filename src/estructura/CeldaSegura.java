package estructura;


/**
 * Este tipo de Celda especial, nunca se llena. por lo tanto. nunca te ahogaras
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

	/* (sin Javadoc)
	 * @see estructura.Celda#getFactorSaturacion()
	 */
	@Override
	public int getFactorSaturacion() {
		return 0;
	}
	
}
