import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] map, dist;
	static int N, M, startc, startr, endc, endr;
	static boolean[][][] visited;
	static Queue<Point> queue = new LinkedList<>();
	static int[] dc = { -1, 1, 0, 0 };
	static int[] dr = { 0, 0, -1, 1 };

	static class Point {
		int x;
		int y;
		int dist;
		boolean broken;

		public Point(int x, int y, int dist, boolean broken) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.broken = broken;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		// 배열의 가로 세로 길이
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		startc = Integer.parseInt(st.nextToken())-1;
		startr = Integer.parseInt(st.nextToken())-1;
		
		st = new StringTokenizer(br.readLine());
		endc = Integer.parseInt(st.nextToken())-1;
		endr = Integer.parseInt(st.nextToken())-1;

		map = new int[N][M];
		dist = new int[N][M];
		visited = new boolean[N][M][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(new Point(startc, startr, 0, false)));

	}

	public static int bfs(Point start) {
		queue.offer(start);
		visited[0][0][0] = true;
		visited[0][0][1] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll();
			
			if(now.x == endc && now.y == endr) {
				return now.dist;
			}

			for (int i = 0; i < 4; i++) {
				int nc = now.x + dc[i];
				int nr = now.y + dr[i];

				if (nc < 0 || nr < 0 || nc >= N || nr >= M) {
					continue;
				}
				if (now.broken) {
					if (map[nc][nr] == 1) {
						continue;
					} else if(map[nc][nr] == 0 && !visited[nc][nr][1]){
						queue.offer(new Point(nc, nr, now.dist + 1, true));
						visited[nc][nr][1] = true;
					}
				}
				else if (!now.broken) {
					if (map[nc][nr] == 1) {
						queue.offer(new Point(nc, nr, now.dist + 1, true));
						visited[nc][nr][1] = true;
					} else if(map[nc][nr] == 0 && !visited[nc][nr][0]) {
						queue.offer(new Point(nc, nr, now.dist + 1, false));
						visited[nc][nr][0] = true;
					}
				}
			}
		}
		return -1;
	}
}
