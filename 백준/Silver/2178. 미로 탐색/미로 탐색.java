import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] maze;
    static boolean[][] visited;
    static int[][] maze_dis;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 목표 x 좌표
        int N = Integer.parseInt(st.nextToken());
        // 목표 y 좌표
        int M = Integer.parseInt(st.nextToken());
        // 미로 경로
        maze = new int[N+2][M+2];
        // 방문 여부 확인용 배열
        visited = new boolean[N+2][M+2];
        // 거리 기록용 배열
        maze_dis = new int[N+2][M+2];
        // 미로 입력
        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            for(int j = 1; j<=M; j++) {
                maze[i][j] = str.charAt(j-1)-'0';
            }
        }
        // 깊이 우선 탐색 실행
        bfs(1,1);

        // 목표 지점까지의 길이 없다면 -1 출력
        if(maze_dis[N][M]==0) {
            System.out.print(-1);
        }
        // 그렇지 않다면 거리 출력
        else {
            System.out.print(maze_dis[N][M]+1);
        }
    }

    public static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        // 시작 지점 방문 표시
        visited[x][y] = true;
        // 시작 지점 큐에 입력
        queue.offer(new Point(x,y));

        // 큐가 빌 때까지 반복
        while(!queue.isEmpty()) {
            // 큐의 요소 꺼내기
            Point p = queue.poll();
            // 해당 지점에서 4방향 탐색
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 길이 있고, 방문하지 않았다면 실행
                if(!visited[nx][ny]&&maze[nx][ny]==1) {
                    // 방문 표시
                    visited[nx][ny] = true;
                    // 큐에 추가
                    queue.offer(new Point(nx,ny));
                    // 해당 지점까지의 거리 이전 지점에서 1 추가
                    maze_dis[nx][ny] = maze_dis[p.x][p.y]+1;
                }
            }
        }
    }
}

// 1. 미로 위치 배열에 입력 후 (1,1)부터 탐색 시작 (큐 활용)
// 2. 시작점부터 맞닿은 칸이 방문하지 않은 길이라면 탐색
// 3. 탐색한 지점은 방문 표시 및 배열에 거리 기록
// 4. 목표로 했던 지점의 좌표의 거리 출력