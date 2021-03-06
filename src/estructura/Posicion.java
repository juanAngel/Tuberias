package estructura;


/**
 * Esta clase representa la posicion respecto un eje de coordenadas X e Y.
 * Es la posicion dentro del tablero de juego.
 * 
 * @author Juan Angel Sanchez Lopez  - Tomás Gómez Castilla 
 * 
 */

public class Posicion {
	
	// ATRIBUTOS
	
	final private int x,y;
	
	// CONSTRUCTORES

	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructor copia
	 * @param p
	 */
	public Posicion(Posicion p){
		this(p.x,p.y);
	}
	
	/**
	 * Representa la posicion 0,0
	 */
	public Posicion() {
		this(0, 0);
	}
	
	// METODOS GET Y SET

	/**
	 * @return el valor de X
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return el valor de Y
	 */
	public int getY() {
		return y;
	}
	
	// METODOS

	/**
	 * Devuelve un objeto posicion adyacente al de la llamada segun la direccion
	 * que se le pase por parametro.
	 */
	public Posicion adyacente(Direccion d){
		return desplazar(d, 1);
	}
	
	/**
	 * @param d Direccion del desplazamiento
	 * @param distancia Distancia a desplazar
	 * @return LA posicion desplazada con respecto al punto
	 */
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
	
	/**
	 * Devuelve true o false dependiendo si una posicion es adyacente a otra
	 * en un posicion determinada
	 */
	public boolean esAdyacente(Posicion p){
		return Math.abs(x-p.x+y-p.y)==1;
	}
	
	/**
	 * Devuelve la distancia respecto a la posicion pasada por parametro.
	 */
	public double distancia(Posicion p){
		return Math.sqrt(Math.pow(x-p.x, 2)+Math.pow(y-p.y, 2));
	}
	
	// METODOS OBJECT 
	
	/* (sin Javadoc)
	 * @see java.lang.Object#clone()
	 */
	public Posicion clone(){
		return new Posicion(this);
	}
	
	/* (sin Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode (){
		
		int primo= 31 ;
		int result =1;
		
		result=result * primo + x;
		result=result * primo + y;
		return result;
	}

	/* (sin Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		
		if (obj == null){
			return false;
		}
		if (obj == this){
			return true;
		}
		if (this.getClass() != obj.getClass()){
			return false;
		}
		
		Posicion burbuja = (Posicion) obj;
		
		return this.x == (burbuja.x) && this.y ==(burbuja.y);
	
	}

	/* (sin Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		
		return getClass().getName() + "[ Coord X: " + x + " // Coord Y: " + y + " ]";
	}


}
