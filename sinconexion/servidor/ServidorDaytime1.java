package socket.sinconexion.servidor;

import java.util.Date; // para obtener una marca de tiempo
import java.text.DateFormat; // para dar formato a la marca de tiempo

/**
 * Este módulo contiene la lógica de aplicación de un servidor Daytime 
 * que utiliza un socket datagrama para la comunicación entre procesos.
 * Se requiere un argumento de línea de mandato para el puerto del servidor.
 * @author M. L. Liu
*/
public class ServidorDaytime1 {
	public static void main(String[] args) {
		int puertoServidor = 13; // puerto por defecto
		if (args.length == 1 )
			puertoServidor = Integer.parseInt(args[0]);
		try {
			// instancia un socket datagrama para tanto enviar como
			// recibir datos
			MiSocketDatagramaServidor miSocket = new MiSocketDatagramaServidor(puertoServidor);
			System.out.println("El servidor Daytime esta listo.");
			while (true) { // bucle infinito
				MensajeDatagrama peticion = miSocket.recibeMensajeYEmisor();
				System.out.println("Petición recibida");
				// no es importante el mensaje recibido; es la dirección
				// del emisor lo que se necesita para responder..
				// Ahora obtiene una marca de tiempo del sistema.
				Date marcaTiempo = new Date ( );
				DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL);
				System.out.println("marca de tiempo enviada:" + df.format(marcaTiempo));
				// Ahora envia la respuesta al solicitante.
				miSocket.enviaMensaje(peticion.obtieneDireccion( ), peticion.obtienePuerto( ), 
									  df.format(marcaTiempo));
			} // fin de while
		} // fin de try
		catch (Exception ex) {
			ex.printStackTrace( );
		} // fin de catch
	} // fin de main
} // fin de class
