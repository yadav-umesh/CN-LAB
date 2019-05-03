import java.io.*;
import java.net.*;
import java.util.Scanner;
public class udp_c 
{
	public static void main(String args[]) throws ArrayIndexOutOfBoundsException
	{
	    DatagramSocket ds=null;
		try
       {
	   ds=new DatagramSocket();
	   System.out.println("1st Client is sending data........");
	   System.out.println("enter the byte stream");
	   Scanner sc=new Scanner(System.in);
	   String s=sc.next();
	   byte[] m=s.getBytes();
	   InetAddress host=InetAddress.getByName("localhost");
	   //int serverport=6661;
	   DatagramPacket request=new DatagramPacket(m,s.length(),host,6662);
	   ds.send(request);
	   byte[]buffer=new byte[1000];
	   DatagramPacket reply=new DatagramPacket(buffer,buffer.length);
	   ds.receive(reply);
	   System.out.println("Reply:"+new String(reply.getData()));
	   
   }
   catch(SocketException se)
   {
	   System.out.println(se.getMessage());
   }
   catch(IOException e)
   {
	   System.out.println(e.getMessage());
   }
   finally
   {
	   if(ds!=null)
	   {
		   ds.close();
	   }
   }
}
}