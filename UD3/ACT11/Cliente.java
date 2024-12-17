package paquete2;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 6000);
             BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("PROGRAMA CLIENTE INICIANDO");
            String cadena;

            // Ciclo para enviar mensajes al servidor
            while (true) {
                System.out.print("Introduce una cadena: ");
                cadena = input.readLine();
                output.println(cadena);

                // Si el cliente envía "*", terminar
                if (cadena.equals("*")) {
                    String respuesta = serverInput.readLine();
                    System.out.println("=>Respuesta:" + respuesta);
                    System.out.println("Fin del envío....");
                    break;
                }

                // Mostrar la respuesta del servidor
                String respuesta = serverInput.readLine();
                System.out.println("=>Respuesta:" + respuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
