package estructura;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
	
	/**
	 * Construlle una tuberia con los parametros indicados
	 * @param ancho de la tuberia
	 * @param alto de la tuberia
	 * @param posCeldaInicial {@link Posicion} de la primera {@linkplain Celda} de la {@linkplain Tuberia}
	 */
	public Tuberia(int ancho,int alto,Posicion posCeldaInicial){
		this(ancho, alto, posCeldaInicial, 200);
	}
	/**
	 * Crea una {@link Tuberia}
	 * @param ancho Indica la altura maxima
	 * @param alto Indica el ancho maximo
	 * @param posCeldaInicial Indica la {@link Posicion} inicial
	 * @param maxAgua Maxima cantidad de agua que se permite escapar de la tuberia
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
	
	/**
	 * Pone en estado de {@link EstadoTuberia#ARRANCADA} la {@linkplain Tuberia}
	 * Solo se permite su uso si se estaba en estado de {@link EstadoTuberia#NO_INICIADO}
	 */
	public void arrancar(){
		assert(estado == EstadoTuberia.NO_INICIADO);
		if(estado != EstadoTuberia.NO_INICIADO)
			throw new IllegalStateException();
		estado = EstadoTuberia.ARRANCADA;
		inicioTimeStamp = System.currentTimeMillis();
	}
	/**
	 * Pone en estado de {@link EstadoTuberia#FINALIZADA} la {@linkplain Tuberia}
	 * Solo se permite su uso si se estaba en estado de {@link EstadoTuberia#ARRANCADA}
	 */
	public void parar(){
		assert(estado == EstadoTuberia.ARRANCADA);
		if(estado != EstadoTuberia.ARRANCADA)
			throw new IllegalStateException();
		estado = EstadoTuberia.FINALIZADA;
		System.out.println("FIN PARTIDA");
	}
	/**
	 * Ejecuta el {@linkplain Entidad#turno()} de las entidades de la tuberia
	 */
	public void actualizar(){
		for (Entidad entidad : entidades) {
			entidad.turno();
		}
	}
	/**
	 * Establece el {@linkplain Jugador}
	 * @param j Jugador a asignar
	 */
	public void setJugador(Jugador j){
		assert(estado == EstadoTuberia.NO_INICIADO);
		if(estado != EstadoTuberia.NO_INICIADO)
			throw new IllegalStateException();
		jugador = j;
		insertarEntidad(jugador,posInicial);
	}
	// METODOS GET Y SET

	/**
	 * @return el Ancho de la tuberia
	 */
	public int getAncho(){
		return matriz[0].length;
	}
	
	/**
	 * @return el Alto de la tuberia
	 */
	public int getAlto(){
		return matriz.length;
	}
	/**
	 * @return El tiempo en segundos desde que la tuberia esta en {@link EstadoTuberia#ARRANCADA}
	 */
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
	/**
	 * @return El agua que queda por salir
	 */
	public long getAguaRestante() {
		long restante = getMaximoAgua()-getAguaEscapada();
		return restante<0?0:restante;
	}
	/**
	 * @return El agua que a salido
	 */
	public long getAguaEscapada() {
		long result = 0;
		for (Contador contador : contadores) {
			result += contador.getIncCaudal();
		}
		
		return result;
	}
	
	/**
	 * Pone una celda en la posicion indicada. La posicion indicada tiene que tener vecina
	 * @param celda Celda a esablecer en la tuberia
	 * @param pos Posicion donde se pondra la nueva celda
	 * @return TRUE si se pudo establecer la celda en la posicion indicada, FALSE en caso contrario
	 */
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
	
	/**
	 * @param pos Posicion de la celda deseada
	 * @return La {@link Celda} que hay en la posicion indicada y null si no la hay
	 */
	public Celda getCelda(Posicion pos){
		Celda celda = null;
		if(pos.getX()>= 0 && pos.getY()>= 0 
				&& pos.getX()< getAncho() && pos.getY()<getAlto()){
			celda = matriz[pos.getY()][pos.getX()];
		}
		return celda;
	}
	
	/**
	 * Devuelve la celda vecina que hay en la posicion pos y la direccion dir
	 * @param pos Posicion de la delda de donde se quiere la vecina
	 * @param dir Direcion de la vecina
	 * @return la {@link Celda} vecina
	 */
	public Celda getVecina(Posicion pos,Direccion dir){
		return getCelda(pos.adyacente(dir));
	}
	

	// METODOS
	
	/**
	 * @return el {@linkplain EstadoTuberia} de la tuberia
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

	/**
	 * @param pos Posicion de la delda de donde se quiere la vecina
	 * @param dir Direcion de la vecina
	 * @return TRUE si existe una Celda vecina en la posicion pos y direccion dir
	 */
	public boolean hayVecina(Posicion pos,Direccion dir){
		return getCelda(pos.adyacente(dir)) != null;
	}
	
	/**
	 * Crea un tubo, que es una sucesion de Celdas en linea recta
	 * @param pos posicion inicial de el tubo
	 * @param dir Direccion hacia la que crece
	 * @param largo del tubo
	 */
	public void crearTubo(Posicion pos,Direccion dir,int largo){
		Posicion posFinal = pos.desplazar(dir, largo);
		
		//Compruevo que el tubo no se salga
		if(matriz.length > posFinal.getY() && matriz[0].length > posFinal.getX()
				&& pos.getX() >= 0 && pos.getY() >= 0){
			for(Posicion posActual = pos;largo != 0;largo--,posActual = posActual.adyacente(dir)){
				setCelda(new Celda(), posActual);
			}
		}
	}
	
	/**
	 * Inserta una {@linkplain Entidad} en la tuberia
	 * @param e {@linkplain Entidad} a ser insertada
	 * @param p {@linkplain Posicion} a ser insertada
	 */
	public void insertarEntidad(Entidad e,Posicion p){
		Celda celda = getCelda(p);
		if(celda != null){
			e.setTuberia(this);
			e.setPosActual(p);
			entidades.add(e);
			dibujables.add(dibujables.size(),e);
		}
	}
	
	/**
	 * Saca una entidad de la tuberia
	 * @param e {@linkplain Entidad} que se quiere sacar
	 */
	public void sacarEntidad(Entidad e){
		if (entidades.contains(e)) {
			if(jugador == e)
				parar();
			dibujables.remove(e);
			entidades.remove(e);
			e.setPosActual(null);
			e.setTuberia(null);
		}
	}

	/**
	 * @return Una coleccion de {@link Dibujable} que perite representar la {@link Tuberia} y su contenido
	 */
	@SuppressWarnings("unchecked")
	public Collection<Dibujable> getDibujables() {
		return (Collection<Dibujable>) dibujables.clone();
	}
	
}
