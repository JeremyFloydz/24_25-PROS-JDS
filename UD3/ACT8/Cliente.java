package uni3ocho;

import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();  // El sistema asignará un puerto automáticamente
            String mensaje = "4";  // Número a enviar al servidor (en este caso, 4)
            byte[] buffer = mensaje.getBytes();

            // Dirección del servidor (localhost)
            InetAddress servidor = InetAddress.getByName("localhost");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, servidor, 12346);

            // Enviamos el número al servidor
            socket.send(packet);

            // Mostramos el mensaje de espera
            System.out.println("Esperando respuesta...");

            // Recibimos la respuesta del servidor
            byte[] bufferRespuesta = new byte[1024];
            DatagramPacket packetRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
            socket.receive(packetRespuesta);

            // Convertimos la respuesta en un String
            String respuesta = new String(packetRespuesta.getData(), 0, packetRespuesta.getLength());
            System.out.println("Esperando respuesta....: el cubo de " + mensaje + " es " + respuesta);
            System.out.println("Adiós...");

        } catch (Exception e) {
            System.err.println("Error al comunicarse con el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
