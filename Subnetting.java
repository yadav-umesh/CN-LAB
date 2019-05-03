import java.io.*;
import java.util.*;
import java.util.Random;

public class Subnetting 
{
	int pn,hosts,thput,rpn;
	int sub,nsub,rnsub;
	double thosts;
	double CA,CB,CC;
	double bu,br;
	char cl;
	char min_class;
	int tc=0;
	double takenhosts=0;
	Address ad=new Address();
	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter Number of Physical Networks:");
		int temp=Integer.parseInt(br.readLine());
		System.out.print("Enter Number of Hosts/Network:");
		int temp1=Integer.parseInt(br.readLine());
		Subnetting sb=new Subnetting(temp,temp1);
		sb.check();
		sb.check_class();
		sb.check_snets();
		System.out.println("\nNumber of Classes Used:"+sb.tc);
		System.out.println("\nThroughput:"+(sb.thosts/sb.takenhosts));
	}
	
	Subnetting(int n1,int n2)
	{
		pn=n1;
		hosts=n2;
		CC=254;
		CB=65534;
		CA=16777214;
	}
	
	void check()
	{
		thosts=pn*hosts;
		
		if(hosts<254)
		{
			min_class='C';
		}
		else if (hosts<65534)
		{
			min_class='B';
		}
		else
		{
			min_class='A';
		}
	}
	
	void check_class()
	{
		if(thosts<=CC)
		{
			cl='C';
		}
		else if(thosts<=CB)
		{
			cl='B';
		}
		else
		{
			cl='A';
		}
		
		//cl=min_class;
	}
	
	void check_snets()
	{
		int temp=pn;
		int count=0;
		int fact=1;
		while((temp/fact)>0)
		{
			fact=fact*2;
			count++;
		}
	//	System.out.println(count);
		if(Math.pow(2,count-1)==temp)
			bu=count-1;
		else	bu=count;
		if(cl=='A')
		{
			br=24-bu;
			takenhosts+=CA;
			takenhosts=takenhosts-2*Math.pow(2, bu);
		}
		else if(cl=='B')
		{
			br=16-bu;
			takenhosts+=CB;
			takenhosts=takenhosts-2*Math.pow(2, bu);
		}
		else 
		{
			br=8-bu;
			takenhosts+=CC;
			takenhosts=takenhosts-2*Math.pow(2, bu);
		}
		double temp3=Math.pow(2, br);
		//System.out.println(temp3);
		//System.out.println(min_class);
		nsub=fact;
		if((temp3-2)<hosts)
			call_split();
		else 
			{
			//tc++;
			calculate();
			}
	}
	
	void call_split()
	{
		double temp2=br+1;
		double npn=0;
		double temp1=Math.pow(2, temp2);
		//System.out.println(temp1);
		if((temp1-2)>hosts)
		{
			bu=bu-1;
			br=br+1;
			//if(bu>0)
			//{
				npn=Math.pow(2, bu);
			//}
			
			//System.out.println(bu);
			//System.out.println(npn);
			rpn=pn-(int)npn;
		//	System.out.println(rpn);
			tc++;
			System.out.println("Class Type:"+cl);
			System.out.println("Number of Subnets:"+npn);
			System.out.println("Number of hosts per Subnet:"+hosts);
			ad.generateip(cl,bu,npn,pn,hosts);
			//System.out.println("First Host:");
			//System.out.println("Last Host:");
			System.out.println("\n");
			calc_rem();
		}
		else
		{
			bu=bu-1;
			br=br+1;
			call_split();
		}
	}
	
	void calc_rem()
	{
		pn=rpn;
		//System.out.println(pn);
		//tc++;
	/*	if(min_class=='A')
			{
			takenhosts+=CA;
			takenhosts=takenhosts-2*Math.pow(2, bu);
			}
		else if (min_class=='B')
		{
			takenhosts+=CB;
			takenhosts=takenhosts-2*Math.pow(2, bu);
		}
		else 
			{
			takenhosts+=CC;
			takenhosts=takenhosts-2*Math.pow(2, bu);
			}
			*/
		check_snets();
	}
	
	void calculate()
	{
		double npn=Math.pow(2, bu);
		tc++;
		
		System.out.println("Class Type:"+cl);
		System.out.println("Number of Subnets:"+npn);
		System.out.println("Number of hosts per Subnet:"+hosts);
		ad.generateip(cl,bu,npn,pn,hosts);
		//System.out.println("First Host:");
		//System.out.println("Last Host:");
		
	}
}

class Address
{
	char cl;
	double bu;
	int subnet;
	int exist=0;
	int x=-1,y=-1,z=-1;
	Random rand = new Random();
	Address()
	{
		
	}
	
	void generateip(char c,double bits,double npn,int pn,int hosts)
	{
		cl=c;
		bu=bits;
		subnet=(int)npn;
		//if(exist==0)
		//{
			exist=1;
			if(cl=='A')
			{
			x = rand.nextInt(126)+1;
			y=0;
			z=0;
			}
			
			else if (cl=='B')
			{
				x = rand.nextInt(64)+128;
				y = rand.nextInt(256);
				z=0;
			}
			
			else if (cl=='C')
			{
				x = rand.nextInt(32)+192;
				y = rand.nextInt(256);
				z = rand.nextInt(256);
			}
		//}
		int k,k1;
		int div=256/subnet;
		if(cl=='C')
		{
			k=z;
			k1=hosts;
		}
		else
		{
		k=((pn-1)*div)+hosts/254;
		k1=254-(hosts/254)+1;
		}
		System.out.println("First Host:"+x+"."+y+"."+z+"."+"1");
		System.out.println("Last Host:"+x+"."+y+"."+k+"."+k1);
		
		x=-1;y=-1;z=-1;
	}
}