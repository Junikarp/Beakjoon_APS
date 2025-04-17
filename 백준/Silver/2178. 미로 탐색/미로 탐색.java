import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point {
        int r;
        int c;
        public Point(int r,int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N+1][M+1];
        boolean[][] visited = new boolean[N+1][M+1];

        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            for(int j = 1; j<=M; j++){
                int wall = str.charAt(j-1)-'0';
                if(wall == 0) {
                    visited[i][j] = true;
                }
            }
        }

        bfs(N,M,visited,dist);

        System.out.print(dist[N][M]);
    }

    static void bfs(int N, int M, boolean[][] visited, int[][] dist) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(1,1));
        visited[1][1] = true;
        dist[1][1] = 1;

        while(!queue.isEmpty()) {
            Point now = queue.poll();

            for(int i = 0; i<4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if(nr <= 0 || nc <=0 || nr > N || nc > M || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                dist[nr][nc] = dist[now.r][now.c] + 1;
                queue.offer(new Point(nr, nc));
            }
        }
    }
}