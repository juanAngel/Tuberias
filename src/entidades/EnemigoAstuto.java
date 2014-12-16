package entidades;

import estructura.Celda;
import estructura.Direccion;
import estructura.Llave;

/**
 * Esta clase representa a los enemigos astutos del juego.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

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
	
	/**
	 * Busca la Celda no saturada mas cercana
	 * @return La direccion hacia donde esta la Celda
	 */
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
	/**
	 * Busca la Llave abierta mas cercana
	 * @return La direccion hacia donde esta la Celda
	 */
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

	/* Abre la llave si es la celda actual
	 * @see entidades.Enemigo#accion()
	 */
	@Override
	protected void accion() {
		if (getCelda() instanceof Llave) {
			Llave llave = (Llave) getCelda();
			llave.setAbierta(true);
		}
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.Dibujable#getImagen()
	 */
	@Override
	public String getImagen() {
		// TODO Apéndice de método generado automáticamente
		return "enemigo-rojo";
	}

}
