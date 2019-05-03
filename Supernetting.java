import java.util.*;
import java.lang.*;

public class Supernetting {
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of hosts");
        int nhos = s.nextInt();
       // if(nhos < 255)
         //   System.out.println("We can use 1 class C");
        double snets = Math.ceil((double)nhos/254)-1;
        if(snets > 65534)
            System.out.println("In this case we need to take class A");
        else if(snets > 254)
            System.out.println("In this case we need to take class B");
        else 
        {
            System.out.println("Number of class C addresses required are: " + Math.ceil((double)nhos/254));
        }
            int i = 1,count = 0;
            while(snets > i)
            {
                i = i*2;
                count++;
            }
            System.out.println("The network id after supernetting is: /" + (24-count));
        
    }
}
