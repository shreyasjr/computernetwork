import java.io.*;
import java.net.*;

public class arpserver {
    public static void main(String[] args) {
        try {
            int port = 2005; // Choose a port that is not in use

            ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(true); // Set SO_REUSEADDR option
            serverSocket.bind(new InetSocketAddress("127.0.0.1", port)); // Replace with your desired IP address

            System.out.println("ARP Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                String ipAddress = reader.readLine();

                String[] ip = {"192.168.1.2", "192.168.1.3"};
                String[] mac = {"AA:BB:CC:11:22:33", "DD:EE:FF:44:55:66"};

                for (int i = 0; i < ip.length; i++) {
                    if (ipAddress.equals(ip[i])) {
                        writer.println(mac[i]);
                        break;
                    }
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
