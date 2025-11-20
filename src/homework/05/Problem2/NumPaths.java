import java.util.Scanner;

public class NumPaths {

    static class Edge {
        int start;
        int end;
        Edge (int start, int end) {
            // Adjusted down to account for input data starting at 1
            this.start = start -1; this.end = end-1;
        } 
    }
    
    public static int findMaxShortestPaths(int[][] adj, int s, int t) {

        int n = adj.length;

        boolean[] seen = new boolean[n];
        int[] dist = new int[n];
        int[] count = new int[n];

        for (int v = 0; v < n; v++) {
            seen[v] = false;
            dist[v] = Integer.MAX_VALUE;
            count[v] = 0;
        }

        count[s] = 1;
        dist[s] = 0;

        int[] Q = new int[n];
        int beg = 0;
        int end = 0;

        Q[end++] = s;
        seen[s] = true;

        while (beg < end) {
            int head = Q[beg++];
            for (int u : adj[head]) {
                if (!seen[u]) {
                    Q[end++] = u;
                    dist[u] = dist[head] + 1;
                    seen[u] = true;
                    count[u] = count[head];
                } else if (dist[u] == dist[head] + 1) {
                    count[u] += count[head];
                }
            }
        }

        return count[t];
    }

    private static int[][] generateUndirectedAdjacencyList(int n, Edge[] edges) {
        int[][] adj = new int[n][n];
        int[] adjLen = new int[n];

        for (Edge edge : edges) {
            adj[edge.start][adjLen[edge.start]] = edge.end;
            adjLen[edge.start] += 1;
            adj[edge.end][adjLen[edge.end]] = edge.start;
            adjLen[edge.end] += 1;
        }

        // trim the arrays (so adj.length is accurate)
        for (int i = 0; i < adj.length; i ++) {
            int[] adjI = new int[adjLen[i]];
            for (int j = 0; j < adjI.length; j ++) {
                adjI[j] = adj[i][j];
            }
            adj[i] = adjI;
        }
        return adj;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] raw_lens = sc.nextLine().trim().split("\\s+");
        int n = Integer.parseInt(raw_lens[0]);
        int m = Integer.parseInt(raw_lens[1]);

        String[] raw_st = sc.nextLine().trim().split("\\s+");
        int t = Integer.parseInt(raw_st[1]) - 1; // -1 to offset from 1 start to 0 start
        int s = Integer.parseInt(raw_st[0]) - 1; // -1 to offset from 1 start to 0 start

        Edge[] input_arr = new Edge[m];

        for (int i = 0; i < m; i ++) {
            String in = sc.nextLine();
            String[] raw_input = in.trim().split("\\s+");
            input_arr[i] = new Edge(Integer.parseInt(raw_input[0]), Integer.parseInt(raw_input[1]));
        }
        sc.close();

        int[][] adj = generateUndirectedAdjacencyList(n, input_arr);

        int paths = findMaxShortestPaths(adj, s, t);
        System.out.println("Number of shortest paths from " + s + " to " + t + " = " + paths);
    }
}
