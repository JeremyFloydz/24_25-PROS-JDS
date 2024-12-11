package paquete1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        final String servidorHost = "localhost"; // Cambiar si el servidor está en otra máquina
        final int puertoServidor = 6010;

        System.out.println("Iniciando cliente...");

        try (Socket conexion = new Socket(servidorHost, puertoServidor)) {
            // Flujos para enviar y recibir datos
            DataOutputStream enviarDatos = new DataOutputStream(conexion.getOutputStream());
            DataInputStream recibirDatos = new DataInputStream(conexion.getInputStream());

            // Pedir número al usuario
            Scanner entradaTeclado = new Scanner(System.in);
            System.out.println("Por favor, introduce un número entero:");
            int numeroIngresado = entradaTeclado.nextInt();

            // Enviar número al servidor
            enviarDatos.writeInt(numeroIngresado);
            System.out.println("Número enviado: " + numeroIngresado);

            // Recibir respuesta del servidor
            String respuestaServidor = recibirDatos.readUTF();
            System.out.println("Respuesta del servidor:");
            System.out.println("\t" + respuestaServidor);

            // Cerrar flujos
            enviarDatos.close();
            recibirDatos.close();

        } catch (IOException ex) {
            System.err.println("Se produjo un error en el cliente: " + ex.getMessage());
        }
    }
}
