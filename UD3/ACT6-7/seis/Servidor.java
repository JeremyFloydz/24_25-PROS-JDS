package paquete1;
import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) {
        final int puertoEscucha = 6010;
        System.out.println("El servidor está en espera de conexiones...");

        try (ServerSocket servidorSocket = new ServerSocket(puertoEscucha)) {
            // Aceptar conexión del cliente
            Socket cliente = servidorSocket.accept();
            System.out.println("Un cliente se ha conectado.");

            // Crear flujos de entrada y salida
            DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());

            // Leer número enviado por el cliente
            int numeroRecibido = flujoEntrada.readInt();
            System.out.println("Número recibido: " + numeroRecibido);

            // Calcular el cuadrado del número
            int resultado = numeroRecibido * numeroRecibido;

            // Preparar y enviar la respuesta al cliente
            String mensajeRespuesta = "El cuadrado del número " + numeroRecibido + " es " + resultado;
            flujoSalida.writeUTF(mensajeRespuesta);
            System.out.println("Resultado enviado: " + mensajeRespuesta);

            // Cerrar recursos
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();

        } catch (IOException ex) {
            System.err.println("Se produjo un error en el servidor: " + ex.getMessage());
        }
    }
}