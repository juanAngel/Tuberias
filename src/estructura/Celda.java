package estructura;

public class Celda {
	private int capacidad = 5;
	private int caudal;
	private Celda vecinas[] = new Celda[4];
	private Posicion posicion;
	
	public Celda(int capacidad) {
		this.capacidad = capacidad;
	}
	public Celda() {}
	private Celda(Celda c){
		capacidad = c.capacidad;
		caudal = c.caudal;
		posicion = c.posicion;
	}
	
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
	@Override
	public Celda clone(){
		return new Celda();
	}
	
}
