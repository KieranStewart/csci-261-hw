import java.util.Arrays;
import java.util.Scanner;

public class IntervalsBreaks {
    static int findMaxClasses(int[][] A, int[][] B) {
        Arrays.sort(A, (a, b) -> Integer.compare(a[1], b[1]));
        int[] S = new int[A.length + 1];
        S[0] = 0;
        for (int j = 0; j < A.length; j ++) {
            int k = j;
            while (k >= 0 && A[k][1] + B[k][j] > A[j][0]) k --;
            S[j+1] = S[j];
            if (k >= 0 && S[k+1] + 1 > S[j+1]) S[j+1] = S[k+1] + 1;
        }
        return S[A.length] + 1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());
        int[][] input_arr = new int[len][2];
        int[][] travel_arr = new int[len][len];

        for (int i = 0; i < len; i ++) {
            String in = sc.nextLine();
            String[] raw_input_arr = in.trim().split("\\s+");
            input_arr[i] = new int[] {Integer.parseInt(raw_input_arr[0]), Integer.parseInt(raw_input_arr[1])};
        }
        for (int i = 0; i < len; i ++) {
            String in = sc.nextLine();
            String[] raw_input_arr = in.trim().split("\\s+");
            for (int j = 0; j < len; j ++) {
                travel_arr[i][j] = Integer.parseInt(raw_input_arr[j]);
            }
        }

        int result = findMaxClasses(input_arr, travel_arr);
        System.out.println("" + result);
        sc.close();
    }  
    
}
