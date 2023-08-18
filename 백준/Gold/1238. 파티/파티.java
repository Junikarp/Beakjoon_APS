import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int v;
		int cost;
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
	static ArrayList<ArrayList<Node>> graph;
	static int[] dist;
	static int INF = 200000000;
	static int[] res;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		// 마을의 수
		int N = Integer.parseInt(st.nextToken());
		// 마을 사이의 도로 수
		int M = Integer.parseInt(st.nextToken());
		// 학생들이 길을 지나는데 소비되는 시간
		int X = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		dist = new int[N+1];
		res = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v,w));
		}
		
		for(int i=1; i<=N; i++) {
			bfs(i);
			if(i == X) {
				continue;
			} else {
				res[i] += dist[X];				
			}
		}
		
		bfs(X);
		
		for(int i=1; i<=N; i++) {
			if(i == X) {
				continue;
			} else {
				res[i] += dist[i];				
			}
		}
		
		Arrays.sort(res);
		
		System.out.println(res[N]);
	}
	
	public static void bfs(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2) -> o1.cost-o2.cost);
		queue.offer(new Node(start, 0));
		Arrays.fill(dist, INF);
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			if(dist[now.v] < now.cost) {
				continue;
			}
			for(Node next : graph.get(now.v)) {
				if(dist[next.v] > now.cost + next.cost) {
					dist[next.v] = now.cost + next.cost;
					queue.offer(new Node(next.v, dist[next.v]));
				}
			}
		}
	}

}

// 1. 각각의 마을에서 N으로 향하는 시간 비용을 계산하여 배열에 저장
// 2. N에서 각각의 마을로 향하는 시간 비용을 계산하여 합산
