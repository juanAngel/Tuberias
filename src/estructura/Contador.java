package estructura;

public class Contador extends Celda{
	int incCaudal = 0;
	public Contador() {
		super(1);
	}

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
