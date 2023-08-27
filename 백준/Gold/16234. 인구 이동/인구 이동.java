import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static boolean flag;
    static int[] dc = {-1, 1, 0, 0};
    static int[] dr = {0, 0, -1, 1};
    static int N, R, L, day;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        day = 0;

        while (true) {
            visited = new boolean[N][N];
            flag = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        bfs(new Point(i, j));
                    }
                }
            }
            if (!flag) {
                break;
            }

            day++;
        }

        System.out.println(day);
    }

    public static void bfs(Point p) {
        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> point = new ArrayList<>();
        queue.offer(p);
        point.add(p);
        int cnt = 1;
        int sum = map[p.x][p.y];

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nc = now.x + dc[i];
                int nr = now.y + dr[i];

                if (nc < 0 || nr < 0 || nc >= N || nr >= N || visited[nc][nr]) {
                    continue;
                }
                int gap = Math.abs(map[now.x][now.y] - map[nc][nr]);

                if (gap < L || gap > R) {
                    continue;
                }
                point.add(new Point(nc,nr));
                queue.offer(new Point(nc,nr));
                sum += map[nc][nr];
                cnt++;
                visited[nc][nr] = true;
            }
        }
        if(cnt >= 2) {
            flag = true;
            int avg = sum/cnt;
            for (Point value : point) {
                map[value.x][value.y] = avg;
            }
        }
    }
}


