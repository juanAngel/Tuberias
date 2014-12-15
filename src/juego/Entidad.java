package juego;

import tuberias.vista.Dibujable;
import estructura.Celda;
import estructura.Direccion;
import estructura.Posicion;
import estructura.Tuberia;


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
				System.out.println("Entidad.mover()"+siguienteCelda.getPosicion());
				setPosActual(siguienteCelda.getPosicion());
			}
		}
	}
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
