package juego;

import estructura.Celda;
import estructura.Direccion;
import estructura.Posicion;
import estructura.Tuberia;


public abstract class Entidad {
	
	// ATRIBUTOS
	
	private Posicion posActual;
	private Tuberia tuberia;
	public abstract void turno();
	
	// METODOS GET Y SET

	public Posicion getPosActual() {
		return posActual;
	}

	public void setPosActual(Posicion posActual) {
		this.posActual = posActual;
	}

	public Tuberia getTuberia() {
		return tuberia;
	}
	
	public boolean inTuberia(){
		return getTuberia() != null;
	}

	public void setTuberia(Tuberia tuberia) {
		this.tuberia = tuberia;
	}
	
	public Celda getCelda(){
		
		return tuberia==null?null:tuberia.getCelda(posActual);
	}
	
	// METODOS
	
	public void mover(Direccion d){
		if(inTuberia()){
			Celda siguienteCelda = tuberia.getCelda(posActual.adyacente(d));
			if(siguienteCelda != null){
				setPosActual(siguienteCelda.getPosicion());
			}
		}
	}

}
