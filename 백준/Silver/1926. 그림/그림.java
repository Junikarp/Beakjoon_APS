import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static int N, M, max, cnt;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new boolean[N][M];
        max = 0;
        cnt = 0;
        
        for (int i = 0; i < map.length; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					bfs(new Point(i,j));
				}
			}
		}
        
        System.out.println(cnt);
        System.out.println(max);
    }
    
    public static void bfs(Point p) {
    	Queue<Point> queue = new LinkedList<>();
    	queue.add(p);
    	visited[p.x][p.y] = true;
    	
    	int size = 1;
    	
    	while(!queue.isEmpty()) {
    		Point now = queue.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int nx = now.x + dx[i];
    			int ny = now.y + dy[i];
    		
    			if(nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny] || map[nx][ny] == 0) {
    				continue;
    			}
    			
    			visited[nx][ny] = true;
    			queue.add(new Point(nx, ny));
    			size++;
    		}
    	}
    	cnt++;
    	max = Math.max(size, max);
    }
}