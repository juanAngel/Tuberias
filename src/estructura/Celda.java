package estructura;

/**
 * Esta clase representa los componentes basicos de las tuberias, como las posiciones
 * en un tablero de ajedrez.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

public class Celda {
	
	// ATRIBUTOS
	
	private int capacidad = 5;
	private int caudal;
	private Celda vecinas[] = new Celda[4];
	private Posicion posicion;
	
	// CONSTRUCTORES
	
	public Celda(int capacidad) {
		this.capacidad = capacidad;
	}
	public Celda() {}	
	private Celda(Celda c){
		this.capacidad = c.capacidad;
		this.caudal = c.caudal;
		this.posicion = c.posicion;
	}
	
	// METODOS GET Y SET
	
	void setVecina(Direccion dir,Celda celda){
		vecinas[dir.ordinal()] = celda;
	}
	
	Celda getVecina(Direccion dir){
		return vecinas[dir.ordinal()];
	}
	
	void resetVecinas(){
		for (int i = 0; i < vecinas.length; i++) {
			vecinas[i] = null;
		}
	}
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	// METODOS
	
	/**
	 * Devuelve la direccion opuesta a la direccion actual del mismo.
	 */
	
	public boolean isSaturada(){
		return caudal>0 && caudal == capacidad;
	}
	
	public boolean isVacia(){
		return caudal == 0;
	}
	void incrementarCaudal(){
		if(capacidad>caudal)
			caudal++;
	}
	void decrementarCaudal(){
		if(capacidad>caudal)
			caudal++;
	}

	public void agregarAgua(){
		agregarAgua(null);
	}
	public void agregarAgua(Celda origen){
		if(caudal<capacidad){
			caudal++;
		}else{
			Celda vecina;
			Direccion direcciones[] = Direccion.values();
			for(int i = 0;i<direcciones.length;i++){
				vecina = getVecina(direcciones[i]);
				if(vecina != null && vecina != origen)
					vecina.agregarAgua(this);
			}
		}
	}
	public void extraerAgua(){
		extraerAgua(null);
	}
	public void extraerAgua(Celda origen){
		if(caudal>0){
			caudal--;
		}else{
			Celda vecina;
			Direccion direcciones[] = Direccion.values();
			for(int i = 0;i<direcciones.length;i++){
				vecina = getVecina(direcciones[i]);
				if(vecina != null && vecina != origen)
					vecina.agregarAgua(this);
			}
		}
	}
	
	// METODOS OBJECT
	
	@Override
	public Celda clone(){
		return new Celda();
	}
	
	public String toString(){
		
		return getClass().getName() + "[ Capacidad: " + capacidad + " // Caudal: " + caudal +
		" // Vecinas: " + vecinas + " // PosicionX: " + this.posicion.getX() + " "
				+ "// PosicionY: " + posicion.getY() + " ]";
	}
	
	public int hashcode (){
		
		int primo= 31 ;
		int result =1;
		
		result=result * primo + posicion.getX();
		result=result * primo + posicion.getY();
		result=result * primo + capacidad;
		result=result * primo + caudal;
		// result=result * primo + vecinas;  NO SE PORQUE NO ME DEJA
		
		return result;
	}
	
	public boolean equals(Object obj){
		
		if (obj == null){
			return false;
		}
		if (obj == this){
			return true;
		}
		if (this.getClass() != obj.getClass()){
			return false;
		}
		
		Celda burbuja = (Celda) obj;
		
		return this.capacidad == (burbuja.capacidad) && this.caudal == (burbuja.caudal)
		&& this.posicion == (burbuja.posicion) && this.vecinas == (burbuja.vecinas);
	
	}
	
}
