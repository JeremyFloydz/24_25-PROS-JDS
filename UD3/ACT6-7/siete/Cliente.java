package uni3siete;

import java.io.*;
import java.net.*;

public class Cliente {

    public static void main(String[] args) {
        final String HOST = "localhost"; // Cambiar si el servidor está en otra máquina
        final int PUERTO = 6013; // Puerto del servidor

        System.out.println("PROGRAMA CLIENTE INICIANDO");

        try (Socket socket = new Socket(HOST, PUERTO)) {
            // Crear flujo de entrada para recibir datos
            DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());

            // Leer y mostrar el mensaje del servidor
            String mensajeRecibido = flujoEntrada.readUTF();
            System.out.println("Recibiendo mensaje del servidor:");
            System.out.println("\t" + mensajeRecibido);

            // Cerrar flujo y socket
            flujoEntrada.close();
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
