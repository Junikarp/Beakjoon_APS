import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dr = { -1, 0, 1 };
    static int[] dc = { 1, 1, 1 };

    static int N, M, cnt;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
      
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        cnt = 0;
        for (int i = 0; i < N; i++) {
            if(dfs(i, 0)) {
            	cnt++;
            }
        }
        
        System.out.println(cnt);
    }

    public static boolean dfs(int r, int c) {
    	map[r][c] = '-';
    	
        if (c == M - 1) {
            return true;
        }
        for (int d = 0; d < 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == 'x' || map[nr][nc] == '-') {
                continue;
            }
            if(dfs(nr, nc)) {
            	return true;
            }
        }
        return false;
    }
    

    public static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                
            }
            System.out.println();
        }
    }
}