package juego;

import tuberias.vista.Dibujable;
import estructura.Celda;
import estructura.Direccion;
import estructura.Posicion;
import estructura.Tuberia;

/**
 * Representa a un objeto de juego que es capaz de interactuar con la tuberia
 * @author Juan Ángel Sánchez López  - Tomás Gómez Castilla 
 * 
 */
public abstract class Entidad implements Dibujable{
	
	private Posicion posActual;
	private Tuberia tuberia;
	
	/* (sin Javadoc)
	 * @see tuberias.vista.Dibujable#getPosicionX()
	 */
	@Override
	public int getPosicionX() {
		assert(inTuberia());
		
		return posActual.getX();
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.Dibujable#getPosicionY()
	 */
	@Override
	public int getPosicionY() {
		assert(inTuberia());
		
		return posActual.getY();
	}
	/**
	 * Ejecuta el turno de la entidad
	 */
	public abstract void turno();
	
	// METODOS GET Y SET

	/**
	 * @return La {@link Posicion} de la {@link Entidad} en la tuberia
	 */
	public Posicion getPosActual() {
		return posActual;
	}

	/**
	 * Asigna una posicion a la {@link Entidad}
	 * @param posActual La nueva posicion de la {@link Entidad}
	 */
	public void setPosActual(Posicion posActual) {
		this.posActual = posActual;
	}

	/**
	 * @return La {@link Tuberia} asignada a la {@link Entidad}
	 */
	public Tuberia getTuberia() {
		return tuberia;
	}
	
	/**
	 * @return Ture Si la {@link Entidad} esta dentro de la {@link Tuberia} y FALSE en caso contrario
	 */
	public boolean inTuberia(){
		return getTuberia() != null;
	}
	
	/**
	 * Asigna una {@link Tuberia} a la {@link Entidad}
	 * @param tuberia
	 */
	public void setTuberia(Tuberia tuberia) {
		this.tuberia = tuberia;
	}
	
	/**
	 * @return La {@link Celda} actual
	 */
	public Celda getCelda(){
		return tuberia==null?null:tuberia.getCelda(posActual);
	}
	
	// METODOS

	/**
	 * Mueve la {@link Entidad} a la {@link Direccion} indicada
	 * @param d 
	 */
	public void mover(Direccion d){
		if(inTuberia()){
			Celda siguienteCelda = tuberia.getCelda(posActual.adyacente(d));
			if(siguienteCelda != null){
				System.out.println("Entidad.mover()"+siguienteCelda.getPosicion());
				setPosActual(siguienteCelda.getPosicion());
			}
		}
	}
	/**
	 * Mueve la entidad a una {@link Direccion} aleatoria si se puede
	 */
	public void moverAleatorio(){
		mover(Direccion.aleatoria());
		/*
		if(inTuberia()){
			Celda siguienteCelda = null;
			while (siguienteCelda == null) {
				siguienteCelda = getCelda().getVecina(Direccion.aleatoria());
			}
			setPosActual(siguienteCelda.getPosicion());
		}*/
	}

}
