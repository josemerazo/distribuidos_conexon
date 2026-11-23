package socket.conconexion.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Locale;

/**
 * Demuestra dos formas de atender clientes:
 * estado conserva una sesión y envía marcas cada cinco segundos;
 * sin-estado responde una vez por conexión y la cierra.
 */
public class ServidorDaytime4 {
	private static final long INTERVALO_MARCAS_MS = 5000;

	public static void main(String[] args) {
		if (args.length < 1 || args.length > 2) {
			mostrarUso();
			return;
		}

		String modo = args[0].toLowerCase(Locale.ROOT);
		if (!modo.equals("estado") && !modo.equals("sin-estado")) {
			mostrarUso();
			return;
		}

		int puertoServidor = 13;
		try {
			if (args.length == 2) {
				puertoServidor = Integer.parseInt(args[1]);
			}
			if (puertoServidor < 1 || puertoServidor > 65535) {
				System.err.println("El puerto debe estar entre 1 y 65535.");
				return;
			}
		} catch (NumberFormatException ex) {
			System.err.println("El puerto debe ser un número entero.");
			mostrarUso();
			return;
		}

		try (ServerSocket socketServidor = new ServerSocket(puertoServidor)) {
			System.out.println("Servidor Daytime en modo " + modo
					+ ", puerto " + puertoServidor + ".");
			while (true) {
				Socket socketCliente = socketServidor.accept();
				System.out.println("Cliente conectado desde "
						+ socketCliente.getInetAddress().getHostAddress()
						+ ":" + socketCliente.getPort());
				Thread hiloCliente = new Thread(
						() -> atenderCliente(socketCliente, modo),
						"daytime-" + modo + "-" + socketCliente.getPort());
				hiloCliente.start();
			}
		} catch (IOException ex) {
			System.err.println("Error en el servidor: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	private static void atenderCliente(Socket socketCliente, String modo) {
		try (MiSocketStream socketDatos = new MiSocketStream(socketCliente)) {
			if (modo.equals("sin-estado")) {
				socketDatos.enviaMensaje(new Date().toString());
				System.out.println("Respuesta sin estado enviada; conexión cerrada.");
				return;
			}

			int numeroMarca = 0;
			while (!Thread.currentThread().isInterrupted()) {
				numeroMarca++;
				String marcaTiempo = new Date().toString();
				socketDatos.enviaMensaje("Marca #" + numeroMarca + ": " + marcaTiempo);
				System.out.println("Sesión con estado: enviada marca #" + numeroMarca
						+ " al puerto remoto " + socketCliente.getPort());
				Thread.sleep(INTERVALO_MARCAS_MS);
			}
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		} catch (IOException ex) {
			System.err.println("Conexión con el cliente "
					+ socketCliente.getInetAddress().getHostAddress()
					+ ":" + socketCliente.getPort() + " finalizada: "
					+ ex.getMessage());
		}
	}

	private static void mostrarUso() {
		System.err.println("Uso: java socket.conconexion.servidor.ServidorDaytime4 "
				+ "<estado|sin-estado> [puerto]");
		System.err.println("El puerto es opcional; por defecto es 13.");
	}
}
