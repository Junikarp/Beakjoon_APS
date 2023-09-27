import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static int parent[];
	static int[][] map;
	static ArrayList<Edge> edgelist = new ArrayList<>();
	static int N, M;

	static class Edge {
		int from;
		int to;
		int w;

		public Edge(int from, int to, int w) {
			super();
			this.from = from;
			this.to = to;
			this.w = w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		parent = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (i != j) {
					edgelist.add(new Edge(i + 1, j + 1, map[i][j]));
				}
			}
		}

		int cnt = 0;
		long sum = 0;

		Collections.sort(edgelist, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.w - o2.w;
			}
		});

		for (Edge edge : edgelist) {
			if (union(edge.from, edge.to)) {
				sum += edge.w;
				if (++cnt == N - 1) {
					break;
				}
			}
		}

		System.out.println(sum);
	}

	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x == y) {
			return false;
		}

		if (x <= y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}

		return true;
	}
}