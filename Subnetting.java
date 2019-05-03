import java.util.Random;
import java.util.Scanner;

public class Subnetting {
    public static void main(String args[]) {
        Random rand = new Random();
        System.out.println("=>This program assumes class-A, class-B, class-C as the only available networks\n" +
                "=>The first and last host ip addresses are excluded for every network");
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter no of physical networks :\t");
        int n_network=sc.nextInt();
        System.out.print("Enter no of host devices per each network :\t");
        int n_host=sc.nextInt();
        int n_nid=24,n_hid=8;
        if(n_network==1&&Math.pow(2,8)-2>=n_host){
            System.out.print("Network\t:\tclass-C\nNumber of subnets = 1\n");
            System.out.println("NetworkId\t\tSubnetMask\t\tInterface");
            System.out.print((rand.nextInt(32)+192)+"."+rand.nextInt(256)+"."
                            +rand.nextInt(256)+"."+"0\t");
            System.out.println("255.255.255.0\t\tA");
            return;
        }
        else if(n_network>1){

        }
    }
}
