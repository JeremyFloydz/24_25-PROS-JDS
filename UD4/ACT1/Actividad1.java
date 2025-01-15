package paquete1;

import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class Actividad1 {

    public static void main(String[] args) {
        FTPClient ftpClient = new FTPClient();
        try {
            // Nos conectamos al servidor FTP
            System.out.println("Nos conectamos a ftp.rediris.es");
            ftpClient.connect("ftp.rediris.es");

            // Iniciar sesión con el usuario anónimo
            boolean login = ftpClient.login("anonymous", "dm2");
            if (login) {
                System.out.println("Login correcto");

                // Mostrar el directorio actual
                String currentDirectory = ftpClient.printWorkingDirectory();
                System.out.println("Directorio actual: " + currentDirectory);

                // Intentar crear el directorio DM2PROS
                boolean directoryCreated = ftpClient.makeDirectory("DM2PROS");
                if (directoryCreated) {
                    System.out.println("Directorio creado....");
                } else {
                    System.out.println("NO SE HA PODIDO CREAR EL DIRECTORIO");
                }

                // Realizar logout
                boolean logout = ftpClient.logout();
                if (logout) {
                    System.out.println("Logout del servidor FTP...");
                } else {
                    System.out.println("Error al hacer logout...");
                }
            } else {
                System.out.println("Login incorrecto...");
            }
        } catch (IOException e) {
            System.out.println("Error de conexión o comunicación con el servidor FTP: " + e.getMessage());
        } finally {
            try {
                // Cerrar la conexión
                ftpClient.disconnect();
                System.out.println("Desconectado...");
            } catch (IOException e) {
                System.out.println("Error al desconectar: " + e.getMessage());
            }
        }
    }
}
