import java.util.Arrays;

public class HorizontalCrossing {
    public static int findHighestCrossings(int[][] array) {
        int[][] events = new int[array.length * 2][2];
        for (int i = 0; i < array.length; i ++) {
            int y1 = array[i][1];
            int y2 = array[(i+1)%array.length][1];
            if (y2 < y1) {
                events[i] = new int[] {y2, 1};
                events[i * 2] = new int[] {y1, -1}; 
            }
            else {
                events[i] = new int[] {y1, 1};
                events[i * 2] = new int[] {y2, -1};
            }
        }
        // Sort events by the first value in each event (the y-coordinate)
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        int max_simul = 0;
        int max_simul_location = 0;
        int current_simul = 0;

        for (int i = 0; i < events.length; i ++) {
            current_simul += events[i][1];
            if (current_simul > max_simul) {
                max_simul = current_simul;
                max_simul_location = events[i][0];
            }
        }
        return max_simul_location;
    }
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int inputLen = Integer.parseInt(scanner.nextLine());
        
        int[][] array = new int[inputLen][2];
        for (int i = 0; i < inputLen; i ++) {
            String[] parts = scanner.nextLine().split(" ");
            array[i][0] = Integer.parseInt(parts[0]);
            array[i][1] = Integer.parseInt(parts[1]);
        }
        System.out.println(findHighestCrossings(array));
        scanner.close();
    }
}
