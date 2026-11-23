package socket.conconexion.unilateral;

import java.net.*;


/**
*    Este ejemplo ilustra la sintaxis basica de los sockets datagrama
*    orientados a conexion.
*    @author M. L. Liu
*/
public class Emisor {

//  Una aplicacion que utiliza un socket datagrama orientado a conexion
//  para mandar multiples mensajes, despues recibe uno.
//  Se esperan cuatro argumentos de linea de mandato, en orden:
//  <nombre de dominio o direccin IP del receptor>
//  <nmero de puerto del socket datagrama del otro proceso>
//  <nmero de puerto del socket datagrama de este proceso>
//  <mensaje, una cadena, para mandar>

 public static void main(String[] args) {
   if (args.length != 4)
     System.out.println
     ("Este programa requiere 4 argumentos de linea de mandato");
   else {
     try {
    	 
     	/*args = new String[4];
     	args[0] = "127.0.0.1";
     	args[1] = "4000";
     	args[2] = "5000";
     	args[3] = "hola";*/
     	
       InetAddress maquinaReceptora = InetAddress.getByName(args[0]);
       int puertoReceptor = Integer.parseInt(args[1]);
       int miPuerto = Integer.parseInt(args[2]);
       String mensaje = args[3];
       // instancia una socket datagrama para la conexion
       MiSocketDatagrama miSocket = new MiSocketDatagrama(miPuerto);
       // hace la conexion
       miSocket.connect(maquinaReceptora, puertoReceptor);
       for (int i=0; i<10; i++)
         miSocket.enviaMensaje( maquinaReceptora, puertoReceptor, mensaje);
       // ahora recibe un mensaje desde el otro extremo
       System.out.println(miSocket.recibeMensaje( ));
       // termina la conexion, despues cierra el socket
       miSocket.disconnect( );
       miSocket.close( );
     } // fin de try
     catch (Exception ex) {
       ex.printStackTrace( );
     } // fin de catch
   } // fin de else
 } // fin de main
} // fin de class
