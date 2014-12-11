/**
 * 
 */
package entidades;

import juego.Entidad;

/**
 * @author juan
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
	/* (sin Javadoc)
	 * @see juego.Entidad#turno()
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
	protected abstract void turnoViva();

}
