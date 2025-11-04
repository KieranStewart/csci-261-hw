import java.util.Scanner;

public class MatrixChainParenthesize {
    static String backtrack(int[][] S, int[][] SK, int L, int R) {
        if (L==R) return "A" + L;
        int k=SK[L][R];
        return "( " + backtrack(S, SK, L, k) + " x " + backtrack(S, SK, k + 1, R) + " )";
    }
    
    static String findOptimalParens(int[] dimensions) {
        int[][] S = new int[dimensions.length][dimensions.length];
        int[][] SK = new int[dimensions.length][dimensions.length];

        for (int i = 1; i <= dimensions.length - 1; i++) {
            S[i][i] = 0;
        }

        for (int d = 1; d <= dimensions.length - 1 - 1; d++) {
            for (int L = 1; L <= dimensions.length - 1 - d; L++) {
                int R = L + d;
                S[L][R] = Integer.MAX_VALUE;
                for (int k = L; k < R; k++) {
                    int contender = S[L][k] + S[k + 1][R] + dimensions[L - 1] * dimensions[k] * dimensions[R];
                    if (contender < S[L][R]) {
                        S[L][R] = contender;
                        SK[L][R] = k;
                    }
                }
            }
        }

        System.out.println("" + S[1][dimensions.length - 1]);
        String result = backtrack(S, SK, 1, dimensions.length - 1);
        System.out.println(result);
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());

        int[] dimensions = new int[len+1];

        String[] raw_dimensions = sc.nextLine().trim().split("\\s+");

        for (int i = 0; i < raw_dimensions.length; i ++) {
            dimensions[i] = Integer.parseInt(raw_dimensions[i]);
        }
        sc.close();
        findOptimalParens(dimensions);
    }
}
