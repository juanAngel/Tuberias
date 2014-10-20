package estructura;

public class Posicion {
	private int x,y;

	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Posicion(Posicion p){
		this(p.x,p.y);
	}
	
	public Posicion() {
		this(0, 0);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Posicion adyacente(Direccion d){
		return desplazar(d, 1);
	}
	public Posicion desplazar(Direccion d,int distancia){
		if(distancia<0)
			return null;
		
		int xInc = 0,yInc = 0;
		switch (d) {
			case ARRIBA:
				yInc = 1;
				break;
			case ABAJO:
				yInc = -1;
				break;
			case IZQUIERDA:
				xInc = -1;
				break;
			case DERECHA:
				xInc = 1;
				break;
		}
		return new Posicion(x+xInc*distancia,y+yInc*distancia);
	}
	public boolean esAdyacente(Posicion p){
		return Math.abs(x-p.x+y-p.y)==1;
	}
	public double distancia(Posicion p){
		return Math.sqrt(Math.pow(x-p.x, 2)+Math.pow(y-p.y, 2));
	}
	public Posicion clone(){
		return new Posicion(this);
		
	}
}
