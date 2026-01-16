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
            // Questo pezzo va dentro il main del ClientSemplice
            while ((rigaDalServer = in.readLine()) != null) {
                System.out.println(rigaDalServer); // Stampa quello che dice il server

                if (rigaDalServer.contains("RISULTATO")) break;

                // TRUCCO: Se la riga finisce con ":", allora tocca a te scrivere!
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