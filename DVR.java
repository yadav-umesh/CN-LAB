import java.util.Scanner;

public class DVR {
    public static void main(String args[]){
        System.out.println("Enter no of routers \t");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int adj[][] = new int[n][n];
        System.out.println("Enter undirected weighted adjacency matrix");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                String input = sc.next();
                if(input.equals("INF"))  adj[i][j] = 99999999;
                else adj[i][j] = Integer.parseInt(input);
            }
        }
        int routing_table[][][]=new int[n][n][3];
        int previous[][] = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++) {
                routing_table[i][j][0] = j;
                routing_table[i][j][1] = adj[i][j];
                previous[i][j] = adj[i][j];
                if(adj[i][j]!=99999999)
                    routing_table[i][j][2] = j;
                else
                    routing_table[i][j][2] = -1;
            }
        }
        for(int a0=0;a0<n-2;a0++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(i==j){
                        routing_table[i][j][1] = 0;
                        routing_table[i][j][2] = i;
                        continue;
                    }
                    int min_index=-1;
                    int min=99999999;
                    for(int k=0;k<n;k++){
                        if(i==k) continue;
                        if(adj[i][k]==99999999) continue;
                        if(min>adj[i][k]+previous[k][j]) {
                            min = adj[i][k] + previous[k][j];
                            min_index = k;
                        }
                    }
                    routing_table[i][j][1] = min;
                    routing_table[i][j][2] = min_index;
                }
            }
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    previous[i][j]=routing_table[i][j][1];
                }
            }
        }
        System.out.println("The following are the routing tables at each node");
        for(int i=0;i<n;i++){
            System.out.println("Routing table at \t"+(char)(i+65)+" :");
            System.out.println("Destination\tDistance\tNext Hop");
            for(int j=0;j<n;j++){
                System.out.println("\t"+(char)(routing_table[i][j][0]+65)
                        +"\t\t\t"+routing_table[i][j][1]
                        +"\t\t\t"+(char)(routing_table[i][j][2]+65));
            }
            System.out.println("\n\n\n");
        }
    }
}
