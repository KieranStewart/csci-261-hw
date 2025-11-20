import java.util.*;

public class Prerequisites {
    
    public static int longestPrerequisiteChain(int n, int[][] prerequisites) {
        int m = 0;
        for (int i = 0; i < n; i++) {
            m += prerequisites[i].length;
        }

        // create adj list
        int[] head = new int[n];
        int[] next = new int[m];
        int[] to = new int[m];
        Arrays.fill(head, -1);

        int[] indegree = new int[n];
        int edgeIndex = 0;

        for (int course = 0; course < n; course++) {
            for (int prereq : prerequisites[course]) {
                to[edgeIndex] = course;
                next[edgeIndex] = head[prereq];
                head[prereq] = edgeIndex;
                indegree[course]++;
                edgeIndex++;
            }
        }

        // queue w/ array 
        int[] queue = new int[n];
        int front = 0, back = 0;

        int[] S = new int[n];
        Arrays.fill(S, 1);

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue[back++] = i;
            }
        }

        while (front < back) {
            int u = queue[front++];
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                S[v] = Math.max(S[v], S[u] + 1);
                indegree[v]--;
                if (indegree[v] == 0) {
                    queue[back++] = v;
                }
            }
        }

        int longest = 0;
        for (int len : S) {
            longest = Math.max(longest, len);
        }
        return longest;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        int[][] prereqs = new int[n][];

        for (int i = 0; i < n; i ++) {
            String in = sc.nextLine();
            String[] raw_input = in.trim().split("\\s+");
            int[] ithPrereqs = new int[raw_input.length - 1];
            for (int j = 0; j < ithPrereqs.length; j ++) {
                ithPrereqs[j] = Integer.parseInt(raw_input[j]) - 1;
            }
            prereqs[i] = ithPrereqs;
        }
        sc.close();

        int result = longestPrerequisiteChain(n, prereqs);
        System.out.println("" + result);
    }
}
