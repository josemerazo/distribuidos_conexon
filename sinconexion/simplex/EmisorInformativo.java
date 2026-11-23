package socket.sinconexion.simplex;

import java.lang.management.ManagementFactory;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class EmisorInformativo {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso: Emisor <receptor> <puerto> <mensaje>");
            return;
        }

        try {
            InetAddress maquinaReceptora = InetAddress.getByName(args[0]);
            int puertoReceptor = Integer.parseInt(args[1]);

            Runtime runtime = Runtime.getRuntime();
            long memoriaUsada = runtime.totalMemory() - runtime.freeMemory();
            long memoriaMaxima = runtime.maxMemory();

            com.sun.management.OperatingSystemMXBean sistema =
                    ManagementFactory.getPlatformMXBean(
                            com.sun.management.OperatingSystemMXBean.class);
            double cargaCpu = sistema.getProcessCpuLoad();

            String cpu = cargaCpu >= 0
                    ? String.format(Locale.ROOT, "%.2f%%", cargaCpu * 100)
                    : "no disponible";

            String mensaje = String.format(
                    "%s | Memoria JVM: %.2f/%.2f MB | CPU proceso: %s",
                    args[2],
                    memoriaUsada / (1024.0 * 1024.0),
                    memoriaMaxima / (1024.0 * 1024.0),
                    cpu);

            byte[] almacen = mensaje.getBytes(StandardCharsets.UTF_8);
            DatagramPacket datagrama = new DatagramPacket(
                    almacen, almacen.length, maquinaReceptora, puertoReceptor);

            try (DatagramSocket miSocket = new DatagramSocket()) {
                miSocket.send(datagrama);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}