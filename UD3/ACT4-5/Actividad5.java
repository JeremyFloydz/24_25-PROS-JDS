package paquete1;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpHeaders;
import java.io.IOException;

public class Actividad5 {

    // Método privado que maneja la conexión a la web y muestra la respuesta
    private static void VisualizarConexión() {
        try {
            // Crear un HttpClient
            HttpClient client = HttpClient.newHttpClient();

            // Crear una solicitud con la URI proporcionada
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://www.vitoria-gasteiz.com"))
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir la primera línea (toString del objeto HttpResponse)
            System.out.println("Conexión con www.vitoria-gasteiz.com");
            System.out.println("==========================");
            System.out.println("\tMétodo toString():" + response.toString());

            // Imprimir la segunda línea (código de estado HTTP)
            System.out.println("\tMétodo getStatusCode():" + response.statusCode());

            // Imprimir la tercera línea (valor del encabezado Content-Type)
            System.out.println("\tMétodo getContentType():" + response.headers().firstValue("Content-Type").orElse("N/A"));

        } catch (IOException | InterruptedException e) {
            System.out.println("Error durante la conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Llamar al método VisualizarConexión
        VisualizarConexión();
    }
}
