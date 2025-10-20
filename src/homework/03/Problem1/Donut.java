import java.util.Scanner;

public class Donut {
    // find_optimal_shop_position_2d
    static int[] findOptimalShopPosition2D(int[][] A) {
        int n = A.length;
        int[] X = new int[n];
        int[] Y = new int[n];
        for (int i = 0; i < n; i++) {
            X[i] = A[i][0];
            Y[i] = A[i][1];
        }
        int x = findOptimalPos1D(X);
        int y = findOptimalPos1D(Y);
        return new int[]{x, y};
    }

    static int findOptimalPos1D(int[] A) {
        int k = A.length / 2; // Will automatically round down
        return select(A, k, A.length);
    }

    static int select(int[] A, int k, int len) {
        int pivot_value = A[0];
        int[] below = new int[len];
        int below_len = 0;
        int[] at = new int[len];
        int at_len = 0;
        int[] above = new int[len];
        int above_len = 0;
        for (int i = 0; i < len; i ++) {
            if (A[i] < pivot_value) below[below_len++] = A[i];
            else if (A[i] > pivot_value) above[above_len++] = A[i];
            else at[at_len++] = A[i];
        }
        
        if (k < below_len) return select(below, k, below_len);
        else if (k < below_len + at_len) return pivot_value;
        else return select(above, k-(below_len + at_len), above_len);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());
        int[][] points = new int[len][2];
        for (int i = 0; i < len; i ++) {
            String in = sc.nextLine();
            String[] splitIn = in.split("\s+");
            points[i][0] = Integer.parseInt(splitIn[0]);
            points[i][1] = Integer.parseInt(splitIn[1]);
        }

        int[] optimal = findOptimalShopPosition2D(points);

        int sum = 0;
        for (int i = 0; i < points.length; i ++) {
            sum += Math.abs(optimal[0] - points[i][0]) + Math.abs(optimal[1] - points[i][1]);
        }
        System.out.println("" + sum);
        sc.close();
    }
}