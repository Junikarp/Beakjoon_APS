import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map, dist;
	static boolean[][] visited;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,1,-1};
	static int N;
	static final int INF = 200000000;
	static class Point {
		int x;
		int y;
		int w;
		public Point(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dist = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j)-'0'== 0 ? 1 : 0;
				dist[i][j] = INF;
			}
		}	
		
		bfs();
		
		System.out.println(dist[N-1][N-1]);
	}
	
	public static void bfs() {
		PriorityQueue<Point> queue = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.w - o2.w;
			}
		});
		
		queue.add(new Point(0,0,0));
		dist[0][0] = 0;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			visited[p.x][p.y] = true;
			
			for(int i = 0; i<4; i++) {
				int nr = p.x + dr[i];
				int nc = p.y + dc[i];
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) {
					continue;
				}
				
				if(dist[nr][nc] > dist[p.x][p.y] + map[nr][nc]) {
					dist[nr][nc] = dist[p.x][p.y] + map[nr][nc];
					queue.add(new Point(nr,nc,dist[p.x][p.y]));
				}
			}
		}
	}	
	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println();
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j]);
			}
		}
	}
}