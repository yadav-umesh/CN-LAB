import  java.util.Scanner;
import java.util.Random;
public class twoDparity {
    static int[] getAscii(char ch){
        int input[]=new int[8];
        int n=(int) ch;
        int parity=0;
        for(int i=6;i>=0;i--){
            input[i]=n%2;
            n/=2;
            parity+=input[i];
        }
        input[7]=parity%2;
        return input;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String input_string=sc.next();
        int n=input_string.length();
        Random rand=new Random();
        int turn=rand.nextInt(8);
        //turn=1;
        int input[][]=new int[n+1][8];
        for(int i=0;i<n;i++){
            input[i]=getAscii(input_string.charAt(i));
        }
        int row[]=new int[n+1];
        int col[]=new int[8];
        for(int i=0;i<n;i++){
            row[i]=0;
            for(int j=0;j<7;j++){
                row[i]+=input[i][j];
            }
            row[i]%=2;
        }
        for(int j=0;j<8;j++){
            col[j]=0;
            for(int i=0;i<n;i++){
                col[j]+=input[i][j];
            }
            col[j]%=2;
            input[n][j]=col[j];
        }
        System.out.println("Data Sent:");
        for(int i=0;i<n;i++){
            for(int j=0;j<7;j++)
                System.out.print(input[i][j]+" ");
            System.out.println(" "+input[i][7]);
        }
        System.out.println();
        for(int j=0;j<7;j++){
            System.out.print(input[n][j]+" ");
        }
        System.out.println(" "+input[n][7]);
        switch(turn) {
            case 1: {
                int r = rand.nextInt(n);
                int c = rand.nextInt(7);
                input[r][c] = (input[r][c] + 1) % 2;
            }
            break;
            case 2: {
                int r=rand.nextInt(n);
                int c1=rand.nextInt(7);
                int c2=rand.nextInt(7);
                input[r][c1] = (input[r][c1] + 1) % 2;
                input[r][c2] = (input[r][c2] + 1) % 2;
            }
            break;
            case 3:{
                int r1=rand.nextInt(n);
                int r2=rand.nextInt(n);
                int c=rand.nextInt(7);
                input[r1][c]=(input[r1][c]+1)%2;
                input[r2][c]=(input[r2][c]+1)%2;
            }
            break;
            case 4:{
                int r1=rand.nextInt(n);
                int r2=rand.nextInt(n);
                int c1=rand.nextInt(7);
                int c2=rand.nextInt(7);
                input[r1][c1]=(input[r1][c1]+1)%2;
                input[r1][c2]=(input[r1][c2]+1)%2;
                input[r2][c1]=(input[r2][c1]+1)%2;
                input[r2][c2]=(input[r2][c2]+1)%2;
            }
            break;
            case 5:{
                int r=rand.nextInt(n);
                input[r][7]=(input[r][7]+1)%2;
            }
            break;
            case 6:{
                int c=rand.nextInt(7);
                input[n][c]=(input[n][c]+1)%2;
            }
            break;
            case 7:{
                int r=rand.nextInt(n);
                int c=rand.nextInt(7);
                input[r][7]=(input[r][7]+1)%2;
                input[n][c]=(input[n][c]+1)%2;
            }
            break;
        }
        System.out.println("\nData Received :");
        for(int i=0;i<n;i++){
            for(int j=0;j<7;j++)
                System.out.print(input[i][j]+" ");
            System.out.println(" "+input[i][7]);
        }
        System.out.println();
        for(int j=0;j<7;j++){
            System.out.print(input[n][j]+" ");
        }
        System.out.println(" "+input[n][7]+"\n");
        int row_parity[]=new int[n+1];
        int col_parity[]=new int[8];
        for(int i=0;i<n+1;i++){
            row_parity[i]=0;
            for(int j=0;j<8;j++){
                row_parity[i]+=input[i][j];
            }
            row_parity[i]%=2;
        }
        for(int j=0;j<8;j++){
            col_parity[j]=0;
            for(int i=0;i<n+1;i++){
                col_parity[j]+=input[i][j];
            }
            col_parity[j]%=2;
        }
        boolean flag_all_r=true;
        boolean flag_all_c=true;
        for(int i=0;i<n+1;i++){
            if(row_parity[i]==1) {
                flag_all_r = false;
                break;
            }
        }
        for(int i=0;i<8;i++){
            if(col_parity[i]==1) {
                flag_all_c = false;
                break;
            }
        }
        if(flag_all_r&&flag_all_c){
            for(int i=0;i<n;i++){
                int ch=0;
                for(int j=0;j<7;j++){
                    ch+=input[i][j]*((int) Math.pow(2,6-j));
                }
                System.out.print((char) ch+"\t");
            }
            System.out.println();
        }
        else if(flag_all_c&&!flag_all_r){
            for(int i=0;i<n;i++){
                if(row_parity[i]==0) {
                    int ch = 0;
                    for (int j = 0; j < 7; j++) {
                        ch += input[i][j] * ((int) Math.pow(2, 6 - j));
                    }
                    System.out.print((char) ch+"\t");
                }
                else System.out.print("error"+"\t");
            }
            System.out.println();
        }
        else if(flag_all_r&&!flag_all_c){
            for(int i=0;i<n;i++){
                int ch=0;
                for(int j=0;j<7;j++){
                    ch+=input[i][j]*((int) Math.pow(2,6-j));
                }
                System.out.print((char) ch+"\t");
            }
            System.out.println();
            System.out.println("Error is detected but not resolved");
        }
        else{
            if(row_parity[n]==0&&col_parity[7]==1){
                for(int i=0;i<n;i++){
                    int ch=0;
                    for(int j=0;j<7;j++){
                        ch+=input[i][j]*((int) Math.pow(2,6-j));
                    }
                    System.out.print((char) ch+"\t");
                }
                System.out.println();
                System.out.println("Row parity bit(s) corrupted");
            }
            else if(row_parity[n]==1&&col_parity[7]==0){
                for(int i=0;i<n;i++){
                    int ch=0;
                    for(int j=0;j<7;j++){
                        ch+=input[i][j]*((int) Math.pow(2,6-j));
                    }
                    System.out.print((char) ch+"\t");
                }
                System.out.println();
                System.out.println("Column parity bit(s) corrupted");
            }
            else if(row_parity[n]==1&&col_parity[7]==1){
                for(int i=0;i<n;i++){
                    int ch=0;
                    for(int j=0;j<7;j++){
                        ch+=input[i][j]*((int) Math.pow(2,6-j));
                    }
                    System.out.print((char) ch+"\t");
                }
                System.out.println();
                System.out.println("Both row and column parity bits corrupted");
            }
            else{
                int row_sum=0;
                int col_sum=0;
                for(int i=0;i<n;i++){
                    row_sum+=row_parity[i];
                }
                for(int j=0;j<7;j++){
                    col_sum+=col_parity[j];
                }
                if(row_sum==1&&col_sum==1){
                    for(int i=0;i<n;i++){
                        int ch=0;
                        for(int j=0;j<7;j++){
                            ch+=input[i][j]*((int) Math.pow(2,6-j));
                        }
                        System.out.print((char) ch+"\t");
                    }
                    System.out.println();
                    int index_i=0,index_j=0;
                    for(int i=0;i<n;i++){
                        if(row_parity[i]==1)
                            index_i=i;
                    }
                    for(int j=0;j<7;j++){
                        if(col_parity[j]==1)
                            index_j=j;
                    }
                    input[index_i][index_j]=(input[index_i][index_j]+1)%2;
                    System.out.println("Error detected and resolved");
                    System.out.println("After Correction :");
                    for(int i=0;i<n;i++){
                        int ch=0;
                        for(int j=0;j<7;j++){
                            ch+=input[i][j]*((int) Math.pow(2,6-j));
                        }
                        System.out.print((char) ch+"\t");
                    }
                    System.out.println();
                }
            }
        }
    }
}
