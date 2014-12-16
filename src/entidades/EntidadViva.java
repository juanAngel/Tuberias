/**
 * 
 */
package entidades;

import juego.Entidad;

/**
 * Esta clase representa a las entidades vivas del juego.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */
public abstract class EntidadViva extends Entidad {
	private long ultimoRespito;
	static private long TIEMPO_MAXIMO_AIRE = 5*1000;

	public EntidadViva() {
		ultimoRespito = System.currentTimeMillis();
	}
	public boolean isViva() {
		return !getCelda().isSaturada() || aire()<TIEMPO_MAXIMO_AIRE;
	}
	public long aire(){
		return (System.currentTimeMillis() - ultimoRespito);
	}
	/**
	 * Ejecuta {@link #turnoViva()} si esta sigue viva, en caso contrario se sale de la tuberia
	 * 
	 */
	@Override
	public void turno() {
		if(isViva()){
			if(!getCelda().isSaturada())
				ultimoRespito = System.currentTimeMillis();
			turnoViva();
		}else{
			getTuberia().sacarEntidad(this);
		}
	}
	/**
	 * Ejecuta la accion correspondiente a estar vivo
	 */
	protected abstract void turnoViva();

}
