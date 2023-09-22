import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int count, max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			for(int j = 0; j<M; j++) {
				char c = str.charAt(j);
				if(c == 'W') {
					map[i][j] = 1;
				} else {
					map[i][j] = 0;
				}
			}
		}

		max = 0;
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == 0) {
					visited = new boolean[N][M];
					count = 0;
					bfs(new Point(i,j));
				}
			}
		}

		System.out.println(max-1);
	}

	public static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(p);
		visited[p.x][p.y] = true;
		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				Point now = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int nr = now.x + dr[i];
					int nc = now.y + dc[i];
					if (nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc] || map[nr][nc] == 1) {
						continue;
					}
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
			count++;
		}
		max = Math.max(max, count);
	}
}