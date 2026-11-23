package socket.sinconexion.cliente;

import java.io.*;


/**
 * Este módulo contiene la lógica de presentación de un ClienteDaytime.
 * @author M. L. Liu
 */
public class ClienteDaytime1 {
	public static void main(String[] args) {
		InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        try {
        	System.out.println("Bienvenido al cliente Daytime.\n" +
        			           "¿Cuál es el nombre de la máquina servidora?");
        	String nombreMaquina = br.readLine( );
        	if (nombreMaquina.length() == 0) // si el usuario no introduce un nombre
        		nombreMaquina = "localhost"; // usa el nombre de máquina por defecto
        	System.out.println("¿Cuál es el número de puerto de la máquina servidora?");
        	String numPuerto = br.readLine();
        	if (numPuerto.length () == 0)
        		numPuerto = "13"; // número de puerto por defecto
        	System.out.println("Aquí está la marca de tiempo recibida del servidor: "
        						+ ClienteDaytimeAuxiliar1.obtenerMarcatiempo(nombreMaquina,numPuerto));
        } // fin de try
        catch (Exception ex) {
        	ex.printStackTrace( );
        } // fin de catch
	} // fin de main
} // fin de class
