import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientSemplice {
    public static void main(String[] args) {
        String hostname = "127.0.0.1";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            String rigaDalServer;
            while ((rigaDalServer = in.readLine()) != null) {
                System.out.println(rigaDalServer);

                if (rigaDalServer.contains("RISULTATO")) break;

                if (rigaDalServer.trim().endsWith(":")) {
                    System.out.print("> ");
                    String inputUtente = scanner.nextLine();
                    out.println(inputUtente);
                }
            }

        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e.getMessage());
        }
    }
}
