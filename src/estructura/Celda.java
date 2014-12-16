package estructura;

import tuberias.vista.Dibujable;


/**
 * Esta clase representa los componentes basicos de las tuberias, como las posiciones
 * en un tablero de ajedrez.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */
public class Celda implements Dibujable{
	
	// ATRIBUTOS
	
	final private int capacidad;
	private int caudal;
	final private Celda vecinas[] = new Celda[4];
	private Posicion posicion;
	
	// CONSTRUCTORES
	
	/**
	 * Crea una Celda
	 * @param capacidad
	 */
	public Celda(int capacidad) {
		this.capacidad = capacidad;
	}
	
	/**
	 * Crea una Celda con Capacidad por defecto 5
	 */
	public Celda() {
		this(5);
	}	
	
	// METODOS GET Y SET	
	
	/**
	 * @return Posicion de la {@link Celda}
	 */
	public Posicion getPosicion() {
		return posicion;
	}
	
	/**
	 * Establece la posicion de la Celda
	 * @param posicion
	 */
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	/**
	 * @return True si la celda esta saturada
	 */
	public boolean isSaturada(){
		return caudal>0 && caudal == capacidad;
	}
	
	/**
	 * @return True si la Celda esta vacia
	 */
	public boolean isVacia(){
		return caudal == 0;
	}
	/**
	 * @return el porcentaje de llenado de la celda
	 */
	public int getFactorSaturacion(){
		if(capacidad==0)
			return 0;
		return (caudal*100)/capacidad;
	}
	
	/**
	 * Establece la vecindad de un par de Celdas
	 * @param dir Direccion en la que esta la Celda vecina
	 * @param celda
	 */
	void setVecina(Direccion dir,Celda celda){
		vecinas[dir.ordinal()] = celda;
	}
	
	/**
	 * @param dir Direccion de la vecina
	 * @return Una Celda que es vecina o Null en caso contrario
	 */
	public Celda getVecina(Direccion dir){
		return vecinas[dir.ordinal()];
	}
	
	/**
	 * @return El caudal de la Celda
	 */
	public int getCaudal() {
		return caudal;
	}

	/**
	 * @return Capacidad de la celda
	 */
	public int getCapacidad() {
		return capacidad;
	}
	
	// METODOS
	@Override
	protected Celda clone() throws CloneNotSupportedException {
		// TODO Apéndice de método generado automáticamente
		return new Celda(capacidad);
	}

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
	public void agregarAgua(){
		agregarAgua(null);
	}
	
	/**
	 * Incrementa el caudal de la celda y si esta saturada se propaga a las vecinas.
	 * @param origen Celda de origen
	 */
	public void agregarAgua(Celda origen){
		if(caudal<capacidad){
			incrementarCaudal();
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
	
	/**
	 * Decrementa el caudal de la celda y si la celda esta vacia se propaga.
	 */
	public void extraerAgua(){
		extraerAgua(null);
	}
	
	/**
	 * Decrementa el caudal de la celda y si la la celda esta vacia se propaga.
	 * @param origen Celda de origen
	 */
	public void extraerAgua(Celda origen){
		if(caudal>0){
			decrementarCaudal();
		}else{
			Celda vecina;
			Direccion direcciones[] = Direccion.values();
			for(int i = 0;i<direcciones.length;i++){
				vecina = getVecina(direcciones[i]);
				if(vecina != null && vecina != origen)
					vecina.extraerAgua(this);
			}
		}
	}
	
	// METODOS OBJECT
	
	
	/* (sin Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		return getClass().getName() + "[ Capacidad: " + capacidad + " // Caudal: " + caudal +
		" // Vecinas: " + vecinas + " // PosicionX: " + this.posicion + " ]";
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.Dibujable#getImagen()
	 */
	@Override
	public String getImagen() {
		int saturacion = getFactorSaturacion();
		return saturacion<50?"celda-vacia":saturacion==100?"celda-llena":"celda-medio";
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.Dibujable#getPosicionX()
	 */
	@Override
	public int getPosicionX() {
		assert(posicion != null);
		
		return posicion.getX();
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.Dibujable#getPosicionY()
	 */
	@Override
	public int getPosicionY() {
		assert(posicion != null);
		
		return posicion.getY();
	}
	
}
