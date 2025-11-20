import java.util.Scanner;

public class DoubleTrouble {

    // Some static variables so I don't have to pass things around a whole lot
    static int a, b; // maze dimensions
    static char[][] maze;
    static int[][] directions = { {-1,0}, {1,0}, {0,-1}, {0,1} }; // Lets me iterate over the directions

    // Encode a state (x1,y1,x2,y2) into a single integer (diy hashing)
    static int encode(int x1, int y1, int x2, int y2) {
        int totalCells = a * b;
        return ((x1 * b + y1) * totalCells) + (x2 * b + y2);
    }

    // Attempt to move one Thing
    static int[] move(int x, int y, int dx, int dy) {
        int nx = x + dx;
        int ny = y + dy;
        
        // outside, return special case -1
        if (nx < 0 || nx >= a || ny < 0 || ny >= b) {
            return new int[] { -1, -1 };
        }

        // inside and space is empty
        if (maze[nx][ny] == '.') {
            return new int[] { nx, ny };
        }

        // can't move
        return new int[] { x, y };
    }

    // Check if moving in direction exits the maze
    static boolean isExit(int x, int y, int dx, int dy) {
        int nx = x + dx;
        int ny = y + dy;
        return !(nx >= 0 && nx < a && ny >= 0 && ny < b);
    }

    /**
     * Finds out if the things can go out of their house.
     * (Sorry for the many comments, its the only way I could get my thoughts together while debugging)
     */
    public static int thingsEscapeHouse(char[][] inputMaze, int[] start1, int[] start2) {
        maze = inputMaze;
        a = maze.length;
        b = maze[0].length;

        int totalCells = a * b;
        int totalStates = totalCells * totalCells;

        // visited array
        boolean[] visited = new boolean[totalStates];

        // BFS queue implemented with arrays
        int[][] queue = new int[totalStates][5]; // each entry: x1,y1,x2,y2,dist
        int head = 0, tail = 0;

        // initialize
        queue[tail++] = new int[]{start1[0], start1[1], start2[0], start2[1], 0};
        visited[encode(start1[0], start1[1], start2[0], start2[1])] = true;

        // BFS loop
        while (head < tail) {
            int[] cur = queue[head++];
            int x1 = cur[0], y1 = cur[1], x2 = cur[2], y2 = cur[3], dist = cur[4];

            for (int[] dir : directions) {
                int dx = dir[0], dy = dir[1];

                int[] next1 = move(x1, y1, dx, dy);
                int[] next2 = move(x2, y2, dx, dy);

                // done?
                if (next1[0] == -1 && next2[0] == -1) {
                    System.out.println(dist + 1);
                    return dist + 1;
                }

                // if move is invalid (one exits)
                if (next1[0] == -1 || next2[0] == -1) {
                    continue;
                }

                // collisions while moving or not
                if (next1[0] == next2[0] && next1[1] == next2[1] &&
                    !(next1[0] == x2 && next1[1] == y2 && next2[0] == x1 && next2[1] == y1)) {
                    continue;
                }

                // So we can put in array (diy hashmap)
                int stateIndex = encode(next1[0], next1[1], next2[0], next2[1]);
                if (!visited[stateIndex]) {
                    visited[stateIndex] = true;
                    queue[tail++] = new int[]{next1[0], next1[1], next2[0], next2[1], dist + 1};
                }
            }
        }

        System.out.println("STUCK");
        return -1; // impossible
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] raw_lens = sc.nextLine().trim().split("\\s+");
        int a = Integer.parseInt(raw_lens[0]);
        int b = Integer.parseInt(raw_lens[1]);
        char[][] maze = new char[a][b];
        int[] start1 = new int[] {0, 0};
        int[] start2 = new int[] {0, 0};

        for (int i = 0; i < a; i ++) {
            String in = sc.nextLine();
            for (int j = 0; j < b; j ++) {
                maze[i][j] = in.charAt(j);
                if (maze[i][j] == '1') {
                    start1 = new int[] {i, j};
                    maze[i][j] = '.';
                }
                else if (maze[i][j] == '2') {
                    start2 = new int[] {i, j};
                    maze[i][j] = '.';
                }
            }
        }
        sc.close();

        int result = thingsEscapeHouse(maze, start1, start2);
    }
}
