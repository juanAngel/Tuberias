package estructura;

/**
 * En este enumerado se representa las cuatro posibles direcciones.
 * 
 * @author Juan Angel Sanchez Lopez  - Tomás Gómez Castilla 
 * 
 */

public enum Direccion {
	
	// ENUMERADOS
	
	ARRIBA, ABAJO, DERECHA,	IZQUIERDA;
	
	// METODOS
	
	/**
	 * Devuelve la direccion opuesta a la direccion actual del mismo.
	 */
	public Direccion opuesta(){
		
		switch (this) {
			case ARRIBA:
				return ABAJO;
			case ABAJO:
				return ARRIBA;
			case DERECHA:
				return IZQUIERDA;
			case IZQUIERDA:
				return DERECHA;
			default:
				return null;
		}
	}
	
	
	/**
	 * Devuelve una direccion aleatorio de las cuatro posibles.
	 */
	public static Direccion aleatoria() {
		Direccion direcciones[] = values();
		return direcciones[(int)(Math.random()*direcciones.length)];
	}
}
