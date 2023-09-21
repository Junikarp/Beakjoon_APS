import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {
 
    static int N, sum;
    static int[][] map,dist;
    static boolean[][] visited;
    static int[] dc = {-1,1,0,0};
    static int[] dr = {0,0,-1,1};
 
    static class Point {
        int c;
        int r;
        int w;
        public Point(int c, int r, int w) {
            this.c = c;
            this.r = r;
            this.w = w;
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = 0;
         
        while(true) {
        	T++;
            N = Integer.parseInt(br.readLine());
             
            if(N==0) {
            	break;
            }
            map = new int[N][N];
            dist = new int[N][N];
            visited = new boolean[N][N];
            sum = 0;
            for(int i = 0; i<N; i++) {
            	st = new StringTokenizer(br.readLine());
                for(int j = 0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = 200000000;
                }
            }
            solve();
            sb.append("Problem "+ T+": ").append(dist[N-1][N-1]).append("\n");
        }
        System.out.println(sb); 
    }
     
    public static void solve() {
        PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.w-o2.w;
            }   
        });
         
        queue.add(new Point(0,0,0));
        visited[0][0] = true;
        dist[0][0] = map[0][0];
         
        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for(int i = 0; i<4; i++) {
                int nc = now.c + dc[i]; 
                int nr = now.r + dr[i];
                 
                if(nc < 0 || nr < 0 || nc >= N || nr >= N || visited[nc][nr]) {
                    continue;
                }   
                 
                if(dist[nc][nr] > dist[now.c][now.r] + map[nc][nr]) {
                    dist[nc][nr] = dist[now.c][now.r] + map[nc][nr];
                    visited[nc][nr] = true; 
                    queue.add(new Point(nc, nr, dist[nc][nr]));
                }
            }
        }               
    }
     
    public static void print() {
        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map.length; j++) {
                System.out.print(dist[i][j]+" ");
            }
             
        }
    }
}