public class DoubleKnapsackWithSolution {
    static int[][] maxWeight(int[] costs, int[] weights, int W1, int W2, int n) {
        int[][][] S = new int[n+1][W1+1][W2+1];
        for (int v = 0; v <= W1; v ++) {
            for (int w = 0; w <= W2; w++) {
                S[0][v][w]=0;
            }
        }
        for (int j = 0; j <= n; j ++) {
            S[j][0][0] = 0;
        }

        for (int j = 1; j <= n; j ++) {
            for (int v =0; v <= W1; v ++) {
                for (int w = 0; w<=W2; w++) {
                    S[j][v][w] = S[j-1][v][w];
                    if (weights[j-1] <= v && S[j-1][v-weights[j-1]][w] + costs[j-1] > S[j][v][w]) {
                        S[j][v][w]=S[j-1][v-weights[j-1]][w] + costs[j-1];
                    }
                    if (weights[j-1] <= w && S[j-1][v][w-weights[j-1]] + costs[j-1] > S[j][v][w]) {
                        S[j][v][w] = S[j-1][v][w-weights[j-1]] + costs[j-1];
                    }
                }
            }
        }
        System.out.println("Max Cost:" + S[n][W1][W2]);
        return reconstructSolution(costs, weights, W1, W2, n, S);
    }

    static int[][] reconstructSolution(int[] costs, int[] weights, int W1, int W2, int n, int[][][] S) {
        int j = n; int v = W1; int w=W2;
        int[][] out = new int[2][costs.length];
        int[] outLengths = new int[] {0,0};

        while (j>0 && v>0 && w>0) {
            if (S[j-1][v][w] == S[j][v][w]) j--;
            else {
                if (weights[j - 1] <= v && S[j - 1][v - weights[j - 1]][w] + costs[j - 1] == S[j][v][w]) {
                    out[0][outLengths[0]++] = j;
                    v -= weights[j - 1];
                } else if (weights[j - 1] <= w && S[j - 1][v][w - weights[j - 1]] + costs[j - 1] == S[j][v][w]) {
                    out[1][outLengths[1]++] = j;
                    w -= weights[j - 1];
                } else {
                    j--;
                }
            }
        }
        int[][] cleanedOut = new int[2][];
        cleanedOut[0] = new int[outLengths[0]];
        cleanedOut[1] = new int[outLengths[1]];
        for (int m = 0; m < cleanedOut.length; m ++) {
            for (int index = 0; index < cleanedOut[m].length; index ++) {
                cleanedOut[m][index] = out[m][index];
            }
        }
        return cleanedOut;
    }
    public static void main(String[] args) {
        // 6 1
        // 5 7
        // 6 9
        // 8 6
        // 3 3
        int[] costs = new int[] {6,5,6,8,3};
        int[] weights = new int[] {1,7,9,6,3};
        int n = 5;
        int W1 = 10;
        int W2 = 10;
        int[][] result = maxWeight(costs, weights, W1, W2, n);
        for (int i = 0; i < result.length; i ++) {
            System.out.print("[");
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println("]");
        }
    }
}
