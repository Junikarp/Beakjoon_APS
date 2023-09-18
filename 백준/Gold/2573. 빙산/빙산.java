import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Ice> ice = new ArrayList<>();
    static boolean[][] visited;
    static int[][] map;
    static int[] dc = {-1, 1, 0, 0};
    static int[] dr = {0, 0, -1, 1};
    static int N,M,time,meltCnt;
    static class Ice {
        int x;
        int y;
        boolean melt;
        int blank;
        public Ice(int x, int y, boolean melt, int blank) {
            this.x = x;
            this.y = y;
            this.melt = melt;
            this.blank = blank;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    ice.add(new Ice(i, j, false, 0));
                }
            }
        }

        time = 0;
        meltCnt = 0;
        while(true) {
            if(meltCnt == ice.size()) {
                System.out.print(0);
                System.exit(0);
            }
            melt();
            time++;
            for (int i = 0; i < ice.size(); i++) {
                if (!ice.get(i).melt) {
                    bfs(new Point(ice.get(i).x, ice.get(i).y));
                    break;
                }
            }
        }
    }

    public static void melt() {
        int size = ice.size();

        for (int i = 0; i < size; i++) {
            if(ice.get(i).melt) {
                continue;
            }
            int cnt = 0;
            int c = ice.get(i).x;
            int r = ice.get(i).y;
            for (int j = 0; j < 4; j++) {
                int nc = c + dc[j];
                int nr = r + dr[j];

                if (nc < 0 || nr < 0 || nc >= N || nr >= M || map[nc][nr] != 0) {
                    continue;
                }
                cnt++;
            }
            ice.get(i).blank = cnt;
        }

        for (int i = 0; i < size; i++) {
            if (ice.get(i).melt) {
                continue;
            }

            int c = ice.get(i).x;
            int r = ice.get(i).y;

            map[c][r] -= ice.get(i).blank;

            if (map[c][r] <= 0) {
                map[c][r] = 0;
                ice.get(i).melt = true;
                meltCnt++;
            }
        }
    }

    public static void bfs(Point p) {
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[N][M];
        queue.add(p);
        visited[p.x][p.y] = true;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nc = now.x + dc[i];
                int nr = now.y + dr[i];

                if (nc < 0 || nr < 0 || nc >= N || nr >= M || map[nc][nr] == 0 || visited[nc][nr]) {
                    continue;
                }
                visited[nc][nr] = true;
                queue.add(new Point(nc, nr));
            }
        }

        for (int i = 0; i < ice.size(); i++) {
            if (ice.get(i).melt) {
                continue;
            }
            if(!visited[ice.get(i).x][ice.get(i).y]) {
                System.out.print(time);
                System.exit(0);
            }
        }
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            System.out.println();
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j]+" ");
            }
        }
        System.out.println();
    }
}