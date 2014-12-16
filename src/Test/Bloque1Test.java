package Test;

import estructura.Direccion;
import estructura.Posicion;
import estructura.Celda;
import estructura.Tuberia;

/**
 * Esta clase main es una de las clases que se usan como
 * bateria de pruebas. 
 * 
 * En ella se recoge las pruebas del Bloque 1.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

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
		
		Celda celda1 = new Celda();	celda1.setPosicion(posicion1);
		Celda celda2 = new Celda(1); celda2.setPosicion(posicion2);
		
		System.out.println("celda1 -> x = " + celda1.getPosicion().getX() + " // y = " + celda1.getPosicion().getY()
				 + " // caudal = " + celda1.getCaudal() + " // capacidad = " + celda1.getCapacidad());
		
		System.out.println("celda2 -> x = " + celda2.getPosicion().getX() + " // y = " + celda2.getPosicion().getY()
				 + " // caudal = " + celda2.getCaudal() + " // capacidad = " + celda2.getCapacidad());
		
		celda2.agregarAgua(); celda2.agregarAgua(); celda2.extraerAgua(); celda2.extraerAgua(); 
		
		System.out.println("celda2 -> x = " + celda2.getPosicion().getX() + " // y = " + celda2.getPosicion().getY()
				 + " // caudal = " + celda2.getCaudal() + " // capacidad = " + celda2.getCapacidad());
		
		// PRUEBA CLASE TUBERIAS
		
		Tuberia tuberia1 = new Tuberia(5,6,posicion1);
		//Tuberia tuberia2 = new Tuberia(7,7,posicion2);
		
		System.out.println("Tuberia1 -> ancho =  " + tuberia1.getAlto() + " // alto = " + tuberia1.getAncho());		
		
	}

}
