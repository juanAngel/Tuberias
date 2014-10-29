package estructura;

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
	
	public void tolggleAbierta(){
		abierta ^= true;
	}
	
	@Override
	public void agregarAgua(Celda origen){
		if(abierta)
			super.agregarAgua(origen);
	}

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
