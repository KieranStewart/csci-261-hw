import java.util.Scanner;

public class LongestCommonSubseqThree {
    static class Solution {
        public final int length;
        public final int[][][] S;
        public Solution(int length, int[][][] S) {
            this.length = length;
            this.S = S;
        }
    }

    private static int max(int a, int b, int c) {
        if (a > b && a > c)
            return a;
        if (b > c)
            return b;
        return c;
    } 

    private static Solution findLongestSubseq(int[] A, int[] B, int[]C) {
        int[][][] S = new int[A.length+1][B.length+1][C.length+1];

        // Initialize the first plane on each of the 0 sides
        for (int j = 0; j <= A.length; j ++) {
            for (int k = 0; k <= B.length; k++) {
                S[j][k][0]=0;
            }
        }
        for (int j = 0; j <= A.length; j ++) {
            for (int l = 0; l <= B.length; l++) {
                S[j][l][0]=0;
            }
        }
        for (int k = 0; k <= A.length; k ++) {
            for (int l = 0; l <= B.length; l++) {
                S[k][l][0]=0;
            }
        }

        for (int j = 1; j <= A.length; j ++) {
            for (int k = 1; k <= B.length; k ++) {
                for (int l = 1; l <=C.length; l ++) {
                    S[j][k][l] = max(S[j-1][k][l], S[j][k-1][l], S[j][k][l-1]);
                    if (A[j-1] == B[k-1] && B[k-1] == C[l-1] && S[j-1][k-1][l-1] + 1 > S[j][k][l]) {
                        S[j][k][l] = S[j-1][k-1][l-1] + 1;
                    }
                }
            }
        }
        return new Solution(S[A.length][B.length][C.length], S);
    }

    private static int[] reconstructSolution(int[] A, int[] B, int[] C, Solution solution) {
        int j = A.length;
        int k = B.length;
        int l = C.length;

        int[] out = new int[solution.length];
        int outIndex = solution.length;

        while (j>0 && k>0 && l>0) {
            if (A[j-1] == B[k-1] && A[j-1] == C[l-1]) {
                j--;k--;l--;
                out[--outIndex] = A[j];
            }
            else if (solution.S[j-1][k][l]>solution.S[j][k-1][l] && solution.S[j-1][k][l]>solution.S[j][k][l-1]) j--;
            else if (solution.S[j][k-1][l] > solution.S[j][k][l-1]) k --;
            else l--;
        }
        return out;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] raw_lens = sc.nextLine().trim().split("\\s+");
        int[] lens = new int[3];
        for (int i = 0; i < 3; i ++){
            lens[i] = Integer.parseInt(raw_lens[i]);
        }
        String[][] raw_input_arr = new String[3][];
        int[][] input_arr = new int[3][];

        for (int i = 0; i < 3; i ++) {
            String in = sc.nextLine();
            raw_input_arr[i] = in.trim().split("\\s+");
            input_arr[i] = new int[lens[i]];
            for (int j = 0; j < lens[i]; j ++) {
                input_arr[i][j] = Integer.parseInt(raw_input_arr[i][j]);
            }
        }
        sc.close();
        
        int[] A = input_arr[0];
        int[] B = input_arr[1]; 
        int[] C = input_arr[2];
        Solution solution = findLongestSubseq(A, B, C);
        int[] solutionArray = reconstructSolution(A, B, C, solution);
        System.out.println(solution.length + "");
        for (int i = 0; i < solutionArray.length; i ++) {
            System.out.print(solutionArray[i] + " ");
        }
    }  
}
