import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0,1,1,-1,-1};
	static int[] dc = {0,0,1,-1,-1,1,-1,1};
	static int N,M,cnt;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N][M];
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {					
					flag = true;
					bfs(new Point(i,j));
					if(flag) {
						cnt++;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	public static void bfs(Point start) {
		Queue<Point> queue = new LinkedList<>();
		visited[start.x][start.y] = true;
		queue.add(new Point(start.x, start.y));
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			for(int i = 0; i<8; i++) {
				int nr = now.x + dr[i];
				int nc = now.y + dc[i];
				
				
				if(nc < 0 || nr < 0 || nc >= M || nr >= N || map[nr][nc] == 0) {
					continue;
				}
				
				if(map[nr][nc] > map[now.x][now.y]) {
					flag = false;
				}
				
				if(map[now.x][now.y] != map[nr][nc] || visited[nr][nc]) {
					continue;
				}
				
				visited[nr][nc] = true;
				queue.add(new Point(nr,nc));
			}		
		}
	}	
}