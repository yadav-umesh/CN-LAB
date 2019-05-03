import java.util.*;
import java.io.*;
import java.net.*;

class ListeningAtB implements Runnable
{

    public static void printData(byte buf[])
    {
        System.out.println("Data packet is: "+buf[0]+" "+buf[1]+" "+buf[2]+" "+buf[3]+" "+buf[4]);
    }

    public  int[] data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int res[]=new int[3];
        int i = 0;
        while (a[i] != 0)
        {
            res[i]=(int) a[i];
            i++;
        }
        return res;
    }
    public byte[] generateDataGram(byte color,byte ttl,byte seqno, byte srcid,byte cycle)
    {
        byte buf[]=new byte[5];
        buf[0]=color;
        buf[1]=ttl;
        buf[2]=seqno;
        buf[3]=srcid;
        buf[4]=cycle;//default clockwise
        return buf;
    }
    int rand50()
    {
        // rand() function will generate odd or even
        // number with equal probability. If rand()
        // generates odd number, the function will
        // return 1 else it will return 0.
        return Math.abs(new Random().nextInt()) & 1;
    }

    public void run() {
        try {
            DatagramSocket ds = new DatagramSocket(1235);
            DatagramSocket ds_sen = new DatagramSocket();
            byte[] receive = new byte[65535];

            DatagramPacket DpReceive = null,DpSend=null;
            while (true) {

                // Step 2 : create a DatgramPacket to receive the data.
                DpReceive = new DatagramPacket(receive, receive.length);
                try {
                    // Step 3 : revieve the data in byte buffer.
                    ds.receive(DpReceive);
                    byte dt[]=receive;
                    System.out.println("Packet Recieved");
                    //Order color ttl seqnum and source id
//                    int dt[]=data(receive);

                    if(dt[0]==0)
                    {
                        DatagramPacket g1=null,g2=null;
                        dt[1]--;
                        byte s1[]=generateDataGram((byte)2,dt[1],dt[2],dt[3],(byte)1);
                        byte s2[]=generateDataGram((byte)2,dt[1],dt[2],dt[3],(byte)0);

                        InetAddress ip = InetAddress.getLocalHost();
//                            byte send[]=generateDataGram((byte)2,dt[1],dt[2],dt[3],(byte)1);
                        DpSend = new DatagramPacket(s1, s1.length,ip,1236);
                        ds_sen.send(DpSend);
                        System.out.println("Green DataGram Packet was forwarded ClockWise");
                        printData(s1);


                        DpSend = new DatagramPacket(s2, s2.length,ip,1235);
                        ds_sen.send(DpSend);
                        System.out.println("Green DataGram Packet was forwarded Anti ClockWise");
                        printData(s2);
                    }
                    else if(dt[0]==1)//Blue Colors Action
                    {
                        System.out.println("Blue Packet recieved with srcid="+dt[3]+" Ip address is"+ds.getInetAddress());
                        printData(dt);
                    }
                    else if(dt[0]==2)//Green Colors Action
                    {
                        if(rand50()==1)
                        {
                            dt[1]--;
                        }
                        if(dt[1]>0)
                        {
                            InetAddress ip = InetAddress.getLocalHost();
                            byte send[]=generateDataGram((byte)2,dt[1],dt[2],dt[3],(byte)1);
                            DpSend = new DatagramPacket(send, send.length,ip,1235);
                            ds_sen.send(DpSend);
                            System.out.println("Green DataGram Packet was forwarded");
                            printData(send);
                        }
                        else if(dt[1]==0)
                        {
                            InetAddress ip = InetAddress.getLocalHost();
                            byte send[]=generateDataGram((byte)1,dt[1],dt[2],dt[3],(byte)1);
                            DpSend = new DatagramPacket(send, send.length,ip,1235);
                            ds_sen.send(DpSend);
                            System.out.println("Blue DataGram Packet send");
                            printData(send);
                        }

                    }



                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }


}
public class B {
    //Let each of the classes A,B,C be running in different ports:
    public byte[] generateDataGram(byte color,byte ttl,byte seqno, byte srcid)
    {
        byte buf[]=new byte[5];
        buf[0]=color;
        buf[1]=ttl;
        buf[2]=seqno;
        buf[3]=srcid;
        buf[4]=1;//default clockwise
        return buf;
    }

    public static void printData(byte buf[])
    {
        System.out.println("Data packet is: "+buf[0]+" "+buf[1]+" "+buf[2]+" "+buf[3]+" "+buf[4]);
    }

    public static void main(String args[]) throws InterruptedException {
        // Step 1:Create the socket object for
        // carrying the data.
        Thread.sleep(10000);
        ListeningAtB la=new ListeningAtB();
        Thread t=new Thread(la);
        t.start();
        try {
            DatagramSocket ds = new DatagramSocket();

            InetAddress ip = InetAddress.getLocalHost();
            byte buf[] = new byte[5];
            byte seq = 0;
            // loop while user not enters "bye"
            while (true) {

                Thread.sleep(5000);
                buf[0] = 1;
                buf[1] = 8;
                buf[2] = (++seq);
                buf[3] = 20;
                buf[4]=1;//ClockWise
                // Step 2 : Create the datagramPacket for sending
                // the data.
                DatagramPacket DpSend =
                        new DatagramPacket(buf, buf.length, ip, 1236); //B's Port number:

                // Step 3 : invoke the send call to actually send
                // the data.
                ds.send(DpSend);
//                System.out.println("Sent Blue packet with details "+buf);
                printData(buf);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
