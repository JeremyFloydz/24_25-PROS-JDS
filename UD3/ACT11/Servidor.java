package paquete2;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        try {
            // Crear el servidor escuchando en el puerto 6000
            serverSocket = new ServerSocket(6000);
            System.out.println("Servidor escuchando en puerto 6000...");

            while (true) {
                // Esperar a un cliente
                clientSocket = serverSocket.accept();
                System.out.println("Cliente Conectado.....");

                // Crear un nuevo hilo para manejar al cliente
                HiloServidor hilo = new HiloServidor(clientSocket);
                hilo.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

