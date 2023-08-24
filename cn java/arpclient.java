import java.io.*;
import java.net.*;

public class arpclient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 2005);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Enter IP address to resolve MAC: ");
            String ipAddress = userInput.readLine();

            writer.println(ipAddress);
            String macAddress = reader.readLine();

            System.out.println("MAC Address for " + ipAddress + ": " + macAddress);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
