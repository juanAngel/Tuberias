package estructura;

public class CeldaSegura extends Celda {
	public CeldaSegura() {
		super(0);
	}
	@Override
	public Celda clone() {
		return new CeldaSegura();
	}
}
