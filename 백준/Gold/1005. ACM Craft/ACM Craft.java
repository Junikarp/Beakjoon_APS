import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> graph;
	static int[] time, indegree, dp;
	static Queue<Integer> queue;
	static StringBuilder sb = new StringBuilder();
	static int sum, end;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			queue = new LinkedList<>();
			graph = new ArrayList<>();
			dp = new int[N + 1];
			time = new int[N + 1];
			indegree = new int[N + 1];
			sum = 0;

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				time[i] = Integer.parseInt(st.nextToken());
				dp[i] = time[i];
			}

			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());

				graph.get(u).add(v);

				indegree[v]++;
			}

			end = Integer.parseInt(br.readLine());

			for (int i = 1; i <= N; i++) {
				if (indegree[i] == 0) {
					queue.add(i);
				}
			}
			topo();
		}
		System.out.println(sb);
	}

	public static void topo() {
		while (!queue.isEmpty()) {
			int now = queue.poll();

			for (int next : graph.get(now)) {
				dp[next] = Math.max(dp[next], dp[now] + time[next]);
				indegree[next]--;

				if (indegree[next] == 0) {
					queue.add(next);
				}
			}
		}
		sb.append(dp[end]).append("\n");
	}
}
