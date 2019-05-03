import java.util.Random;
import java.util.Scanner;

public class CRC {
    public static void main(String args[]){
        System.out.print("Enter input data in binary system:\t");
        Scanner scanner=new Scanner(System.in);
        String input=scanner.next();
        System.out.print("Enter divisor in binary system :\t");
        String divisor=scanner.next();
        int data[]=new int[(input.length() + divisor.length()) - 1];
        int n=divisor.length();
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='1')
                data[i]=1;
            else if(input.charAt(i)=='0')
                data[i]=0;
            else{
                System.out.println("Error in input data format");
                return;
            }
        }
        for(int i=0;i<n-1;i++){
            if(divisor.charAt(i)=='1'||divisor.charAt(i)=='0')
                data[input.length()+i]=0;
            else{
                System.out.println("Error in divisor data format");
                return;
            }
        }
        if(input.charAt(divisor.length()-1)!='1'&&input.charAt(divisor.length()-1)!='0'){
            System.out.println("Error in divisor data format");
            return;
        }
        int div[]=new int[n];
        for(int i=0;i<n;i++)
            div[i]=(int) divisor.charAt(i)-48;
        for(int i=0;i<n-1;i++)
            data[input.length()+i]=0;
        int buffer[]=new int[input.length()+n-1];
        for(int i=0;i<input.length();i++)
            buffer[i]=data[i];
        for(int i=input.length();i<input.length()+n-1;i++)
            buffer[i]=0;
        int j=0;
        while(true){
            if(j>=input.length())
                break;
            for(int i=0;i<n;i++){
                if(buffer[j+i]==div[i])
                    buffer[j+i]=0;
                else
                    buffer[j+i]=1;
            }
            int k;
            for(k=0;k<n;k++){
                if(buffer[j+k]==1)
                    break;
            }
            j=k+j;
        }
        System.out.print("Sent :\t\t");
        for(int i=0;i<input.length();i++)
            System.out.print(data[i]+" ");
        for(int i=input.length();i<input.length()+n-1;i++)
            System.out.print(buffer[i]+" ");
        System.out.println();
        for(int i=0;i<input.length();i++)
            buffer[i]=data[i];
        Random rand=new Random();
        int turn= rand.nextInt(2);
        if(turn==1){
            int index=rand.nextInt(input.length()+n-1);
            buffer[index]=(buffer[index]+1)%2;
        }
        System.out.print("Received :\t");
        for(int i=0;i<input.length()+n-1;i++)
            System.out.print(buffer[i]+" ");
        j=0;
        while(true){
            if(j>=input.length())
                break;
            for(int i=0;i<n;i++){
                if(buffer[j+i]==div[i])
                    buffer[j+i]=0;
                else
                    buffer[j+i]=1;
            }
            int k;
            for(k=0;k<n;k++){
                if(buffer[j+k]==1)
                    break;
            }
            j=k+j;
        }
        boolean status=true;
        for(int i=input.length();i<input.length()+n-1;i++){
            if(buffer[i]==1)
                status=false;
        }
        System.out.print("\nRemainder :\t");
        for(int i=input.length();i<input.length()+n-1;i++)
            System.out.print(buffer[i]+" ");
        System.out.println();
        if(status)
            System.out.println("No Error, remainder is zero");
        else
            System.out.println("Error, remainder non zero");
    }
}
