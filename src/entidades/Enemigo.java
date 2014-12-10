package entidades;

public abstract class Enemigo extends EntidadViva {

	@Override
	protected void turnoViva() {
		movimiento();
		accion();
	}
	protected abstract void movimiento();

	protected abstract void accion();
	

}
