import java.util.Random;
import java.util.Scanner;
public class Parity {
    static void send(char ch){
        int n=(int) ch;
        int parity=0;
        int input[]=new int[8];
        for(int i=6;i>=0;i--){
            input[i]=n%2;
            n/=2;
            parity+=input[i];
        }
        input[7]=parity%2;
        transmit(input);
    }
    static void transmit(int input[]){
        Random rand=new Random();
        int turn=rand.nextInt(2);
        int output[]=new int[8];
        for(int i=0;i<8;i++)
            output[i]=input[i];
        if(turn==1){
            int index=rand.nextInt(7);
            output[index]=(input[index]+1)%2;
            receive(output);
        }
        else{
            receive(output);
        }
    }
    static  void receive(int input[]){
        int parity=0;
        for(int i=0;i<8;i++){
            parity+=input[i]%2;
        }
        parity=parity%2;
        if(parity==0){
            int ch=0;
            for(int i=0;i<7;i++){
                ch+=input[i]*((int) Math.pow(2,6-i));
            }
            System.out.print((char)ch+"\t");
        }
        else{
            System.out.print("error"+"\t");
        }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String input=sc.next();
        System.out.println("Sent :\t"+input);
        System.out.println("Received :");
        for(int i=0;i<input.length();i++)
            send(input.charAt(i));
    }
}
