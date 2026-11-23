package socket.sinconexion.simplex;

import java.net.*;


/**
 *    Este ejemplo ilustra las llamadas de metodo basicas para sockets
 *    datagrama sin conexion
 *    @author M. L. Liu
 */
public class Emisor {

//  Una aplicacion que manda un mensaje utilizando un socket datagrama
//  sin conexion.
//  Se esperan tres argumentos de linea de mandato, en orden:
// <nombre del dominio o direccion IP del receptor>
// <numero del puerto del socket del receptor>
// <mensaje, una cadena, para mandar>

	public static void main(String[ ] args) {
      if (args.length != 3)
        System.out.println
          ("Este programa requiere 3 argumentos de linea de mandato");
      else {
        try {
          
          InetAddress maquinaReceptora = InetAddress.getByName(args[0]);
          int puertoReceptor = Integer.parseInt(args[1]);

          
          String mensaje = args[2];

          // instancia un socket datagrama para mandar los datos
          DatagramSocket miSocket = new DatagramSocket( );
          byte[ ] almacen = mensaje.getBytes( );
          DatagramPacket datagrama =
            new DatagramPacket(almacen, almacen.length,
              maquinaReceptora, puertoReceptor);
          while(true){
            System.out.println("Enviando mensaje...");  
            miSocket.send(datagrama);
          }
          //miSocket.close( );
        } // fin de try
        catch (Exception ex) {
          ex.printStackTrace( );
        } // fin de catch
      } // fin de else
    } // fin de main
  } // fin de class
