import java.io.*;
import java.net.*;
public class udp_s
{
	public static void main(String args[])
	{
		DatagramSocket ds1=null;
		DatagramSocket ds2=null;
		DatagramSocket ds3=null;

		try
		{
			ds1=new DatagramSocket(6661);
			ds2=new DatagramSocket(6662);
			ds3=new DatagramSocket(6663);
			
			byte []buffer=new byte[1000];
			while(true)
			{
				DatagramPacket request=new DatagramPacket(buffer,buffer.length);
				ds1.receive(request);
				DatagramPacket reply1=new DatagramPacket(request.getData(),request.getLength(),request.getAddress(),request.getPort());
				ds1.send(reply1);
				//2nd
				//DatagramPacket request=new DatagramPacket(buffer,buffer.length);
				ds2.receive(request);
				DatagramPacket reply2=new DatagramPacket(request.getData(),request.getLength(),request.getAddress(),request.getPort());
				ds2.send(reply2);
				//3rd server
				//DatagramPacket request=new DatagramPacket(buffer,buffer.length);
				ds3.receive(request);
				DatagramPacket reply3=new DatagramPacket(request.getData(),request.getLength(),request.getAddress(),request.getPort());
				ds3.send(reply3);
			}
		}
		catch(SocketException se)
		{
			System.out.println(se.getMessage());
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(IllegalArgumentException ee)
		{
			System.out.println(ee);
		}
		finally
		{
			if(ds1!=null||ds2!=null||ds3!=null)
			{
				ds1.close();
				ds2.close();
				ds3.close();
			}
		}
		
	    }

}
