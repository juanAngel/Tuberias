package Test;

import estructura.Llave;

public class Bloque2Test {

	public static void main(String[] args) {
		
		// CLASE LLAVE
		
		Llave llave1 = new Llave();

		System.out.println("Llave1 ->  Abierta = " + llave1.isAbierta() + " // Caudal = " + llave1.getCaudal() + 
				" / Capacidad = " + llave1.getCapacidad());		
		
		llave1.conmutarAbierta(); llave1.añadirAgua(); llave1.extraerAgua();
		
		System.out.println("Llave1 ->  Abierta = " + llave1.isAbierta() + " // Caudal = " + llave1.getCaudal() + 
				" / Capacidad = " + llave1.getCapacidad());	
		
		
		
	}

}
