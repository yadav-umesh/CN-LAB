import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TCP_Client {
    static Timer timer=new Timer();
    static Random random=new Random();
    static int receiverinterval=3000,sendinterval;
    public static void main(String args[]) throws IOException, InterruptedException {
        Socket s = new Socket("localhost", 5000);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        sendinterval=din.read();
        dout.write(receiverinterval);
        while (true ) {
            if(sendinterval>receiverinterval){
                Thread.sleep(sendinterval-receiverinterval);
            }

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        String msg = din.readUTF();
                        System.out.println("received " + msg);
                        //timer.cancel();
                    }
                    catch (Exception e) {
                        //e.printStackTrace();
                    }

                }
            }, 5000);


        }
    }
}