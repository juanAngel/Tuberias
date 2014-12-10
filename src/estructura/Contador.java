package estructura;


/**
 * En esta clase se representara el contador. Este lleva una cuenta del agua que se introduce
 * en la celda, ademas deja salir el agua de la tuberia.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

public class Contador extends Celda{
	
	
	// ATRIBUTOS
	
	int incCaudal = 0;
	
	// CONSTRUCTORES
	
	public Contador() {
		super(1);
	}
	
	// METODOS

	/**
	 * Incrementa el caudal en uno y deja salir el agua.
	 */
	@Override
	public void agregarAgua(Celda origen){
		incCaudal++;  
		Celda vecina;
		Direccion direcciones[] = Direccion.values();
		for(int i = 0;i<direcciones.length;i++){
			vecina = consultarVecina(direcciones[i]);
			if(vecina != null && vecina != origen)
				vecina.agregarAgua(this);
		}
	}
	
	public int getIncCaudal(){
		return incCaudal;
	}

}
