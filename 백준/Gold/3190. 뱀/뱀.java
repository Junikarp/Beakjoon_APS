import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int tail_d, d, time, real_time;
    // 우, 상, 좌, 하
    static int[] dc = {0, -1, 0, 1};
    static int[] dr = {1, 0, -1, 0};
    static Point head, tail;
    static Queue<Point> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];

        // 사과 위치 표시
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            map[u][v] = 1;
        }

        head = new Point(1, 1);
        tail = new Point(1, 1);

        queue.offer(head);

        map[1][1] = 2;
        d = 0;
        tail_d = 0;
        time = 0;

        // 이동
        int L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int dist = Integer.parseInt(st.nextToken());
            String str = st.nextToken();

            move(dist, str);
        }
        move(N+time, "D");
    }

    public static void move(int dist, String str) {
        int o = dist - time;
        for (int i = 0; i < o; i++) {
            int nc = head.x + dc[d];
            int nr = head.y + dr[d];
            if (nc >= map.length || nr >= map.length || nc <= 0 || nr <= 0 || map[nc][nr] == 2) {
                System.out.print(time + 1);
                System.exit(0);
            }
            if (map[nc][nr] != 1) {
                tail = queue.poll();
                map[tail.x][tail.y] = 0;
            }
            map[nc][nr] = 2;
            head = new Point(nc, nr);
            queue.offer(head);
            time++;
//            print();
//            System.out.println(head + " " + tail);
        }

        if (str.equals("D")) {
            if (d - 1 == -1) {
                d = 3;
            } else {
                d -= 1;
            }
        } else {
            if (d + 1 == 4) {
                d = 0;
            } else {
                d += 1;
            }
        }
    }

    public static void print() {
        for (int i = 1; i < map.length; i++) {
            System.out.println();
            for (int j = 1; j < map.length; j++) {
                System.out.print(map[i][j]);
            }
        }
    }
}