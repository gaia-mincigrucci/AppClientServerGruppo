import java.io.*;
import java.net.*;

public class ServerMultiThread {
    public static void main(String[] args) {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server Pitagora avviato sulla porta " + port);

            new Thread(() {
                try {
                    Thread.sleep(500);
                    ClientSemplice.main(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuovo client connesso: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Errore del server: " + e.getMessage());
        }
    }
}
