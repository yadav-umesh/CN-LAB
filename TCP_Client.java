import java.lang.*;
import java.io.*;
import java.net.*;

class TCP_Client {
   public static void main(String args[]) throws IOException {
	   int receive_interval=100;
	   
         Socket skt = new Socket("localhost", 8080);
         DataInputStream dis=new DataInputStream(skt.getInputStream());
         try {
         BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
         System.out.println("server IP Address and port is:"+skt.getInetAddress()+"/"+skt.getPort());
         System.out.print("Received string is:");
         while (!in.ready()) {}
         System.out.println(in.readLine()); // Read one line and output it
         System.out.print("\n");
         in.close();
      }
      catch(Exception e) 
      {
         System.out.print("It didn't work!\n");
      }
      while(true)
      { DataInputStream dis1=new DataInputStream(skt.getInputStream());
    	 final long start=System.currentTimeMillis();
    	 dis.readUTF();
    	 final long end=System.currentTimeMillis();

      if(receive_interval>(end-start))
      {
   	   try {
   	      Thread.sleep(100);   
           }
      catch(Exception e) {}
     }
   }
   }
}

