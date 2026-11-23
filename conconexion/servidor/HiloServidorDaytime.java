package socket.conconexion.servidor;

import java.util.Date;

/**
 * Este módulo está diseñado para usarse con un servidor Daytime concurrente.
 * Su método run lleva a cabo la lógica de una sesión de cliente.
 * @author M. L. Liu
 */
class HiloServidorDaytime implements Runnable {
    MiSocketStream miSocketDatos;

    HiloServidorDaytime (MiSocketStream miSocketDatos) {
    	this.miSocketDatos = miSocketDatos;
    } // fin de constructor

    public void run( ) {
    	try {
    		while (!Thread.currentThread().isInterrupted()) {
    			Date marcaTiempo = new Date();
    			miSocketDatos.enviaMensaje(marcaTiempo.toString());
    			Thread.sleep(5000);
    		}
    	} catch (InterruptedException ex) {
    		Thread.currentThread().interrupt();
    	} // fin de try
    	catch (Exception ex) {
    		System.out.println("Excepción capturada en hilo: " + ex);
    	} finally {
    		try {
    			miSocketDatos.close();
    		} catch (Exception ex) {
    			System.out.println("No se pudo cerrar el socket del cliente: " + ex);
    		}
    	} // fin de catch
    } // fin de run
} // fin de class