import java.net.*;
import java.util.Random;

class SenderB implements Runnable{

    private static byte RED = 0;
    private static byte BLUE = 1;
    private static byte GREEN = 2;
    private static byte A = 10;
    private static byte B = 20;
    private static byte C = 30;

    public void run() {
        try {
            while (true) {
                DatagramSocket ds = new DatagramSocket();
                DatagramPacket dp;
                byte color = RED;
                byte sid = B;
                byte ttl = 8;
                byte seqNo = 0;
                byte message[] = {RED, B, 8, 0, 'H', 'i',' ','f','r','o','m',' ','B'};
                InetAddress ip = InetAddress.getLocalHost();
                dp = new DatagramPacket(message, message.length, ip, 4003);
                ds.send(dp);
                Thread.sleep(5000);
            }
        }
        catch (Exception e){

        }
    }
}

class ReceiverB implements Runnable{

    private static byte RED = 0;
    private static byte BLUE = 1;
    private static byte GREEN = 2;
    private static byte A = 10;
    private static byte B = 20;
    private static byte C = 30;


    private static String myColor(byte c){
        if(c == RED)
            return "RED";
        else if (c == BLUE)
            return "BLUE";
        else if (c == GREEN)
            return "GREEN";
        else
            return "No Color";
    }

    public void run(){
        try {
            while (true) {

                DatagramSocket ds = new DatagramSocket(4002);
                byte receive[] = new byte[65535];

                DatagramPacket DpReceive = new DatagramPacket(receive,receive.length);
                ds.receive(DpReceive);

                byte color = receive[0];
                int i;
                for(i=4;receive[i]!=0;i++){
                    System.out.print((char) receive[i]);
                }
                System.out.println(" and my color is "+ myColor(color));
                if(color == RED){
                    receive[2]--;
                    if(receive[2]>0) {
                        ds = new DatagramSocket();
                        DatagramPacket dp;
                        InetAddress ip = InetAddress.getLocalHost();
                        receive[0] = GREEN;
                        dp = new DatagramPacket(receive, i, ip, 4001);
                        ds.send(dp);
                        dp = new DatagramPacket(receive, i, ip, 4003);
                        ds.send(dp);
                    }
                }
                else if(color == GREEN){
                    Random random = new Random();
                    switch (random.nextInt(2)){
                        case 0:
                            receive[2]--;
                            break;
                        default:
                    }
                    if(receive[2]>0){
                        ds = new DatagramSocket();
                        InetAddress ip = InetAddress.getLocalHost();
                        DatagramPacket dp = new DatagramPacket(receive, i, ip, 4003);
                        ds.send(dp);
                        //Thread.sleep(5000);
                    }
                    else if (receive[2] == 0){
                        receive[0] = BLUE;
                        receive[2] = 8;
                        ds = new DatagramSocket();
                        InetAddress ip = InetAddress.getLocalHost();
                        DatagramPacket dp = new DatagramPacket(receive, i, ip, 4003);
                        ds.send(dp);
                        //Thread.sleep(5000);
                    }
                }
                else if (color == BLUE){
                    System.out.println(DpReceive.getPort());
                }
            }

        }
        catch (Exception e){

        }
    }

}

public class ProcessB {

    public static void main(String args[]) {

        Thread sender = new Thread(new SenderB());
        Thread receiver = new Thread(new ReceiverB());
        sender.start();
        receiver.start();
    }
}