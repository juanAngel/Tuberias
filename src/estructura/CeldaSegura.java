package estructura;



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
