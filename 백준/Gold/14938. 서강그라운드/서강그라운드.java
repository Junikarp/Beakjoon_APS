import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Node>> graph;
	static boolean[] visited;
	static int[] dist, item;
	static int N,M, max;
	static final int INF = 200000000;
	static class Node {
		int v;
		int w;
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		item = new int[N+1];
		
		for(int i = 0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i<T; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v,w));
			graph.get(v).add(new Node(u,w));
		}
		
		max = 0; 
		
		for(int i = 1; i<=N; i++) {
			bfs(i);
		}
		
		Arrays.sort(dist);
		
		System.out.println(max);
	}
	
	public static void bfs(int start) {
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.w - o2.w;
			}
		});
		visited = new boolean[N+1];
		dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		queue.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			visited[now.v] = true;
			for(Node next : graph.get(now.v)) {
				if(visited[next.v]) {
					continue;
				}
				
				if(dist[next.v] >= dist[now.v] + next.w) {
					dist[next.v] = dist[now.v] + next.w;
					queue.add(new Node(next.v, dist[next.v]));
				}				
			}
		}	
		int sum = 0;
		
		for(int i = 1; i<=N; i++) {
			if(dist[i] <= M) {
				sum += item[i];
			}
		}
		
		max = Math.max(max, sum);
	}	
	public static void print() {
		for (int i = 0; i < N; i++) {
			System.out.println();
			for (int j = 0; j < N; j++) {
				System.out.println();
			}
		}
	}
}