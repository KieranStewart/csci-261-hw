import java.util.Arrays;

public class WeightedInversions {
    public static long countWeighted(int[] A) {
        int n = A.length;
        if (n <= 1)
            return 0;

        int m = n / 2;

        // Split into two halves
        int[] B = Arrays.copyOfRange(A, 0, m);
        int[] C = Arrays.copyOfRange(A, m, n);

        // Recursive counts
        long leftCount = countWeighted(B);
        long rightCount = countWeighted(C);

        // Sort halves
        Arrays.sort(B);
        Arrays.sort(C);

        // Mid count
        long midCount = countMidInvWeighted(B, C);

        return leftCount + rightCount + midCount;
    }

    private static long countMidInvWeighted(int[] Bs, int[] Cs) {
        int i = 0, j = 0;
        long count = 0;
        int nB = Bs.length, nC = Cs.length;

        while (i < nB && j < nC) {
            if (Bs[i] <= Cs[j]) {
                i++;
            } else {
                // For each Bs[i] > Cs[j], add Bs[i] - Cs[j] for all Bs[i] left
                count += (long) (nB - i) * (Bs[i] - Cs[j]);
                j++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int inputLen = Integer.parseInt(scanner.nextLine());
        
        int[] array = new int[inputLen];
        for (int i = 0; i < inputLen; i ++) {
            array[i] = Integer.parseInt(scanner.nextLine());
        }
        System.out.println(countWeighted(array));
        scanner.close();
    }
}
