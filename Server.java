import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server
{
    public static void main(String[] args) throws IOException
    {
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive;
        while (true)
        {
            DpReceive = new DatagramPacket(receive, receive.length);
            ds.receive(DpReceive);
            System.out.println("Client:-" + data(receive));
            if (data(receive).equals("bye"))
            {
                System.out.println("Client sent bye.....EXITING");
                break;
            }
            receive = new byte[65535];
        }
    }
    private static String data(byte[] a)
    {
        if (a == null)
            return null;
        String str="";
        int i = 0;
        while (a[i] != 0)
        {
            str+=(char)a[i]+"";
            i++;
        }
        return str;
    }
}
