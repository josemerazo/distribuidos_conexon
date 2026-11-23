package socket.conconexion.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Demuestra exclusión mutua al actualizar un contador compartido
 * desde los hilos que atienden a los clientes.
 */
public class ServidorDaytime5 {
	private static final long INTERVALO_MARCAS_MS = 5000;
	private static final long DURACION_SECCION_CRITICA_MS = 1000;
	private static int contadorMarcas;

	public static void main(String[] args) {
		if (args.length > 1) {
			System.err.println("Uso: java socket.conconexion.servidor.ServidorDaytime5 [puerto]");
			return;
		}

		int puertoServidor = 13;
		try {
			if (args.length == 1) {
				puertoServidor = Integer.parseInt(args[0]);
			}
			if (puertoServidor < 1 || puertoServidor > 65535) {
				System.err.println("El puerto debe estar entre 1 y 65535.");
				return;
			}
		} catch (NumberFormatException ex) {
			System.err.println("El puerto debe ser un número entero.");
			return;
		}

		try (ServerSocket socketServidor = new ServerSocket(puertoServidor)) {
			System.out.println("ServidorDaytime5 listo en el puerto " + puertoServidor + ".");
			while (true) {
				Socket socketCliente = socketServidor.accept();
				System.out.println("Cliente conectado desde "
						+ socketCliente.getInetAddress().getHostAddress()
						+ ":" + socketCliente.getPort());
				Thread hiloCliente = new Thread(
						() -> atenderCliente(socketCliente),
						"cliente-" + socketCliente.getPort());
				hiloCliente.start();
			}
		} catch (IOException ex) {
			System.err.println("Error en el servidor: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private static void atenderCliente(Socket socketCliente) {
		try (MiSocketStream socketDatos = new MiSocketStream(socketCliente)) {
			while (!Thread.currentThread().isInterrupted()) {
				String marca = obtenerMarcaSincronizada();
				socketDatos.enviaMensaje(marca);
				System.out.println("Enviada a " + socketCliente.getPort() + ": " + marca);
				Thread.sleep(INTERVALO_MARCAS_MS);
			}
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		} catch (IOException ex) {
			System.out.println("Cliente desconectado en el puerto "
					+ socketCliente.getPort() + ".");
		}
	}

	private static synchronized String obtenerMarcaSincronizada()
			throws InterruptedException {
		String hilo = Thread.currentThread().getName();
		System.out.println(hilo + " entra en la sección crítica.");
		try {
			Thread.sleep(DURACION_SECCION_CRITICA_MS);
			contadorMarcas++;
			return "Marca global #" + contadorMarcas + " - " + new Date();
		} finally {
			System.out.println(hilo + " sale de la sección crítica.");
		}
	}
}
