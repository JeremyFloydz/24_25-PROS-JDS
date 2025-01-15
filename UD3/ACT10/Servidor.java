package eje10;

import java.net.*;
import java.io.*;

public class Servidor {

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Crear el socket para escuchar en el puerto 12348
            socket = new DatagramSocket(12348);
            System.out.println("Esperando datagrama.......");
            
            // Crear un buffer para recibir el datagrama
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // Recibir el datagrama
            socket.receive(packet);

            // Deserializar el objeto Tenista
            ByteArrayInputStream byteStream = new ByteArrayInputStream(packet.getData());
            ObjectInputStream objectStream = new ObjectInputStream(byteStream);
            Tenista tenista = (Tenista) objectStream.readObject();

            // Mostrar los datos recibidos
            System.out.println("Recibo el objeto: " + tenista.getApellido() + " " + tenista.getAltura());
            System.out.println("IP de origen: " + packet.getAddress());
            System.out.println("Puerto de origen: " + packet.getPort());

            // Modificar los datos del Tenista
            tenista.setApellido("Karlovic");
            tenista.setAltura(208);

            // Mostrar los datos modificados
            System.out.println("Envío el objeto: " + tenista.getApellido() + " " + tenista.getAltura());

            // Serializar el objeto Tenista modificado
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
            objectOutStream.writeObject(tenista);

            // Enviar el objeto modificado de vuelta al cliente
            byte[] data = byteOutStream.toByteArray();
            DatagramPacket responsePacket = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
            socket.send(responsePacket);

            System.out.println("Fin del servidor");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

