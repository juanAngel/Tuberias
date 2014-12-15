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
import estructura.Llave;
import estructura.Posicion;
import estructura.Tuberia;
import tuberias.vista.Dibujable;
import tuberias.vista.IControlador;
import tuberias.vista.IPantalla;

/**
 * @author juan
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
	 * 
	 */
	public Partida() {
	}

	@Override
	public void abrir(String arg0) {
		// TODO Apéndice de método generado automáticamente
		
	}

	@Override
	public void actualizar() {
		assert(tuberia != null);
		tuberia.actualizar();
		StringBuilder stateString = new StringBuilder();
		stateString.append("Restante ").append(tuberia.getAguaEscapada())
				.append(" Tiemo ").append(tuberia.getTranscurrido());
		
		screen.setBarraEstado(stateString.toString());
		if(tuberia.getEstado() == EstadoTuberia.FINALIZADA)
			screen.abrirDialogo("Partida", "Partida Finalizada");;
	}

	@Override
	public void actuar() {
		assert(jugador != null);
		jugador.actuar();
	}

	@Override
	public boolean dibujarEscenario() {
		return tuberia != null && tuberia.getEstado() == EstadoTuberia.ARRANCADA;
	}

	@Override
	public int getAltoEscenario() {
		return tuberia != null?tuberia.getAlto():0;
	}

	@Override
	public int getAnchoEscenario() {
		return tuberia != null?tuberia.getAncho():0;
	}

	@Override
	public Collection<Dibujable> getDibujables() {
		return tuberia.getDibujables();
	}

	@Override
	public void mueveAbajo() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.ABAJO);
	}

	@Override
	public void mueveArriba() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.ARRIBA);		
	}

	@Override
	public void mueveDerecha() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.DERECHA);
	}

	@Override
	public void mueveIzquierda() {
		assert(jugador != null);
		jugador.solicitudMovimiento(Direccion.IZQUIERDA);
	}

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

		tuberia.insertarEntidad(new BombaInyec(1), new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()-1));
		tuberia.insertarEntidad(new BombaExtrac(3), new Posicion(DEFALT_INICIAL_POS.getX()+4,DEFALT_INICIAL_POS.getY()));

		tuberia.insertarEntidad(new EnemigoLoco(), new Posicion(DEFALT_INICIAL_POS.getX()+1,DEFALT_INICIAL_POS.getY()));
		tuberia.insertarEntidad(new EnemigoAstuto(2), new Posicion(DEFALT_INICIAL_POS.getX()+2,DEFALT_INICIAL_POS.getY()+5));
		
		
		tuberia.arrancar();
	}

	@Override
	public void setPantalla(IPantalla arg0) {
		screen = arg0;
	}

}
