import java.util.Scanner;

public class LongestIncreasingSubseqRecursive {
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());
        int[] input_arr = new int[len];
        String in = sc.nextLine();
        String[] raw_input_arr = in.trim().split("\\s+");//FIXME
        for (int i = 0; i < len; i ++) {
            input_arr[i] = Integer.parseInt(raw_input_arr[i]);
        }

        int result = incrSubseqDynamic(input_arr);
        System.out.println("" + result);
        sc.close();
    }    
}
