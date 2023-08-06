import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Queue<Point> queue = new LinkedList<Point>();
    static boolean[][] visited;
    static int[][] maze;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 미로 표현 배열
        maze = new int[N][M];
        // 방문 여부 확인
        visited = new boolean[N][M];

        for (int i = 0; i < maze.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                // 미로가 막혀있는 부분은 방문표시
                if (maze[i][j] == 0) {
                    visited[i][j] = true;
                }
                // 출발점은 큐에 삽입후 방문표시
                // 거리 측정을 취해 0 대입
                if (maze[i][j] == 2) {
                    maze[i][j] = 0;
                    queue.offer(new Point(i, j));
                    visited[i][j] = true;
                }
            }
        }

        // 탐색
        bfs();

        // 목표지점에서 각 지점까지의 거리 입력
        // 도달하지 못한 지점은 -1로 처리
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (visited[i][j]) {
                    sb.append(maze[i][j]).append(" ");
                } else {
                    sb.append(-1).append(" ");
                }

            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            // 좌표 하나를 꺼내서 사방향 탐색
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                // 미로의 범위를 벗어나지 않고 방문하지 않았다면 표시 후 큐에 삽입
                if (nx >= 0 && ny >= 0 && nx < maze.length && ny < maze[0].length && !visited[nx][ny]) {
                    maze[nx][ny] = maze[p.x][p.y] + 1;
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }
}


// 1. 목표지점에서 출발하여 길이 있는 곳은 이전 지점 +1
// 2. 목표지점에서 도달할 수 없다면 bfs 이후 방문표시 x
// 3. 따라서 방문표시 안된 1인 지점은 -1 출력
