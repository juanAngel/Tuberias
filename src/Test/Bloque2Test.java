package Test;

import estructura.CeldaSegura;
import estructura.Contador;
import estructura.Llave;

public class Bloque2Test {

	public static void main(String[] args) {
		
		// CLASE LLAVE
		
		Llave llave1 = new Llave();

		System.out.println("Llave1 ->  Abierta = " + llave1.isAbierta() + " // Caudal = " + llave1.getCaudal() + 
				" // Capacidad = " + llave1.getCapacidad());		
		
		llave1.conmutarLlave(); llave1.agregarAgua(); llave1.conmutarLlave(); llave1.extraerAgua();
		
		System.out.println("Llave1 ->  Abierta = " + llave1.isAbierta() + " // Caudal = " + llave1.getCaudal() + 
				" // Capacidad = " + llave1.getCapacidad());	
		
		
		// CLASE CELDA SEGURA
		
		CeldaSegura celdaSegura1 = new CeldaSegura();
		
		celdaSegura1.agregarAgua();
		
		System.out.println("CeldaSegura1 ->  Caudal = " + celdaSegura1.getCaudal() + " // Capacidad" + celdaSegura1.getCapacidad());
		
		
		// CLASE CONTADOR
		
		Contador contador1 = new Contador();
		
		System.out.println("Contador1 -> Capacidad = " + contador1.getCapacidad() + " // Caudal = " + contador1.getCaudal() + 
				" // Incremento Caudal = " + contador1.getIncCaudal());
		
		contador1.agregarAgua(); contador1.agregarAgua();
		
		System.out.println("Contador1 -> Capacidad = " + contador1.getCapacidad() + " // Caudal = " + contador1.getCaudal() + 
				" // Incremento Caudal = " + contador1.getIncCaudal());
		
		
		
	}

}
