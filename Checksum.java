import java.util.Random;
import java.util.Scanner;

public class Checksum {
    static int[] getAscii(char ch){
        int input[]=new int[7];
        int n=(int) ch;
        for(int i=6;i>=0;i--){
            input[i]=n%2;
            n/=2;
        }
        return input;
    }
    public static void main(String args[]){
        System.out.print("Enter input string :\t");
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        int n=input.length();
        int data[][]=new int[n+1][7];
        System.out.println("Data Sent :");
        System.out.println("\tData :");
        for(int i=0;i<n;i++){
            data[i]=getAscii(input.charAt(i));
            System.out.print("\t");
            for(int j=0;j<7;j++)
                System.out.print(data[i][j]);
            System.out.println();
        }
        int checksum[]=new int[7];
        for(int i=0;i<7;i++){
            checksum[i]=data[0][i];
        }
        int c=0;
        for(int i=1;i<n;i++){
            for(int j=6;j>=0;j--){
                checksum[j]+=data[i][j]+c;
                c=checksum[j]/2;
                checksum[j]=checksum[j]%2;
            }
            while(c==1){
                for(int j=6;j>=0;j--) {
                    checksum[j] += c;
                    c = checksum[j] / 2;
                    checksum[j] = checksum[j] % 2;
                }
            }
        }
        for(int i=0;i<7;i++)
            checksum[i]=(checksum[i]+1)%2;
        System.out.println("\tChecksum :");
        System.out.print("\t");
        for(int i=0;i<7;i++)
            System.out.print(checksum[i]);
        System.out.println();

        for(int i=0;i<7;i++)
            data[n][i]=checksum[i];
        Random rand=new Random();
        int turn=rand.nextInt(2);
        if(turn==1){
            int row=rand.nextInt(n+1);
            int col=rand.nextInt(7);
            data[row][col]=(data[row][col]+1)%2;
        }
        System.out.println("\nData Received : ");
        System.out.println("\tData :");
        for(int i=0;i<n+1;i++){
            System.out.print("\t");
            for(int j=0;j<7;j++)
                System.out.print(data[i][j]);
            System.out.println();
        }
        for(int i=0;i<7;i++)
            checksum[i]=data[n][i];
        System.out.println("\tChecksum :");
        System.out.print("\t");
        for(int i=0;i<7;i++)
            System.out.print(checksum[i]);
        System.out.println();
        c=0;
        for(int i=0;i<n;i++){
            for(int j=6;j>=0;j--){
                checksum[j]+=data[i][j]+c;
                c=checksum[j]/2;
                checksum[j]=checksum[j]%2;
            }
            while(c==1){
                for(int j=6;j>=0;j--) {
                    checksum[j] += c;
                    c = checksum[j] / 2;
                    checksum[j] = checksum[j] % 2;
                }
            }
        }
        for(int i=0;i<7;i++)
            checksum[i]=(checksum[i]+1)%2;
        System.out.print("Final Checksum :\t");
        for(int i=0;i<7;i++)
            System.out.print(checksum[i]);
        System.out.println();
        System.out.print("Retrived data :\t");
        for(int i=0;i<n;i++){
            int ch=0;
            for(int j=0;j<7;j++){
                ch+=data[i][j]*((int) Math.pow(2,6-j));
            }
            System.out.print((char) ch);
        }
        System.out.println();
        int sum=0;
        for(int i=0;i<7;i++){
            sum+=checksum[i];
        }
        if(sum==0)
            System.out.println("Data received without any error");
        else
            System.out.println("There is an error in received data");
    }
}
