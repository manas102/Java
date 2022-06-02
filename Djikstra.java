import java.util.*;

class Djikstra {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int e = sc.nextInt();
        Map<Integer, List<Integer[]>> adj = new HashMap<>();
        for(int i = 0; i < e; ++i){
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adj.computeIfAbsent(u,ign->new ArrayList<>()).add(new Integer[]{v,w});
            adj.computeIfAbsent(v,ign->new ArrayList<>()).add(new Integer[]{u,w});
        }
        int[] dist = new int[n+1];
        boolean[] seen = new boolean[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->dist[a]-dist[b]);
        dist[1] = 0;
        minHeap.offer(1);
        while(!minHeap.isEmpty()){
            int u = minHeap.poll();
            if(seen[u] == true) continue;
            seen[u] = true;
            if(adj.containsKey(u)){
                for(Integer[] edge: adj.get(u)){
                    int v = edge[0], w = edge[1];
                    if(dist[v] > dist[u] + w){
                        dist[v] = dist[u] + w;
                        minHeap.offer(v);
                    }
                }
            }  
        }
        for(int i = 2; i <= n; ++i){
            System.out.print(dist[i]+ " ");
        }

    }
}
