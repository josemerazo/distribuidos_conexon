package socket.conconexion.stream;



/**
*    Este ejemplo ilustra la sintaxis basica del socket
*    en modo stream.
*    @author M. L. Liu
*/
public class SolicitanteConexion {

//  Una aplicacion que manda un mensaje usando un socket en modo stream.
//  Se esperan dos argumentos de linea de mandato, en orden:
//
//  <nombre de la maquina del aceptador de la conexion>
//  <numero de puerto del aceptador de la conexion>

  public static void main(String[] args) {
    if (args.length != 2)
      System.out.println
      ("Este programa requiere dos argumentos de linea de mandato");
    else {
      try {
        /*args = new String[2];
        args[0] = "localhost";
        args[1] = "5000";*/
    	  String maquinaAceptadora = args[0];
        int puertoAceptador = Integer.parseInt(args[1]);
        // instancia un socket de datos
        
        MiSocketStream miSocket =
          new MiSocketStream(maquinaAceptadora, puertoAceptador);
/**/    System.out.println("Solicitud de conexion concedida");
        for(int i=0; i<100; i++){
        String mensaje = miSocket.recibeMensaje( );
/**/    System.out.println("Mensaje recibido:");
        System.out.println("\t" + mensaje);
        }
        miSocket.close( );
/**/    //System.out.println("socket de datos cerrado");
      } // fin de try
      catch (Exception ex) {
        ex.printStackTrace( );
      }
    } // fin de else
  } // fin de main
} // fin de class
