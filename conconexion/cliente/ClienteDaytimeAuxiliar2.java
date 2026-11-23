package socket.conconexion.cliente;

/**
 * Esta clase es un módulo que proporciona la lógica de aplicación
 * para un cliente Daytime que utiliza un socket en modo stream para IPC.
 * @author M. L. Liu
 */
public class ClienteDaytimeAuxiliar2 {

	public static void recibirMarcasTiempo(String nombreMaquina,
										   String numPuerto) throws Exception {
		int puertoServidor = Integer.parseInt(numPuerto);
		System.out.println("Petición de conexión realizada");
		MiSocketStream miSocket = new MiSocketStream(nombreMaquina, puertoServidor);
		try {
			String marcaTiempo;
			while ((marcaTiempo = miSocket.recibeMensaje()) != null) {
				System.out.println("Aquí está la marca de tiempo recibida del servidor: "
						+ marcaTiempo);
			}
		} finally {
			miSocket.close();
		}
    } // fin
} // fin de class