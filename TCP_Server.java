import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TCP_Server {
    static Timer timer=new Timer();
    static Random rand=new Random();
    static int sendinterval,receiverinterval;
    public static void main(String args[]) throws IOException, InterruptedException {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("waiting for client");
        Socket s = ss.accept();
        sendinterval=5000;
        System.out.println("connected to client having ip " + s.getInetAddress().getHostAddress());
        System.out.println("client side port is " + s.getPort());
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        StringBuilder sb=new StringBuilder();
        dout.write(sendinterval);
        receiverinterval=din.read();

        for(int i=0;i<1500;i++){
            sb.append((char)rand.nextInt(26)+65);
        }
        String st=sb.toString();

        while(true){
            if(sendinterval<receiverinterval)
                Thread.sleep(receiverinterval-sendinterval);
            timer.schedule(new TimerTask() {

                @Override
                public void run()  {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        dout.writeUTF(st);
                        System.out.println("sent"+st);
                        //   timer.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }, 5000);

        }}
}
