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
	public Celda clone() {
		return new CeldaSegura();
	}
	
	public String toString(){
		
		return this.toString();
	}
	
	public int hashcode (){
		
		return this.hashcode();
	}
	
	public boolean equals(Object obj){

		return this.equals(obj);
		
	}
	
}
