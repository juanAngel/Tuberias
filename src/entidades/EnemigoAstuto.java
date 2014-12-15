package entidades;

import estructura.Celda;
import estructura.Direccion;
import estructura.Llave;

public class EnemigoAstuto extends Enemigo {
	final private int vision;
	/**
	 * @param vision
	 */
	public EnemigoAstuto(int vision) {
		super();
		this.vision = vision;
	}

	@Override
	protected void movimiento() {
		Direccion dir = null;
		if(aire()<2*1000){
			dir = buscarLlave();
		}else{
			dir = buscarCeldaNoSaturada();
		}
		if(dir == null)
			moverAleatorio();
		else
			mover(dir);
	}
	Direccion buscarCeldaNoSaturada(){
		int masCerca = vision;
		Direccion d = null;
		Celda actual;
		for(int y = 0;y<Direccion.values().length;y++){
			actual = getCelda();
			for(int i = 0;i<vision && actual != null;++i){
				actual = actual.getVecina(Direccion.values()[y]);
				if(actual != null && !actual.isSaturada()){
					if(i<masCerca){
						d = Direccion.values()[y];
						masCerca = i;
					}
					i = vision;
				}
			}
		}
		return d;
	}
	Direccion buscarLlave(){
		int masCerca = vision;
		Direccion d = null;
		Celda actual;
		for(int y = 0;y<Direccion.values().length;y++){
			actual = getCelda();
			for(int i = 0;i<vision && actual != null;++i){
				actual = actual.getVecina(Direccion.values()[y]);
				if(actual instanceof Llave){
					Llave llave = (Llave)actual;
					if(i<masCerca && !llave.isAbierta()){
						d = Direccion.values()[y];
						masCerca = i;
					}
					i = vision;
				}
			}
		}
		return d;
	}

	@Override
	protected void accion() {
		if (getCelda() instanceof Llave) {
			Llave llave = (Llave) getCelda();
			llave.setAbierta(true);
		}
	}

	@Override
	public String getImagen() {
		// TODO Apéndice de método generado automáticamente
		return "enemigo-rojo";
	}

}
