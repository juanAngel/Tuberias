package estructura;

public class Llave extends Celda {
	private boolean abierta;
	
	public Llave() {
	}

	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}
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

	@Override
	public Celda clone() {
		return new Llave();
	}
	

}
