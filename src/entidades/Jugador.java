package entidades;

import estructura.Direccion;
import estructura.Llave;

/**
 * Esta clase representa al jugador del juego.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */
public class Jugador extends EntidadViva {
	private Direccion dirSig = null;
	private boolean conmutarLLave = false;
	
	public Jugador() {}

	@Override
	protected void turnoViva() {
		if(dirSig != null){
			mover(dirSig);
			dirSig = null;
		}
		if(conmutarLLave){
			if(getCelda() instanceof Llave){
				Llave llave =(Llave)getCelda();
				llave.conmutarLlave();
			}
			conmutarLLave = false;
		}
		
	}
	public void solicitudMovimiento(Direccion d) {
		dirSig = d;
	}
	public void actuar(){
		conmutarLLave = true;
	}
	
	@Override
	public String getImagen() {
		// TODO Apéndice de método generado automáticamente
		return "link Zelda";
	}

}
