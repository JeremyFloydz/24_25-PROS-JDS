package paquete1;

import java.net.URI;
import java.net.URISyntaxException;

public class Actividad4 {

    // Clase que maneja la extracción y visualización de la información de una URI
    private static class URIHandler {
        private URI uri;

        // Constructor que recibe la URI
        public URIHandler(URI uri) {
            this.uri = uri;
        }

        // Método para mostrar la información de la URI
        public void displayInformation() {
            System.out.println("URI: " + uri.toString());
            System.out.println("\tHost: " + getHost());
            System.out.println("\tPort: " + getPort());
            System.out.println("\tPath: " + getPath());
            System.out.println("\tScheme: " + getScheme());
            System.out.println("\tAuthority: " + getAuthority());
            System.out.println();
        }

        // Métodos auxiliares para obtener información de la URI
        private String getHost() {
            return uri.getHost() != null ? uri.getHost() : "No disponible";
        }

        private int getPort() {
            return uri.getPort();  // Devuelve -1 si no se especifica un puerto
        }

        private String getPath() {
            return uri.getPath() != null ? uri.getPath() : "";
        }

        private String getScheme() {
            return uri.getScheme() != null ? uri.getScheme() : "No disponible";
        }

        private String getAuthority() {
            return uri.getAuthority() != null ? uri.getAuthority() : "No disponible";
        }
    }

    public static void main(String[] args) {
        try {
            // Definimos las tres URIs según el enunciado
            URI uri1 = new URI("http://docs.oracle.com");
            URI uri2 = new URI("http://docs.oracle.com/javase/7");
            URI uri3 = new URI("http://docs.oracle.com/javase/7/docs/api/java/net/URL.html");

            // Creamos instancias de URIHandler para cada URI
            URIHandler handler1 = new URIHandler(uri1);
            URIHandler handler2 = new URIHandler(uri2);
            URIHandler handler3 = new URIHandler(uri3);

            // Mostramos la información de cada URI
            handler1.displayInformation();
            handler2.displayInformation();
            handler3.displayInformation();

        } catch (URISyntaxException e) {
            System.out.println("Error al crear la URI: " + e.getMessage());
        }
    }
}
