import java.lang.*;
import java.io.*;
import java.net.*;
class TCP_Server {
   public static void main(String args[]) throws IOException {
	   String data = "disjointed. I like to see at least thf ";
   try {
         ServerSocket srvr = new ServerSocket(8080);
         Socket skt = srvr.accept();
         System.out.print("Server has connected!\n");
         System.out.println("srvr.getInetAddress();:"+srvr.getInetAddress());
         PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
         System.out.print("Sending string: '" + data + "'\n");
         System.out.print(data);
         out.close();
         skt.close();
         srvr.close();
      }
      catch(Exception e)
      {
         System.out.print(" It didn't work!\n");
      }
   ServerSocket srvr1 = new ServerSocket(8080);
   Socket s = srvr1.accept();
   String data1 = "disjointed. I like to see at least three or four lines to a paragraph, and as an indication, my longest ";
   DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
   int send_interval=100;
   int ind=0;
   while(true)
   {
 	 final long start=System.currentTimeMillis();
 	 dos.writeUTF((ind++)+data+data);
 	 final long end=System.currentTimeMillis();

   if(send_interval<(end-start))
   {
	   try {
	Thread.sleep(100);   
   }
   catch(Exception e) {}
   }
   }
   }
}