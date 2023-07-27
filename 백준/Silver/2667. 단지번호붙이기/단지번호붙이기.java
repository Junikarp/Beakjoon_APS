import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] apart;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 단지 수 카운팅
    static int apartComp = 0;
    // 단지 내 아파트 수 카운팅
    static int[] apartCompCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        // 아파트 위치 배열
        apart = new int[T + 2][T + 2];
        // 방문 여부 확인용 배열
        visited = new boolean[T + 2][T + 2];
        // 단지 내 아파트 수 기록용 배열
        apartCompCnt = new int[1000];

        for (int i = 1; i <= T; i++) {
            String str = br.readLine();
            for (int j = 1; j <= T; j++) {
                apart[i][j] = Character.getNumericValue(str.charAt(j - 1));
            }
        }
        
        // 방문하지 않은 단지 내 아파트 발견 시
        // 1. 단지 수 카운팅
        // 2. 깊이 우선 탐색으로 단지 내 아파트 탐색
        for (int i = 1; i <= T; i++) {
            for (int j = 1; j <= T; j++) {
                if (!visited[i][j] && apart[i][j] == 1) {
                    apartComp++;
                    dfs(i, j);
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        sb.append(apartComp).append("\n");

        Arrays.sort(apartCompCnt);

        for (int i = 0; i < 1000; i++) {
            if (apartCompCnt[i] != 0) {
                sb.append(apartCompCnt[i]).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static void dfs(int apart_x, int apart_y) {
        // 방문 여부 확인
        visited[apart_x][apart_y] = true;
        // 단지 내 아파트 수 카운팅
        apartCompCnt[apartComp]++;

        // 상하좌우 탐색 후 조건에 맞다면 탐색
        for (int i = 0; i < 4; i++) {
            int nx = apart_x + dx[i];
            int ny = apart_y + dy[i];
            if (apart[nx][ny] == 1 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }
}

// 구현할 목록
// 1. 단지 위치 기록용 배열(int), 단지 방문 여부 기록용 배열(boolean)
// 2. x 좌표, y 좌표 이동용 배열 dx, dy
// 3. dfs 로 방문하지 않은 단지라면 상하좌우 탐색 (+dx, +dy)
// 4. 방문한 단지 카운팅, 단지 내 집의 수 카운팅