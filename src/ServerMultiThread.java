 import java.io.*;

import java.net.*;



public class ServerMultiThread {

    public static void main(String[] args) throws IOException {

        int port = 12345;

        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("Server Pitagora avviato sulla porta " + port);



        while (true) {

            Socket clientSocket = serverSocket.accept();

            System.out.println("Nuovo client connesso: " + clientSocket.getInetAddress());



            // Crea un thread per gestire il client

            new Thread(new ClientHandler(clientSocket)).start();

        }

    }

}

