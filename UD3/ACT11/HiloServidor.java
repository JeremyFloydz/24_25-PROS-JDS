package paquete2;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {
    private Socket socket;

    public HiloServidor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            String mensaje;
            while ((mensaje = input.readLine()) != null) {
                // Mostrar datos del socket
                System.out.println("Comunico con: " + socket);

                // Si el mensaje es "*", finalizar la conexión
                if (mensaje.equals("*")) {
                    System.out.println("Fin de la conexión con: " + socket);
                    break;
                }

                // Convertir a mayúsculas y enviar de vuelta al cliente
                output.println(mensaje.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
