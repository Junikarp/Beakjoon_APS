import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Node>> graph;
	static int[] indegree1, indegree2, dp, need;
	static Queue<Node> queue;
	static StringBuilder sb = new StringBuilder();
	static int sum, end;
	
	static class Node {
		int v;
		int w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}	
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		queue = new LinkedList<>();
		graph = new ArrayList<>();
		dp = new int[N + 1];    // 결과
		need = new int[N + 1];  // 필요 부품수
		indegree1 = new int[N + 1]; // 기본 부품 화인
		indegree2 = new int[N + 1]; // 진입차수 확인
		sum = 0;

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v, w));
			
			indegree1[u]++; 
			indegree2[v]++;
			
		}
		
		queue.add(new Node(N, 1));
		
		dp[N] = 1;
		
		topo();
		
		for(int i=1; i<N; i++) {
			if(indegree1[i] == 0) {
				sb.append(i).append(" ").append(dp[i]).append("\n");
			}
		}
		
		System.out.println(sb);

	}

	public static void topo() {
		while (!queue.isEmpty()) {
			Node now = queue.poll();
			for (Node next : graph.get(now.v)) {
				dp[next.v] += dp[now.v] * next.w;
				indegree2[next.v]--; 

				if (indegree2[next.v] == 0) {
					queue.add(next);
				}
			}
		}
	}
}
