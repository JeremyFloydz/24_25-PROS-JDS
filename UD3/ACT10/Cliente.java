package eje10;

import java.net.*;
import java.io.*;

public class Cliente {

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Crear el socket para enviar el datagrama
            socket = new DatagramSocket(34567);

            // Crear el objeto Tenista
            Tenista tenista = new Tenista("del Potro", 198);
            System.out.println("Envío el objeto: " + tenista.getApellido() + " " + tenista.getAltura());

            // Serializar el objeto Tenista
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
            objectOutStream.writeObject(tenista);

            // Enviar el objeto al servidor
            byte[] data = byteOutStream.toByteArray();
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 12348);
            socket.send(packet);

            // Esperando la respuesta del servidor
            System.out.println("Esperando datagrama.......");
            byte[] buffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            // Deserializar el objeto Tenista recibido
            ByteArrayInputStream byteInStream = new ByteArrayInputStream(responsePacket.getData());
            ObjectInputStream objectInStream = new ObjectInputStream(byteInStream);
            Tenista tenistaRecibido = (Tenista) objectInStream.readObject();

            // Mostrar el objeto recibido
            System.out.println("He recibido el objeto: " + tenistaRecibido.getApellido() + " " + tenistaRecibido.getAltura());
            System.out.println("Fin del cliente");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

