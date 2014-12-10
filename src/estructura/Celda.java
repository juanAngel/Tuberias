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
	
	final private int capacidad;
	private int caudal;
	final private Celda vecinas[] = new Celda[4];
	private Posicion posicion;
	
	// CONSTRUCTORES
	
	public Celda(int capacidad) {
		this.capacidad = capacidad;
	}
	
	public Celda() {
		this(5);
	}	
	
	// METODOS GET Y SET	
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	public boolean isSaturada(){
		return caudal>0 && caudal == capacidad;
	}
	
	public boolean isVacia(){
		return caudal == 0;
	}
	
	void establecerVecina(Direccion dir,Celda celda){
		vecinas[dir.ordinal()] = celda;
	}
	
	Celda consultarVecina(Direccion dir){
		return vecinas[dir.ordinal()];
	}
	
	public int getCaudal() {
		return caudal;
	}

	public int getCapacidad() {
		return capacidad;
	}
	
	// METODOS


	/**
	 * Incrementa el caudal en uno, siempre que no se pase de la capacidad
	 */
	void incrementarCaudal(){
		if(capacidad>caudal)
			caudal++;
	}
	
	/**
	 * Decrementa el caudal en uno, siempre que no se pase de cero.
	 */
     void decrementarCaudal(){
		if(caudal>0)
			caudal--;
	}

	/**
	 * Eliminar la relacion con todas las celdas vecinas
	 */
	 void resetVecinas(){
		for (int i = 0; i < vecinas.length; i++) {
			vecinas[i] = null;
		}
	}
	
	/**
	 * Incrementa el caudal de la celda y si esta saturada se propaga a las vecinas
	 */
	public void añadirAgua(){
		agregarAgua(null);
	}
	
	/**
	 * Incrementa el caudal de la celda y si esta saturada se propaga a las vecinas.
	 * Con la celda de origen
	 */
	public void agregarAgua(Celda origen){
		if(caudal<capacidad){
			incrementarCaudal();
		}else{
			Celda vecina;
			Direccion direcciones[] = Direccion.values();
			for(int i = 0;i<direcciones.length;i++){
				vecina = consultarVecina(direcciones[i]);
				if(vecina != null && vecina != origen)
					vecina.agregarAgua(this);
			}
		}
	}
	
	/**
	 * Decrementa el caudal de la celda y si la celda esta vacia se propaga.
	 */
	public void extraerAgua(){
		extraerAgua(null);
	}
	
	/**
	 * Decrementa el caudal de la celda y si la la celda esta vacia se propaga.
	 */
	public void extraerAgua(Celda origen){
		if(caudal>0){
			decrementarCaudal();
		}else{
			Celda vecina;
			Direccion direcciones[] = Direccion.values();
			for(int i = 0;i<direcciones.length;i++){
				vecina = consultarVecina(direcciones[i]);
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
