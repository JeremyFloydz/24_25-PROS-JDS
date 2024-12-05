package uni3ocho;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(12346);  // Puerto para recibir los datagramas
            System.out.println("Esperando datagrama...");

            // Preparamos el buffer para recibir el datagrama
            byte[] buffer = new byte[1024];
            DatagramPacket packetRecibido = new DatagramPacket(buffer, buffer.length);

            // Esperamos el datagrama
            socket.receive(packetRecibido);

            // Convertimos el dato recibido en un número entero
            String mensaje = new String(packetRecibido.getData(), 0, packetRecibido.getLength());
            int numero = Integer.parseInt(mensaje);

            System.out.println("Vamos a calcular el cubo de: " + numero);
            System.out.println("IP de origen: " + packetRecibido.getAddress().getHostAddress());
            System.out.println("Puerto de origen: " + packetRecibido.getPort());

            // Calculamos el cubo del número
            int cubo = numero * numero * numero;

            // Enviamos el resultado al cliente
            String resultado = String.valueOf(cubo);
            byte[] respuesta = resultado.getBytes();
            DatagramPacket packetRespuesta = new DatagramPacket(respuesta, respuesta.length, packetRecibido.getAddress(), packetRecibido.getPort());
            socket.send(packetRespuesta);

            System.out.println("Enviamos el resultado..." + cubo);
            System.out.println("Adiósss");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
