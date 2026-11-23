package socket.conconexion.stream;

import java.net.*;


/**
*    Este ejemplo ilustra la sintaxis basica del socket
*    en modo stream.
*    @author M. L. Liu
*/
public class AceptadorConexion {

// Una aplicacion que recibe un mensaje usando un socket en modo stream.
//  Se esperan dos argumentos de linea de mandato, en orden:
//  <numero de puerto del socket de servidor utilizado en este proceso>
//  <mensaje, una cadena, para mandar>

  public static void main(String[] args) {
    if (args.length != 2)
      System.out.println
      ("Este programa requiere dos argumentos de linea de mandato");
    else {
      try {
        /*args = new String[2];
        args[0] = "5000";
        args[1] = "hola mundo";*/
    	int numPuerto = Integer.parseInt(args[0]);
        String mensaje = args[1];
        // instancia un socket para aceptar la conexion
        ServerSocket socketConexion = new ServerSocket(numPuerto);
        while (true) {
/**/    System.out.println("preparado para aceptar una conexion");
        // espera una peticion de conexion, instante en el cual
        // se crea un socket de datos
        MiSocketStream socketDatos =
          new MiSocketStream(socketConexion.accept());
/**/    System.out.println("conexion aceptada");
        for(int i=0; i<100; i++){
        socketDatos.enviaMensaje(mensaje);
/**/    System.out.println("mensaje enviado");
        }}
        //socketDatos.close( );
/**/    //System.out.println("socket de datos cerrado");
        //socketConexion.close( );
/**/    //System.out.println("socket de conexion cerrado");
      } // fin de try
      catch (Exception ex) {
        ex.printStackTrace( );
      } // fin de catch
    } // fin de else
  } // fin de main
} // fin de class