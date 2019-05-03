// A Java program for a Server
import java.net.*;
import java.io.*;

public class ServerParity
{
    //initialize socket and input stream
    private Socket		 socket = null;
    private ServerSocket server = null;
    private DataInputStream in	 = null;

    // constructor with port
    public ServerParity(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String input = "";

            try
            {
                input = in.readUTF();
                System.out.println(input);
            }
            catch(IOException i) {
                System.out.println(i);
            }

            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
            int n=input.length();
            System.out.println("String Received :");
            for(int k=0;k<n/8;k++) {
                int parity=0;
                for (int i = 0; i < 8; i++) {
                    parity += ((int) input.charAt(8*k+i)-48) % 2;
                }
                parity = parity % 2;
                if (parity == 0) {
                    int ch = 0;
                    for (int i = 0; i < 7; i++) {
                        ch += ((int) input.charAt(8*k+i)-48) * ((int) Math.pow(2, 6 - i));
                    }
                    System.out.print((char) ch + "\t");
                }
                else {
                    System.out.print("error" + "\t");
                }
            }
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        ServerParity server = new ServerParity(5000);
    }
}

