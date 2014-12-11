package entidades;

import estructura.Celda;
import estructura.Direccion;
import estructura.Llave;

public class Jugador extends EntidadViva {
	private Direccion dirSig = null;
	private boolean conmutarLLave = false;
	
	public Jugador() {
		// TODO Apéndice de constructor generado automáticamente
	}

	@Override
	protected void turnoViva() {
		if(dirSig != null){
			Celda vecina = getCelda().getVecina(dirSig);
			if(vecina != null)
				setPosActual(vecina.getPosicion());
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

}
