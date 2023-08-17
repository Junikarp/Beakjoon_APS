import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Node {
		int v;
		int cost;

		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static ArrayList<ArrayList<Node>> graph;
	static boolean[] visited;
	static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		// 그래프
		graph = new ArrayList<>();
		// 해당 지점까지의 최소 비용
		dist = new int[V + 1];
		// 방문 표시
		visited = new boolean[V + 1];

		// 그래프 추가 및 최소비용 초기화
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
			dist[i] = Integer.MAX_VALUE;
		}

		int K = Integer.parseInt(br.readLine());

		// 간선과 가중치 입력
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			// 시작 노드
			int u = Integer.parseInt(st.nextToken());
			// 도착 노드
			int v = Integer.parseInt(st.nextToken());
			// 가중치
			int w = Integer.parseInt(st.nextToken());

			graph.get(u).add(new Node(v, w));
		}

		bfs(K);

		for (int i = 1; i <= V; i++) {
			if (dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
	}

	public static void bfs(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		queue.offer(new Node(start, 0));
		dist[start] = 0;

		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (!visited[now.v]) {
				visited[now.v] = true;
			}

			for (Node next : graph.get(now.v)) {
				if (!visited[next.v] && dist[next.v] > now.cost + next.cost) {
					dist[next.v] = now.cost + next.cost;
					queue.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
	}

}

// 1. 가중치와 다음 노드를 담을 그래프 생성
// 2. 우선순위 큐를 이용하여 가중치가 낮은 순서대로 탐색
// 3. 만일 해당 노드에 이미 값이 담겨있다면 더 작은 값으로 갱신
