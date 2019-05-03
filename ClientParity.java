// A Java program for a Client
import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ClientParity
{
    // initialize socket and input output streams
    private Socket socket		 = null;
    private DataInputStream input = null;
    private DataOutputStream out	 = null;

    // constructor to put ip address and port
    public ClientParity(String address, int port)
    {
        Scanner sc=new Scanner(System.in);
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal
            input = new DataInputStream(System.in);

            // sends output to the socket
            out = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        Random rand=new Random();
        try {
            System.out.print("Enter input string:\t");
            line=sc.nextLine();
            String inp="";
            for(int k=0;k<line.length();k++){
                int n=(int) line.charAt(k);
                int parity=0;
                int input[]=new int[8];
                for(int i=6;i>=0;i--){
                    input[i]=n%2;
                    n/=2;
                    parity+=input[i];
                }
                input[7]=parity%2;
                int turn=rand.nextInt(2);
                if(turn==1){
                    int index=rand.nextInt(7);
                    input[index]=(input[index]+1)%2;
                }
                for(int i=0;i<8;i++)
                    inp+=input[i];
            }

            out.writeUTF(inp);
        }
        catch(IOException i) {
            System.out.println(i);
        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        ClientParity client = new ClientParity("172.30.5.132", 5000);
    }
}
