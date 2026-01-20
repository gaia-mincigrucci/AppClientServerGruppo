import java.io.*;
import java.net.*;

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            System.out.println("Connessione stabilita con il server.");

            System.out.println("Inserisci il tuo Nome Utente:");
            String username = in.readLine();

            System.out.println("Inserisci la tua Password:");
            String password = in.readLine();

            System.out.println("Ciao " + username + "! Accesso eseguito. Iniziamo il calcolo.");

            System.out.println("Cosa vuoi calcolare? -1: Ipotenusa -2: Cateto):");
            String sceltaStr = in.readLine();

            if (sceltaStr != null) {
                int x = Integer.parseInt(sceltaStr);

                switch (x) {
                    case 1:
                        System.out.println("Inserisci il valore del cateto 1:");
                        double c1 = Double.parseDouble(in.readLine());
                        System.out.println("Inserisci il valore del cateto 2:");
                        double c2 = Double.parseDouble(in.readLine());
                        double ipotenusa = Math.sqrt((c1 * c1) + (c2 * c2));
                        System.out.println("RISULTATO: L'ipotenusa è: " + ipotenusa);
                        break;
                    case 2:
                        System.out.println("Inserisci il valore dell'ipotenusa:");
                        double ipo = Double.parseDouble(in.readLine());
                        System.out.println("Inserisci il valore del cateto noto:");
                        double cateto1 = Double.parseDouble(in.readLine());
                        double catetoMancante = Math.sqrt((ipo * ipo) - (cateto1 * cateto1));
                        System.out.println("RISULTATO: Il cateto è: " + catetoMancante);
                        break;
                    default:
                        System.out.println("Errore: Scelta non valida.");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Connessione terminata con il client.");
        } finally {
            try { socket.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }
}

