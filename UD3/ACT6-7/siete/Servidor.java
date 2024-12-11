package uni3siete;

import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        final int PUERTO = 6013; // Puerto donde escucha el servidor
        int contadorClientes = 0; // Contador de clientes atendidos
        final int MAX_CLIENTES = 3; // Número máximo de clientes permitidos

        System.out.println("Esperando a los clientes.....");

        try (ServerSocket servidorSocket = new ServerSocket(PUERTO)) {
            while (contadorClientes < MAX_CLIENTES) {
                // Aceptar conexión del cliente
                Socket clienteSocket = servidorSocket.accept();
                contadorClientes++;

                System.out.println("Cliente " + contadorClientes + " conectado.");

                // Crear flujos de entrada/salida
                DataOutputStream flujoSalida = new DataOutputStream(clienteSocket.getOutputStream());

                // Enviar mensaje de saludo al cliente
                String saludo = "Hola cliente " + contadorClientes;
                flujoSalida.writeUTF(saludo);
                System.out.println("Saludo enviado al cliente " + contadorClientes);

                // Cerrar los flujos y el socket del cliente
                flujoSalida.close();
                clienteSocket.close();
            }

            System.out.println("Se han atendido los " + MAX_CLIENTES + " clientes permitidos. Cerrando servidor...");
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
