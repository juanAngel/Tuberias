package estructura;

import tuberias.vista.Dibujable;


/**
 * Esta clase representa los componentes basicos de las tuberias, como las posiciones
 * en un tablero de ajedrez.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

/**
 * @author juan
 * Representa una Celda de la {@link Tuberia}
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
	 * @param posicion
	 */
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	
	/**
	 * @return
	 */
	public boolean isSaturada(){
		return caudal>0 && caudal == capacidad;
	}
	
	/**
	 * @return
	 */
	public boolean isVacia(){
		return caudal == 0;
	}
	public int getFactorSaturacion(){
		if(capacidad==0)
			return 0;
		return (caudal*100)/capacidad;
	}
	
	/**
	 * @param dir
	 * @param celda
	 */
	void setVecina(Direccion dir,Celda celda){
		vecinas[dir.ordinal()] = celda;
	}
	
	/**
	 * @param dir
	 * @return
	 */
	public Celda getVecina(Direccion dir){
		return vecinas[dir.ordinal()];
	}
	
	/**
	 * @return
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
	
	
	public String toString(){
		
		return getClass().getName() + "[ Capacidad: " + capacidad + " // Caudal: " + caudal +
		" // Vecinas: " + vecinas + " // PosicionX: " + this.posicion + " ]";
	}

	@Override
	public String getImagen() {
		int saturacion = getFactorSaturacion();
		return saturacion<50?"celda-vacia":saturacion==100?"celda-llena":"celda-medio";
	}

	@Override
	public int getPosicionX() {
		assert(posicion != null);
		
		return posicion.getX();
	}

	@Override
	public int getPosicionY() {
		assert(posicion != null);
		
		return posicion.getY();
	}
	
}
