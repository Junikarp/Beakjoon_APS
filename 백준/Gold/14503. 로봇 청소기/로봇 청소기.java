import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] dc = {-1, 0 ,1, 0};
    static int[] dr = {0, 1, 0, -1};
    static int c,r,d, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        // 처음 좌표
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        // 방향 -> 북 동 남 서
        d = Integer.parseInt(st.nextToken());

        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean();
    }

    public static void clean() {
        cnt = 0;
        while(true) {
            if(map[c][r] == 0) {
                map[c][r] = 2;
                cnt++;
            }
            int not = 0;
            for(int i = 0; i < 4; i++) {
                int nc = c + dc[i];
                int nr = r + dr[i];
                if(nc >= map.length || nr >= map[0].length || nc <0|| nr < 0 || map[nc][nr] != 0) {
                    continue;
                }
                not++;
            }
            if(not >= 1) {
                while(true){
                    d--;
                    if(d == -1) {
                        d = 3;
                    }
                    int nc = c + dc[d];
                    int nr = r + dr[d];

                    if(map[nc][nr] == 0) {
                        map[nc][nr] = 2;
                        c = nc;
                        r = nr;
                        cnt++;
                        break;
                    }
                }
            } else {
                c = c + (-dc[d]);
                r = r + (-dr[d]);
                if(map[c][r] == 1) {
                    System.out.print(cnt);
                    System.exit(0);
                }
            }
        }
    }
}