package estructura;

public enum Direccion {
	ARRIBA, ABAJO, DERECHA,	IZQUIERDA;
	public Direccion opuesta(){
		switch (this) {
			case ARRIBA:
				return ABAJO;
			case ABAJO:
				return ARRIBA;
			case DERECHA:
				return IZQUIERDA;
			case IZQUIERDA:
				return DERECHA;
			default:
				return null;
		}
	}
	public static Direccion aleatoria() {
		Direccion direcciones[] = values();
		return direcciones[(int)(Math.random()*direcciones.length)];
	}
}
