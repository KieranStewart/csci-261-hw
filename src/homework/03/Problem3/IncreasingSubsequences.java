public class IncreasingSubsequences {
    public static int incrSubseqRecursive(int[] A, int j) {
        int maxLen = 0;

        for (int i = 0; i < j; i++) {
            if (A[i] < A[j]) {
                int subseqLen = incrSubseqRecursive(A, i);
                if (subseqLen > maxLen) {
                    maxLen = subseqLen;
                }
            }
        }
        return maxLen + 1;
    }

    public static int longestIncreasingSubsequence(int[] A) { // A wrapper for the recurrence
        int n = A.length;
        int overallMax = 0;

        for (int j = 0; j < n; j++) {
            int len = incrSubseqRecursive(A, j);
            if (len > overallMax) {
                overallMax = len;
            }
        }
        return overallMax;
    }

    public static int incrSubseqDynamic(int[] A) {
        int[] S = new int[A.length];
        for (int j = 0; j < A.length; j ++) {
            S[j] = 1;
            for (int k = 0; k < j; k ++) {
                if (A[k] < A[j] && S[j] < S[k] + 1) {
                    S[j] = S[k] + 1;
                }
            }
        }
        int max = 0;
        for (int j = 0; j < S.length; j ++) {
            if (S[j] > max) {
                max = S[j];
            }
        }
        return max;
    }

    static int randomInt() {
        return (int)(Math.random() * 100);
    }
    public static void main(String[] args) {
        int[] N_VALUES = {0, 5, 10, 20, 30, 40, 50, 60, 70, 80};
        long[][] results = new long[N_VALUES.length][3]; // Col 1: n value, 2: recursive time, 3: dynamic time
        for (int i = 0; i < results.length; i ++) {
            int n_value = N_VALUES[i];
            System.out.printf("Testing With Length <%d> (%d/%d complete)\n", n_value, i, results.length);
            int[] input_arr = new int[n_value];
            for (int j = 0; j < n_value; j ++) input_arr[j] = randomInt(); // Generate random values to fill the array

            long startTimeRecursive = System.nanoTime();
            int recursiveResult = longestIncreasingSubsequence(input_arr.clone());
            long endTimeRecursive = System.nanoTime();

            long startTimeDynamic = System.nanoTime();
            int dynamicResult = incrSubseqDynamic(input_arr.clone());
            long endTimeDynamic = System.nanoTime();

            if (dynamicResult != recursiveResult) {
                System.out.printf("Results do not match! %d!=%d\n", recursiveResult, dynamicResult);
                for (int j = 0; j < n_value; j ++) {
                    System.out.printf("\t%d\n", input_arr[j]);
                }
            }

            results[i][0] = n_value;
            results[i][1] = endTimeRecursive - startTimeRecursive;
            results[i][2] = endTimeDynamic - startTimeDynamic;
        }
        try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.File("results.csv"))) {
            pw.println("n,recursiveTimeMillis,dynamicTimeMillis");
            for (long[] row : results) {
                pw.printf("%d,%d,%d%n", row[0], row[1], row[2]);
            }
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
