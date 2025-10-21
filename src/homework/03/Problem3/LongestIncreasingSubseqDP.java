import java.util.Scanner;


public class LongestIncreasingSubseqDP {
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());
        int[] input_arr = new int[len];
        String in = sc.nextLine();
        String[] raw_input_arr = in.trim().split("\\s+");//FIXME
        for (int i = 0; i < len; i ++) {
            input_arr[i] = Integer.parseInt(raw_input_arr[i]);
        }

        int result = longestIncreasingSubsequence(input_arr);
        System.out.println("" + result);
        sc.close();
    }  
}
