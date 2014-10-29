package estructura;

public class Contador extends Celda{
	
	// ATRIBUTOS
	
	int incCaudal = 0;
	
	// CONSTRUCTORES
	
	public Contador() {
		super(1);
	}
	
	// METODOS

	@Override
	public void agregarAgua(Celda origen){
		incCaudal++;
		Celda vecina;
		Direccion direcciones[] = Direccion.values();
		for(int i = 0;i<direcciones.length;i++){
			vecina = getVecina(direcciones[i]);
			if(vecina != null && vecina != origen)
				vecina.agregarAgua(this);
		}
	}
	
	public int getIncCaudal(){
		return incCaudal;
	}

}
