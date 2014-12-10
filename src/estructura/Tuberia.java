package estructura;

import java.util.Set;


/**
 * Esta clase representa la tuberia que no es mas que un grupo de celdas unidas
 * entre si.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

public class Tuberia {
	
	// ATRIBUTOS
	
	private Celda matriz[][];
	private Set<Entidad> entidades;
	
	// CONSTRUCTOR
	
	public Tuberia(int ancho,int alto,Posicion celdaInicial) {
		matriz = new Celda[alto][ancho];
		Celda newCelda = new Celda();
		//Pongo la celda inicial
		newCelda.setPosicion(celdaInicial);
		matriz[celdaInicial.getY()][celdaInicial.getX()] = newCelda;
	}
	
	// METODOS GET Y SET

	public int getAncho(){
		return matriz[0].length;
	}
	
	public int getAlto(){
		return matriz.length;
	}
	
	public boolean setCelda(Celda celda,Posicion pos){
		boolean status = false;
		Direccion dirAdy = null;
		if(getCelda(pos) == null){
			Direccion direciones[] = Direccion.values();
			Posicion posTemp;
			boolean multipleVecinas = false;
			//Busco y compruebo qu solo tenga una ceda vecina
			for(int i = 0;i<direciones.length && !multipleVecinas;i++){
				if(hayVecina(pos, direciones[i])){
					if(dirAdy == null){
						dirAdy = direciones[i];
					}else{
						multipleVecinas = true;
						dirAdy = null;
					}
				}
			}
			if(dirAdy != null){
				posTemp = pos.adyacente(dirAdy);
				Celda vecina = getCelda(posTemp);
				if(celda != null){
					celda.resetVecinas();
				
					//seteo la vecindad y la posicion
					celda.establecerVecina(dirAdy, vecina);
					celda.setPosicion(pos);
				}
				
				vecina.establecerVecina(dirAdy.opuesta(), celda);

				matriz[pos.getY()][pos.getX()] = celda;

				status = true;
			}
		}else{
			if(celda != null){
				celda.resetVecinas();
				celda.setPosicion(pos);
			}
			
			Direccion direciones[] = Direccion.values();
			Celda celVecina;
			//Seteo la vecindad de todos los alrededores
			for(int i = 0;i<direciones.length;i++){
				dirAdy = direciones[i];
				celVecina = setVecina(pos,dirAdy);
				if(celVecina != null){
					if(celda != null)
						celda.establecerVecina(dirAdy, celVecina);
					celVecina.establecerVecina(dirAdy.opuesta(), celda);
				}
			}
			
			matriz[pos.getY()][pos.getX()] = celda;
			status = true;
		}
		return status;
	}
	
	public Celda getCelda(Posicion pos){
		return matriz[pos.getY()][pos.getX()];
	}
	
	public Celda setVecina(Posicion pos,Direccion dir){
		return getCelda(pos.adyacente(dir));
	}
	

	// METODOS
	
	public boolean hayVecina(Posicion pos,Direccion dir){
		return getCelda(pos.adyacente(dir)) != null;
	}
	
	public void crearTubo(Posicion pos,Direccion dir,int largo){
		Posicion posFinal = pos.desplazar(dir, largo);
		
		//Compruevo que el tubo no se salga
		if(matriz.length>posFinal.getY() && matriz[0].length > posFinal.getX()){
			for(Posicion posActual = pos;largo != 0;largo--,posActual = posActual.adyacente(dir)){
				setCelda(new Celda(), posActual);
			}
		}
	}
	
	public void insertarEntidad(Entidad e,Posicion p){
		Celda celda = getCelda(p);
		if(celda != null){
			e.setTuberia(this);
			e.setPosActual(p);
			entidades.add(e);
		}
	}
	
	public void sacarEntidad(Entidad e){
		if (entidades.contains(e)) {
			e.setPosActual(null);
			e.setTuberia(null);
		}
	}
}
