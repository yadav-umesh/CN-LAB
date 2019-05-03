import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IPtoDomainName {
    public static void main(String args[])throws UnknownHostException {
        System.out.print("Enter ip address :\t");
        Scanner sc = new Scanner(System.in);
        String ip = sc.next();
        //InetAddress inetAddr = InetAddress.getByName(ip);
        String hostname = InetAddress.getByName(ip).getHostName();
        System.out.println("Host Name :\t"+hostname);
    }
}
