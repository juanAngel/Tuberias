/**
 * 
 */
package juego;

import java.util.Collection;

import entidades.BombaExtrac;
import entidades.BombaInyec;
import entidades.EnemigoAstuto;
import entidades.EnemigoLoco;
import entidades.Jugador;
import estructura.CeldaSegura;
import estructura.Contador;
import estructura.Direccion;
import estructura.EstadoTuberia;
import estructura.Llave;
import estructura.Posicion;
import estructura.Tuberia;
import tuberias.vista.Dibujable;
import tuberias.vista.IControlador;
import tuberias.vista.IPantalla;

/**
 * Gestiona la partida y proporciona la interface {@link IControlador} a la pantalla
 * @author Juan Ángel Sánchez López  - Tomás Gómez Castilla 
 * 
 */
public class Partida implements IControlador{
	private Jugador jugador = null;
	private IPantalla screen = null;
	private Tuberia tuberia = null;
	
	public static final int DEFAULT_ANCHO = 25;
	public static final int DEFAULT_ALTO = 12;
	public static final Posicion DEFALT_INICIAL_POS = new Posicion(1,1);
	/**
	 * Constructor
	 */
	public Partida() {
	}

	/**
	 * Abre un archivo de configuracion de partida
	 */
	@Override
	public void abrir(String arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

	
	/** Actualiza el estado de la partida y la interface grafica
	 * @see tuberias.vista.IControlador#actualizar()
	 */
	@Override
	public void actualizar() {
		assert(tuberia != null);
		tuberia.actualizar();
		long aguaRestante = tuberia.getAguaRestante();
		if(aguaRestante <= 0){
			tuberia.parar();
		}
		StringBuilder stateString = new StringBuilder();
		stateString.append("Restante ").append(aguaRestante)
				.append(" Tiempo ").append(tuberia.getTranscurrido());
		
		screen.setBarraEstado(stateString.toString());
		if(tuberia.getEstado() == EstadoTuberia.FINALIZADA)
			screen.abrirDialogo("Partida", "Partida Finalizada");;
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#actuar()
	 */
	@Override
	public void actuar() {
		assert(jugador != null);
		jugador.actuar();
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#dibujarEscenario()
	 */
	@Override
	public boolean dibujarEscenario() {
		return tuberia != null && tuberia.getEstado() == EstadoTuberia.ARRANCADA;
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#getAltoEscenario()
	 */
	@Override
	public int getAltoEscenario() {
		return tuberia != null?tuberia.getAlto():0;
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#getAnchoEscenario()
	 */
	@Override
	public int getAnchoEscenario() {
		return tuberia != null?tuberia.getAncho():0;
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#getDibujables()
	 */
	@Override
	public Collection<Dibujable> getDibujables() {
		return tuberia.getDibujables();
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#mueveAbajo()
	 */
	@Override
	public void mueveAbajo() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.ABAJO);
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#mueveArriba()
	 */
	@Override
	public void mueveArriba() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.ARRIBA);		
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#mueveDerecha()
	 */
	@Override
	public void mueveDerecha() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.DERECHA);
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#mueveIzquierda()
	 */
	@Override
	public void mueveIzquierda() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.IZQUIERDA);
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#nueva()
	 */
	@Override
	public void nueva() {
		tuberia = new Tuberia(DEFAULT_ANCHO, DEFAULT_ALTO, DEFALT_INICIAL_POS);
		jugador = new Jugador();
		tuberia.setJugador(jugador);
		
		tuberia.crearTubo(new Posicion(DEFALT_INICIAL_POS.getX()+1,DEFALT_INICIAL_POS.getY()), Direccion.DERECHA, 5);
		tuberia.crearTubo(new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()), Direccion.ARRIBA, 9);
		tuberia.crearTubo(new Posicion(DEFALT_INICIAL_POS.getX()+3,DEFALT_INICIAL_POS.getY()+5), Direccion.DERECHA, 5);

		tuberia.setCelda(new CeldaSegura(),DEFALT_INICIAL_POS);
		tuberia.setCelda(new Contador(),new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()+9));
		tuberia.setCelda(new Llave(),new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()-1));
		tuberia.setCelda(new Llave(),new Posicion(DEFALT_INICIAL_POS.getX()+4,DEFALT_INICIAL_POS.getY()));
		
		tuberia.setCelda(new Llave(),new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()+6));
		tuberia.setCelda(new Llave(),new Posicion(DEFALT_INICIAL_POS.getX()+3,DEFALT_INICIAL_POS.getY()+5));

		tuberia.setCelda(new Contador(),new Posicion(DEFALT_INICIAL_POS.getX()+8,DEFALT_INICIAL_POS.getY()+5));

		tuberia.insertarEntidad(new BombaInyec(2), new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()-1));
		tuberia.insertarEntidad(new BombaInyec(2), new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()+5));
		tuberia.insertarEntidad(new BombaExtrac(3), new Posicion(DEFALT_INICIAL_POS.getX()+4,DEFALT_INICIAL_POS.getY()));

		tuberia.insertarEntidad(new EnemigoLoco(), new Posicion(DEFALT_INICIAL_POS.getX()+1,DEFALT_INICIAL_POS.getY()));
		tuberia.insertarEntidad(new EnemigoAstuto(4), new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()+3));
		
		
		tuberia.arrancar();
	}

	/* (sin Javadoc)
	 * @see tuberias.vista.IControlador#setPantalla(tuberias.vista.IPantalla)
	 */
	@Override
	public void setPantalla(IPantalla arg0) {
		screen = arg0;
	}

}
