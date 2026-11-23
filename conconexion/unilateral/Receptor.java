package socket.conconexion.unilateral;

import java.net.*;


/**
*    Este ejemplo ilustra la sintaxis basica de los sockets datagrama
*    orientados a conexion.
*    @author M. L. Liu
*/
public class Receptor {

//  Una aplicacion que utiliza un socket datagrama orientado a conexion
//  para recibir multiples mensajes, despues envia uno.
//  Se esperan cuatro argumentos de linea de mandato, en orden:
//  <nombre de dominio o direccion IP del emisor>
//  <numero de puerto del socket datagrama del emisor>
//  <numero de puerto del socket datagrama de este proceso>
//  <mensaje, una cadena, para mandar>

 public static void main(String[ ] args) {
   if (args.length != 4)
     System.out.println
     ("Este programa requiere 4 argumentos de linea de mandato");
   else {
     try {
    	/*args = new String[4];
    	args[0] = "127.0.0.1";
    	args[1] = "5000";
    	args[2] = "4000";
    	args[3] = "hola";*/
    	
       InetAddress maquinaEmisora = InetAddress.getByName(args[0]);
       int puertoEmisor = Integer.parseInt(args[1]);
       int miPuerto = Integer.parseInt(args[2]);
       String mensaje = args[3];
       // instancia un socket datagrama para recibir los datos
       MiSocketDatagrama miSocket = new MiSocketDatagrama(miPuerto);
       // hace una conexion con el socket del emisor 
       miSocket.connect(maquinaEmisora, puertoEmisor);
       for (int i=0; i<10; i++)
         System.out.println(miSocket.recibeMensaje( ));
       // ahora manda un mensaje al otro extremo
       miSocket.enviaMensaje( maquinaEmisora, puertoEmisor, mensaje);
       miSocket.close( );
     } // fin de try
     catch (Exception ex) {
       ex.printStackTrace( );
     } // fin de catch
   } // fin de else
 } // fin de main
} // fin de class

