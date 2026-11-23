package socket.conconexion.servidor;

import java.net.*;

/**
 * Este módulo contiene la lógica de aplicación de un servidor Daytime
 * que utiliza un socket en modo stream para comunicacin entre procesos.
 * A diferencia de ServidorDaytime2, los clientes se sirven concurrentemente.
 * Se requiere un argumento de lnea de mandato para el puerto del servidor.
 * @author M. L. Liu
 */
public class ServidorDaytime3 {
	public static void main(String[] args) {
		int puertoServidor = 13; // puerto por defecto

		if (args.length == 1 )
			puertoServidor = Integer.parseInt(args[0]);
		try {
			// instancia un socket stream para aceptar
			// las conexiones
			ServerSocket miSocketConexion = new ServerSocket(puertoServidor);
			System.out.println("El servidor Daytime está listo.");
			while (true) { // bucle infinito
				// espera para aceptar una conexión
				/**/    System.out.println("Espera una conexión.");
				Socket socketCliente = miSocketConexion.accept();
				System.out.println("Un cliente se ha conectado desde "
						+ socketCliente.getInetAddress().getHostAddress()
						+ ":" + socketCliente.getPort());
				MiSocketStream miSocketDatos = new MiSocketStream(socketCliente);
				// Arranca un hilo para manejar la sesión de cliente 
				Thread elHilo = new Thread(new HiloServidorDaytime(miSocketDatos));
				elHilo.start();
				// y continua con el siguiente cliente 
			} // fin de while infinito
		} // fin de try
		catch (Exception ex) {
			ex.printStackTrace( );
		} // fin de catch
	} // fin de main
} // fin de class