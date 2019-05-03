import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class hosttoIP {
    public static void main(String args[]) throws UnknownHostException {
        System.out.print("Enter domain name :\t");
        Scanner sc = new Scanner(System.in);
        String domain_name=sc.next();
        String host = InetAddress.getByName(domain_name).getHostAddress();
        System.out.println("Host IP is \t"+host);
    }
}
