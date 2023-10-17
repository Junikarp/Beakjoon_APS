import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {
 
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited; 
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken()); 
        map = new char[N][M]; 
        visited = new boolean[N][M][N][M]; 
        
        int rdr, rdc, ldr, ldc;
        rdr = rdc = ldr = ldc = 0;
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 'R') {
                    rdr = i; rdc = j;
                }
                else if(map[i][j] == 'B') {
                    ldr = i; ldc = j;
                }
            }
        }
        
        System.out.println(process(rdr, rdc, ldr, ldc));
    }
 
    private static int process(int rdr, int rdc, int ldr, int ldc) {
        
        Queue<Turn> q = new LinkedList<>();
        int time = 1;
        
        q.add(new Turn(rdr, rdc, ldr, ldc));
        visited[rdr][rdc][ldr][ldc] = true;
        
        ball red = null, blue = null;
        while(!q.isEmpty()) {
            
            int size = q.size();
            while(size-- > 0) {
                Turn now = q.poll();
                
                for (int d = 0; d < 4; d++) {
                    red = move(now.rdr, now.rdc, 0, d);
                    blue = move(now.ldr, now.ldc, 0, d);
                    
                    if(map[blue.r][blue.c] == 'O') {
                    	continue;
                    }
                    if(map[red.r][red.c] == 'O') {
                    	return time;
                    }
                    
                    if (red.r == blue.r && red.c == blue.c) {
                        if(red.dist> blue.dist) {
                            red.r -= dr[d];    
                            red.c -= dc[d];
                        } else {
                            blue.r -= dr[d];
                            blue.c -= dc[d];
                        }
                    }
 
                    if(visited[red.r][red.c][blue.r][blue.c]) continue;
                    
                    visited[red.r][red.c][blue.r][blue.c] = true;
                    
                    q.add(new Turn(red.r, red.c, blue.r, blue.c));
                }
            }
    
            if(++time > 10) {
            	return -1;
            }
        }
        return -1;
    }
 
    private static ball move(int r, int c, int dist, int d) {
 
        int nr = r, nc = c;
        while(map[nr + dr[d]][nc + dc[d]] != '#' && map[nr][nc] != 'O') {
            nr += dr[d];
            nc += dc[d];
            dist++;
        }
        
        return new ball(nr, nc, dist);
    }
 
    static class ball {
        int r, c, dist;
 
        public ball(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
        
    }
    
    static class Turn {
        int rdr, rdc, ldr, ldc;
 
        public Turn(int rdr, int rdc, int ldr, int ldc) {
            this.rdr = rdr;
            this.rdc = rdc;
            this.ldr = ldr;
            this.ldc = ldc;
        }
    }
}