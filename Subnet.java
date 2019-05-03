import java.io.*;
import java.util.*;
class Subnet{
public static void main(String args[]){
Scanner sc = new Scanner(System.in);
System.out.println("Enter the ip address: ");
String ip = sc.nextLine();
System.out.println("Enter the no of network ids: ");
int nw=sc.nextInt();
int s=0;
int d;
if(nw>24&&nw<32)
   s=(int)Math.pow(2,(nw-24));
else if(nw>16&&nw<24)
	s=(int)Math.pow(2, nw-16);
else if(nw>8&&nw<16)
	s=(int)Math.pow(2, nw-8);
else
	System.out.println("wrong ip");
System.out.println("no of subnets possible is"+ s);
String split_ip[] = ip.split("\\."); //SPlit the string after every .
String split_bip[] = new String[4]; //split binary ip
String bip = "";
for(int i=0;i<4;i++){
split_bip[i] = appendZeros(Integer.toBinaryString(Integer.parseInt(split_ip[i]))); // “18” => 18 => 10010 => 00010010
bip += split_bip[i];
}
System.out.println("IP in binary is "+bip);
System.out.print("Enter the number of addresses: ");
int n = sc.nextInt();

//Calculation of mask

int bits = (int)Math.ceil(Math.log(n)/Math.log(2)); /*eg if address = 120, log 120/log 2 gives log to the base 2 => 6.9068, ceil gives us upper integer */
/*System.out.println("Number of bits required for address = "+bits);
int mask = 32-bits;
System.out.println("The subnet mask is = "+mask);*/
String s1="";
String s2="11111111";
String s3="00000000";
int l=nw%8;

	for(int i=0;i<8;i++)
	{	if(l!=0)
		{
		s1=s1.concat("1");l--;
		}
		else
		s1=s1.concat("0");
	}
	//System.out.println(s1);
	int h;
	System.out.println("subnet mask is");
int k=0;
for(int i=0;i<nw/8;i++)
{
	h=Integer.parseInt(s2,2);
	System.out.print(h+".");
	k++;
}
System.out.print(Integer.parseInt(s1,2));
while(k!=3)
{
	System.out.print("."+Integer.parseInt(s3,2));
	k++;
}
	System.out.println();
int p=32-((nw/8)*8);
int div=(int)Math.pow(2,p);
k=0;
int t,o;
System.out.println("no of hosts per subnet is"+(div/s));
for(int j=0;j<s;j++)
{k=0;
	System.out.println("first address of "+(j+1)+"subnet is");
for(int i=0;i<nw/8;i++)
{
	System.out.print(split_ip[i]+".");k++;
	
}
t=div/s;
o=nw%8;
while(k!=3)
{
	if((div/s)>256)
	{	
		t=div/(int)Math.pow(2, 8-o);
		System.out.print(t*(j));
		System.out.print(".");
	}	
	k++;
}
System.out.print((t)*j);
System.out.println();
k=0;
System.out.println("last address of "+(j+1)+"subnet is");
for(int i=0;i<nw/8;i++)
{
	System.out.print(split_ip[i]+".");k++;
}
while(k!=3)
{
	if((div/s)>256)
	{	t=div/(int)Math.pow(2, 8-o);
		
		System.out.print(t*(j+1)-1);
		System.out.print(".");
	}	
	k++;
}

System.out.print((t)*(j+1)-1);
System.out.println();
}

//Calculation of first address and last address
int fbip[] = new int[32];
for(int i=0; i<32;i++) fbip[i] = (int)bip.charAt(i)-48; //convert cahracter 0,1 to integer 0,1
for(int i=31;i>31-bits;i--)//Get first address by ANDing last n bits with 0
fbip[i] &= 0;
String fip[] = {"","","",""};
for(int i=0;i<32;i++)
fip[i/8] = new String(fip[i/8]+fbip[i]);
System.out.print("First address is = ");
for(int i=0;i<4;i++){
System.out.print(Integer.parseInt(fip[i],2));
if(i!=3) System.out.print(".");
}
System.out.println();

int lbip[] = new int[32];
for(int i=0; i<32;i++) lbip[i] = (int)bip.charAt(i)-48; //convert cahracter 0,1 to integer 0,1
for(int i=31;i>31-bits;i--)//Get last address by ORing last n bits with 1
lbip[i] |= 1;
String lip[] = {"","","",""};
for(int i=0;i<32;i++)
lip[i/8] = new String(lip[i/8]+lbip[i]);
System.out.print("Last address is = ");
for(int i=0;i<4;i++){
System.out.print(Integer.parseInt(lip[i],2));
if(i!=3) System.out.print(".");
}
System.out.println();
}
static String appendZeros(String s){
String temp = new String("00000000");
return temp.substring(s.length())+ s;
}
}
