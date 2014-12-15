package estructura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import tuberias.vista.Dibujable;
import entidades.Jugador;
import juego.Entidad;


/**
 * Esta clase representa la tuberia que no es mas que un grupo de {@link Celda} unidas
 * entre si.
 * 
 * @author Juan Angel Sanchez Lopez - Tomás Gómez Castilla 
 * 
 */

public class Tuberia {
	
	// ATRIBUTOS
	private EstadoTuberia estado = EstadoTuberia.NO_INICIADO;
	private Posicion posInicial;
	private Celda matriz[][];
	private Jugador jugador;
	
	private long inicioTimeStamp;
	final private long maximoAgua;

	private Set<Entidad> entidades = new CopyOnWriteArraySet<Entidad>();
	private Set<Contador> contadores = new HashSet<Contador>();
	private ArrayList<Dibujable> dibujables = new ArrayList<Dibujable>();
	
	// CONSTRUCTOR
	
	public Tuberia(int ancho,int alto,Posicion posCeldaInicial){
		this(ancho, alto, posCeldaInicial, 200);
	}
	/**
	 * Crea una {@link Tuberia}
	 * @param ancho Indica la altura maxima
	 * @param alto Indica el ancho maximo
	 * @param celda Inicial Indica la {@link Posicion} inicial
	 */
	public Tuberia(int ancho,int alto,Posicion posCeldaInicial,long maxAgua) {
		maximoAgua = maxAgua;
		matriz = new Celda[alto][ancho];
		Celda newCelda = new Celda();
		//Pongo la celda inicial
		this.posInicial = posCeldaInicial;
		newCelda.setPosicion(posCeldaInicial);
		matriz[posCeldaInicial.getY()][posCeldaInicial.getX()] = newCelda;
		dibujables.add(newCelda);
	}
	
	public void arrancar(){
		assert(estado == EstadoTuberia.NO_INICIADO);
		estado = EstadoTuberia.ARRANCADA;
		inicioTimeStamp = System.currentTimeMillis();
	}
	public void parar(){
		assert(estado == EstadoTuberia.ARRANCADA);
		estado = EstadoTuberia.FINALIZADA;
		System.out.println("FIN PARTIDA");
	}
	public void actualizar(){
		for (Entidad entidad : entidades) {
			entidad.turno();
		}
	}
	public void setJugador(Jugador j){
		assert(estado == EstadoTuberia.NO_INICIADO);
		jugador = j;
		insertarEntidad(jugador,posInicial);
	}
	// METODOS GET Y SET

	public int getAncho(){
		return matriz[0].length;
	}
	
	public int getAlto(){
		return matriz.length;
	}
	public long getTranscurrido(){
		assert(estado == EstadoTuberia.ARRANCADA);
		return (System.currentTimeMillis()-inicioTimeStamp)/1000;
	}
	/**
	 * @return el maximoAgua
	 */
	public long getMaximoAgua() {
		return maximoAgua;
	}
	public long getAguaRestante() {
		return getMaximoAgua()-getAguaEscapada();
	}
	public long getAguaEscapada() {
		long result = 0;
		for (Contador contador : contadores) {
			result += contador.getIncCaudal();
		}
		
		return result;
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
					celda.setVecina(dirAdy, vecina);
					celda.setPosicion(pos);
					if (celda instanceof Contador) {
						Contador contador = (Contador) celda;
						contadores.add(contador);
					}
					dibujables.add(0,celda);
				}
				
				vecina.setVecina(dirAdy.opuesta(), celda);

				matriz[pos.getY()][pos.getX()] = celda;

				status = true;
			}
		}else{
			Celda oldCelda = matriz[pos.getY()][pos.getX()];
			if(celda != null){
				celda.resetVecinas();
				celda.setPosicion(pos);
				

				if (celda instanceof Contador) {
					Contador contador = (Contador) celda;
					contadores.add(contador);
				}
				dibujables.add(0,celda);
			}
			
			Direccion direciones[] = Direccion.values();
			Celda celVecina;
			//Seteo la vecindad de todos los alrededores
			for(int i = 0;i<direciones.length;i++){
				dirAdy = direciones[i];
				celVecina = getVecina(pos,dirAdy);
				if(celVecina != null){
					if(celda != null)
						celda.setVecina(dirAdy, celVecina);
					celVecina.setVecina(dirAdy.opuesta(), celda);
				}
			}
			

			if (celda instanceof Contador) {
				Contador contador = (Contador) celda;
				contadores.add(contador);
			}
			matriz[pos.getY()][pos.getX()] = celda;
			dibujables.remove(oldCelda);
			if (oldCelda instanceof Contador) {
				Contador contador = (Contador) oldCelda;
				contadores.remove(contador);
			}
			
			status = true;
		}
		return status;
	}
	
	public Celda getCelda(Posicion pos){
		Celda celda = null;
		if(pos.getX()>= 0 && pos.getY()>= 0 
				&& pos.getX()< getAncho() && pos.getY()<getAlto()){
			celda = matriz[pos.getY()][pos.getX()];
		}
		return celda;
	}
	
	public Celda getVecina(Posicion pos,Direccion dir){
		return getCelda(pos.adyacente(dir));
	}
	

	// METODOS
	
	/**
	 * @return el estado
	 */
	public EstadoTuberia getEstado() {
		return estado;
	}

	/**
	 * @param estado el estado a establecer
	 *//*
	public void setEstado(EstadoTuberia estado) {
		this.estado = estado;
	}*/

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
			dibujables.add(dibujables.size(),e);
		}
	}
	
	public void sacarEntidad(Entidad e){
		if (entidades.contains(e)) {
			if(jugador == e)
				parar();
			synchronized (dibujables) {
				dibujables.remove(e);
				entidades.remove(e);
				//e.setPosActual(null);
				//e.setTuberia(null);
			}
		}
	}

	/**
	 * @return el dibujables
	 */
	public Collection<Dibujable> getDibujables() {
		synchronized (dibujables) {
			return new CopyOnWriteArrayList<Dibujable>(dibujables);
		}
	}

	/**
	 * @param dibujables el dibujables a establecer
	 */
	/*
	public void setDibujables(TreeSet<Dibujable> dibujables) {
		this.dibujables = new TreeSet<Dibujable>(dibujables);
	}*/
	
}
