package Test;

import estructura.Direccion;
import estructura.Posicion;
import estructura.Celda;
import estructura.Tuberia;

public class Bloque1Test {

	public static void main(String[] args) {

	
		// PRUEBA CLASE DIRECCION
	
		System.out.println("DIRRECCION OPUESTA(ABAJO): " + Direccion.ABAJO.opuesta());		
		System.out.println("DIRRECCION ALEATORIA: " + Direccion.aleatoria());
		
		// PRUEBA CLASE POSICION
		
		Posicion posicion1 = new Posicion();
		Posicion posicion2 = new Posicion(0,1);
		Posicion posicion3 = new Posicion(posicion2);
		
		System.out.println("Constructor sin argumentos: x = " + posicion1.getX() + " // y = " + posicion1.getY());
		System.out.println("Constructor con argumentos: x = " + posicion2.getX() + " // y = " + posicion2.getY());
		System.out.println("Constructor copia: x = " + posicion3.getX() + " // y = " + posicion3.getY());
		
		System.out.println("Prueba metodo adyacente : x = " + posicion1.adyacente(Direccion.DERECHA).getX()
				+ " // y = " + posicion1.adyacente(Direccion.DERECHA).getY());
		
		System.out.println("Prueba metodo esAdyacente : " + posicion1.esAdyacente(posicion2));
		
		System.out.println("Prueba metodo distancia : " + posicion1.distancia(posicion2));
		
		// PRUEBA CLASE CELDA
		
		Celda celda1 = new Celda();
		Celda celda2 = new Celda(5);
		
		
		
	}

}
