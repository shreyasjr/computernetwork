import java.net.*;
import java.io.*;

public class slidingsender{
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(10);
        Socket clientSocket = serverSocket.accept();
        DataInputStream in = new DataInputStream(System.in);
        DataInputStream in1 = new DataInputStream(clientSocket.getInputStream());
        String sbuff[] = new String[8];
        PrintStream p;
        int sptr = 0, sws = 8, nf, ano, i;
        String ch;
        
        do {
            p = new PrintStream(clientSocket.getOutputStream());
            System.out.print("Enter the no. of frames : ");
            nf = Integer.parseInt(in.readLine());
            p.println(nf);

            if (nf <= sws - 1) {
                System.out.println("Enter " + nf + " Messages to be sent\n");
                for (i = 1; i <= nf; i++) {
                    sbuff[sptr] = in.readLine();
                    p.println(sbuff[sptr]);
                    sptr = ++sptr % 8;
                }
                sws -= nf;
                System.out.print("Acknowledgment received");
                ano = Integer.parseInt(in1.readLine());
                System.out.println(" for " + ano + " frames");
                sws += nf;
            } else {
                System.out.println("The no. of frames exceeds window size");
                break;
            }
            
            System.out.print("\nDo you want to send more frames? ");
            ch = in.readLine();
            p.println(ch);
        } while (ch.equals("yes"));
        
        clientSocket.close();
    }
}
