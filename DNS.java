import java.net.*;
import java.util.*;
import java.io.*;

public class DNS

 {
    public static void main(String[] args) throws Exception

    {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter the Domain`enter code here` Name");
        String domainName = s.nextLine();
        System.out.println("Domain into IP");
        System.out.println(InetAddress.getByName(domainName).getHostAddress());

        System.out.println("Enter the IP Name");
        String ipName = s.nextLine();
        System.out.println("IP into Domain");
        System.out.println(InetAddress.getByName(ipName).getHostName());        

    }
 }

