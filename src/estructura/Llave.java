package estructura;


/**
 * Esta clase representa las llaves del juego, donde se puede bloquear la inserccion
 * y la extraccion del agua.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

public class Llave extends Celda {
	
	// ATRIBUTOS
	
	private boolean abierta;
	
	// CONSTRCUTOR
	
	public Llave() {
		
	}
	
	// METODOS GET Y SET

	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}
	
	// METODOS
	
	/**
	 * Cambia el estado de abierto. De true a false y viceversa.
	 */
	public void conmutarAbierta(){
		abierta ^= true;
	}
	
	/**
	 * Permite el paso del agua si abierta es true.
	 */
	@Override
	public void añadirAgua(Celda origen){
		if(abierta)
			super.añadirAgua(origen);
	}

	/**
	 * Impide el paso del agua si abierta es false.
	 */
	@Override
	public void extraerAgua(Celda origen){
		if(abierta)
			super.extraerAgua(origen);
	}

	// METODOS OBJECT
	
	@Override
	public Celda clone() {
		return new Llave();
	}
	

}
