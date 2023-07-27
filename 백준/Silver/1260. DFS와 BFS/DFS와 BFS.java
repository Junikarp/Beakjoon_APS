import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int cnt;
	static int[] seq; 
	static StringBuilder sb;
	static Queue<Integer> queue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		// 정점의 수
		int N = Integer.parseInt(st.nextToken());
		// 간선의 수
		int M = Integer.parseInt(st.nextToken());
		// 시작 정점
		int start = Integer.parseInt(st.nextToken());
		// 그래프 생성
		graph = new ArrayList<>();
		// 순서 저장용 배열
		seq = new int[N+10];
		// 방문 여부 확인
		visited = new boolean[N+1];
		// 너비 우선 탐색용 큐 생성
		queue = new LinkedList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 정점과 정점 사이 연결
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}

		// 정점 방문 기준대로 정렬(오름차순)
		for (int i = 0; i < graph.size(); i++) {
			Collections.sort(graph.get(i));
		}
		
		// 방문 순서 카운팅
		cnt = 1;
		 
		// 깊이 우선 탐색 실행
		dfs(start);
		
		sb = new StringBuilder();
		
		// 깊이 우선 탐색 순서 기록
		for(int i = 1; i<=cnt; i++) {
			if(seq[i]!=0)
			sb.append(seq[i]).append(" ");
		}
		sb.append("\n");
		
		// 배열 & 카운팅 변수 초기화
		Arrays.fill(seq, 0);
		Arrays.fill(visited, false);
		
		cnt = 1;
		
		// 너비 우선 탐색 실행
		bfs(start);
		
		for(int i = 1; i<=cnt; i++) {
			if(seq[i]!=0)
			sb.append(seq[i]).append(" ");
		}

		System.out.println(sb);
	}

	public static void dfs(int start) {
		
		// 해당 정점 방문 표시
		visited[start] = true;
		// 순서 = index, 값 = 해당 순서에 방문한 정점
		seq[cnt++] = start;
		
		for (int i = 0; i < graph.get(start).size(); i++) {
			// 방문 정점과 연결된 정점 순회 / 방문하지 않은 정점 있다면 탐색 시작
			if(!visited[graph.get(start).get(i)]) {
				visited[graph.get(start).get(i)] = true;
				dfs(graph.get(start).get(i));
			}
		}

	}
	
	public static void bfs(int start) {
		
		// 순서 = index, 값 = 해당 순서에 방문한 정점
		seq[cnt++] = start;
		// 방문 정점 큐에 추가
		queue.offer(start);
		// 방문 여부 표시
		visited[start] = true;
		
		// 큐에 있는 원소가 모두 꺼내질 때 까지
		while(!queue.isEmpty()) {
			// 방문 원소 큐에서 꺼내기
			int Node = queue.poll();
			// 방문 정점과 연결된 그래프 순회
			for(int i=0; i<graph.get(Node).size(); i++) {
				int newNode = graph.get(Node).get(i);
				// 연결된 정점 중 방문하지 않은 정점 처리
				// 방문 표시 및 순서 배열에 추가 / 큐에 추가
				if(!visited[newNode]) {
					visited[newNode] = true;
					seq[cnt++] = newNode;
					queue.offer(newNode);
				}
			
			}
			
		}
		
	}

}