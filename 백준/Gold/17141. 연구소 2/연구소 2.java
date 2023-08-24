import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] map;
	static int[] dc = { -1, 1, 0, 0 };
	static int[] dr = { 0, 0, -1, 1 };
	static Queue<Point> queue = new LinkedList<>();
	static ArrayList<Point> arr;
	static Point[] route;
	static int N, M, max, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		max = 0;
		min = Integer.MAX_VALUE;
		route = new Point[M];

		arr = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					arr.add(new Point(i, j));
				}
			}
		}

		dfs(0, 0);

		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.print(min-1);
		}
	}

	public static void bfs() {
		int time = 0;
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			visited[route[i].x][route[i].y] = true;
			queue.add(route[i]);
		}
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int j = 0; j < size; j++) {

				Point p = queue.poll();
				for (int i = 0; i < 4; i++) {
					int nc = p.x + dc[i];
					int nr = p.y + dr[i];

					if (nc < 0 || nr < 0 || nc >= map.length || nr >= map.length || visited[nc][nr] || map[nc][nr] == 1) {
						continue;
					}
					visited[nc][nr] = true;
					queue.offer(new Point(nc, nr));
				}
			}
			time++;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && map[i][j] != 1) {
					return;
				}
			}
		}

		min = Math.min(time, min);
	}

	public static void dfs(int d, int start) {
		if (d == M) {
			bfs();
			return;
		}

		for (int i = start; i < arr.size(); i++) {
			route[d] = arr.get(i);
			dfs(d + 1, i + 1);

		}
	}
}
