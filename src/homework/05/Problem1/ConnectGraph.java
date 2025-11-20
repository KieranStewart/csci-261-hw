import java.util.Scanner;

public class ConnectGraph {
    // Allows me to pass a pointer in java (im sure there is a better way)
    static class BoolArray {
        boolean[] arr;
        BoolArray(int len) {
            this.arr = new boolean[len];
            for (int i = 0; i < len; i ++) {this.arr[i] = false;}
        }
    }

    static class Edge {
        int start;
        int end;
        Edge (int start, int end) {
            // Adjusted down to account for input data starting at 1
            this.start = start -1; this.end = end-1;
        } 
    }
    private static void dfs(int v, BoolArray seen, int[][] adj, int[] adjLen) {
        seen.arr[v] = true;
        for (int i = 0; i < adjLen[v]; i ++) {
            int u = adj[v][i];
            if (! seen.arr[u]) dfs(u, seen, adj, adjLen);
        }
    }

    private static int findMinConnections(int n, Edge[] edges) {
        int[][] adj = new int[n][n-1]; // Space inneficient I know, but I don't want to make it more complex 
        int[] adjLen = new int[n];
        for (int i = 0; i < n; i ++) {adjLen[i] = 0;}

        for (Edge edge : edges) {
            adj[edge.start][adjLen[edge.start]] = edge.end;
            adjLen[edge.start] += 1;
            adj[edge.end][adjLen[edge.end]] = edge.start;
            adjLen[edge.end] += 1;
        }

        BoolArray visited = new BoolArray(n);

        int components = 0;

        for (int i = 0; i < n; i ++) {
            if (!visited.arr[i]) {
                dfs(i, visited, adj, adjLen);
                components ++;
            }
        }
        return components -1;
    } 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] raw_lens = sc.nextLine().trim().split("\\s+");
        int n = Integer.parseInt(raw_lens[0]);
        int m = Integer.parseInt(raw_lens[1]);
        Edge[] input_arr = new Edge[m];

        for (int i = 0; i < m; i ++) {
            String in = sc.nextLine();
            String[] raw_input = in.trim().split("\\s+");
            input_arr[i] = new Edge(Integer.parseInt(raw_input[0]), Integer.parseInt(raw_input[1]));
        }
        sc.close();
        System.out.println(findMinConnections(n, input_arr));
    }
}