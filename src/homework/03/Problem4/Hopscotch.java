import java.util.Scanner;

public class Hopscotch {
    static int findBestHopscotch(int[] A) {
        if (A.length < 3) return A[0];
        int[] S = new int[A.length];
        S[0] = A[0];
        S[1] = 0;
        S[2] = S[0] + A[2];
        for (int i = 3; i < A.length; i ++) {
            if (S[i-2] > S[i-3]) S[i] = S[i-2] + A[i];
            else S[i] = S[i-3] + A[i];
        }
        int max = 0;
        for (int i = 0; i < S.length; i ++)
            if (S[i] > max) max = S[i];
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

        int result = findBestHopscotch(input_arr);
        System.out.println("" + result);
        sc.close();
    }  
    
}
