import java.sql.SQLSyntaxErrorException;
import java.util.Random;
import java.util.Scanner;

public class Hamming {
    static int[] display(String input){
        int m=input.length();
        int r=0;
        while((int) Math.pow(2,r)<m+r+1)
            r++;
        int data[]=new int[m+r+1];
        for(int i=0;i<m+r+1;i++)
            data[i]=-1;
        for(int i=0;i<r;i++)
            data[(int) Math.pow(2,i)]=0;
        int i=1;
        for(int k=0;k<m;k++){
            while(data[i]!=-1)
                i++;
            data[i]=(int) input.charAt(k)-48;
        }
        int num[]=new int[m+r+1];
        for(i=0;i<m+r+1;i++)
            num[i]=i;
        for(int k=0;k<r;k++){
            int parity=0;
            for(int j=0;j<m+r+1;j++){
                if(num[j]%2==1)
                    parity+=data[j];
                num[j]/=2;
            }
            parity%=2;
            data[(int) Math.pow(2,k)]=parity;
        }
        for (i=1;i<m+r+1;i++)
            System.out.print(data[i]);
        System.out.println();
        int inp[]=new int[m+r];
        for(i=1;i<m+r+1;i++)
            inp[i-1]=data[i];
        return inp;
    }
    static void check(int input[]){
        int n=input.length;
        int r=0;
        while((int) Math.pow(2,r)<n+1)
            r++;
        int num[]=new int[n+1];
        int data[]=new int[n+1];
        for(int i=0;i<n;i++)
            data[i+1]=input[i];
        int i=1;
        for(i=0;i<n+1;i++)
            num[i]=i;
        int sum=0;
        for(int k=0;k<r;k++){
            int parity=0;
            for(int j=0;j<n+1;j++){
                if(num[j]%2==1)
                    parity+=data[j];
                num[j]/=2;
            }
            parity%=2;
            if(parity!=0)
                sum+=(int) Math.pow(2,k);
        }
        for(i=0;i<n;i++){
            System.out.print(input[i]);
        }
        if(sum!=0)
        System.out.println("\nError position :\t"+sum);
        else
            System.out.println("\nNo Error");
        int cnt=0,ch=0,next_index=0,j=6;
        System.out.print("Before correction :\t");
        for(i=1;i<n+1;i++){
            if(i!=(int)Math.pow(2,next_index)) {
                cnt++;
                ch += data[i]*(int) Math.pow(2,j);
                if(j>0)
                    j--;
                else if(j==0)
                    j=6;
            }
            else{
                next_index++;
            }
            if(cnt%7==0&&cnt!=0){
                System.out.print((char) ch);
                ch=0;
            }
        }
        System.out.println();
        ch=0;
        cnt=0;
        next_index=0;
        j=6;
        System.out.print("After correction :\t");
        for(i=1;i<n+1;i++){
            if(i!=(int)Math.pow(2,next_index)) {
                cnt++;
                if(i!=sum)
                ch += data[i]*(int) Math.pow(2,j);
                else{
                    ch+=((data[i]+1)%2)*(int) Math.pow(2,j);
                }
                if(j>0)
                    j--;
                else if(j==0)
                    j=6;
            }
            else{
                next_index++;
            }
            if(cnt%7==0&&cnt!=0){
                System.out.print((char) ch);
                ch=0;
            }
        }
        System.out.println();
    }
    static String getAscii(String input){
        int n=input.length()*7;
        int data[]=new int[n];
        for(int i=0;i<input.length();i++){
            int ch=(int) input.charAt(i);
            for(int j=6;j>=0;j--){
                data[7*i+j]=ch%2;
                ch/=2;
            }
        }
        String buffer="";
        for(int i=0;i<n;i++)
            buffer+=data[i];
        return buffer;
    }
    public static void main(String args[]){
        System.out.println("Enter input string");
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        System.out.print("Sent data :\t\t");
        String in=getAscii(input);
        int inp[]=display(in);
        Random rand=new Random();
        int turn=rand.nextInt(2);
        if(turn==1){
            int index=rand.nextInt(inp.length);
            inp[index]=(inp[index]+1)%2;
        }
        System.out.print("Received data :\t");
        check(inp);
    }
}
