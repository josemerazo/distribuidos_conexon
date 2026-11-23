  package socket.sinconexion.duplex;
  import java.net.*;
  import java.io.*;
   /**
   *    Una subclase de DatagramSocket que contiene
   *    metodos para mandar y recibir mensajes.
   *    @author M. L. Liu
   */
   public class MiSocketDatagrama extends DatagramSocket {
    static final int MAX_LON = 10;
    private DatagramPacket ultimoDatagrama;
    
    MiSocketDatagrama(int numPuerto) throws SocketException {
      super(numPuerto);
    }
    public void enviaMensaje(InetAddress maquinaReceptora, int puertoReceptor,
        String mensaje) throws IOException {

      byte[ ] almacenEnvio = mensaje.getBytes( );
      DatagramPacket datagrama =
        new DatagramPacket(almacenEnvio, almacenEnvio.length,
        maquinaReceptora, puertoReceptor);
      this.send(datagrama);
    } // fin de enviaMensaje

    public String recibeMensaje( )
        throws IOException {
      byte[ ] almacenRecepcion = new byte[MAX_LON];
      ultimoDatagrama =
        new DatagramPacket(almacenRecepcion, MAX_LON);
      this.receive(ultimoDatagrama);
      String mensaje = new String(almacenRecepcion);
      return mensaje;
    } // fin de recibeMensaje
    
    public String obtenerIPRemitente() {
      if (ultimoDatagrama != null) {
        return ultimoDatagrama.getAddress().getHostAddress();
      }
      return null;
    } // fin de obtenerIPRemitente
    
    public int obtenerPuertoRemitente() {
      if (ultimoDatagrama != null) {
        return ultimoDatagrama.getPort();
      }
      return -1;
    } // fin de obtenerPuertoRemitente
  } // fin de class
