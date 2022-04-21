import java.util.*;
//Omar Shalaby COP3503C 4/1/2022

public class shortestpath2 {

    public static int oo = 987654321;//meant to check for out of range
    public static Scanner stdin = new Scanner(System.in);
    public static int[] dist;//store answers

    public static void main(String[]  args){

        int n = stdin.nextInt();//reads in inputs
        int m = stdin.nextInt();
        int q = stdin.nextInt();
        int s = stdin.nextInt();

        while(n + m + s + q != 0) {
            dist = new int[n];
            node[] graph = new node[n];
            int[] endings = new int[q];

            for (int i = 0; i < n; i++)
                graph[i] = new node();

            for (int i = 0; i < m; i++) {
                edge tmp = new edge();//inputs each edge and stores it around for each node
                int v1 = stdin.nextInt();

                tmp.ending = stdin.nextInt();
                tmp.t0 = stdin.nextInt();
                tmp.interval = stdin.nextInt();
                tmp.weight = stdin.nextInt();

                graph[v1].connect.add(tmp);
            }

            for (int i = 0; i < q; i++) {
                endings[i] = stdin.nextInt();
            }
                dist = dijkstra(graph, s); //completes Dijkstra's algo

            for(int i = 0; i < q; i++) {//prints answers
                if(endings[i] >= dist.length) continue;

                if (dist[endings[i]] != oo) {
                    System.out.println(dist[endings[i]]);
                } else {
                    System.out.println("Impossible");
                }
            }


             n = stdin.nextInt();
             m = stdin.nextInt();
             q = stdin.nextInt();// get next inputs
             s = stdin.nextInt();

            System.out.println();
        }

    }

    public static int[] dijkstra(node[] graph, int start){

        int n = graph.length;
        boolean[] traveled = new boolean[n];
        int[] ret = new int[n];
        Arrays.fill(ret, oo);//intialize array and values
        ret[start] = 0;

        PriorityQueue<Path> queue =  new PriorityQueue<>();
        queue.add(new Path(start, 0));

        while(!queue.isEmpty()){

            Path processedPath = queue.poll();
            int currNode = processedPath.node;//checks edges around each node

            if(traveled[currNode])
                continue;
            traveled[currNode] = true;

            for(edge e: graph[currNode].connect){
                int currTime = processedPath.length;
                int startTime = e.t0;

                if(currTime > e.t0 && e.interval == 0) continue;//it can't access the edge, skips to next edge
                else if(e.interval == 0) currTime = startTime;
                else { //makes it so it's the correct time to traverse
                    int i =0;
                    while(currTime > startTime){
                        startTime = e.t0 + (e.interval * i);
                        i++;
                    }
                    currTime = startTime;
                }
                int next = e.ending;
                int nextWeight = e.weight + currTime;

                if(next >= ret.length) continue;

                if(ret[next] > nextWeight){//if it finds a faster path, adds that to the output
                    ret[next] = nextWeight;
                    queue.add(new Path(next, nextWeight));
                }
            }

        }


        return ret;//returns output
    }


}

class node{

    ArrayList<edge> connect = new ArrayList<>();

}

class edge{

    int ending;
    int interval;
    int t0;
    int weight;

}

class Path implements Comparable<Path> {
    int node, length;
    Path(int node, int weight) {
        this.node = node;
        this.length = weight;
    }
    public int compareTo(Path o) {
        return length - o.length;
    }
}