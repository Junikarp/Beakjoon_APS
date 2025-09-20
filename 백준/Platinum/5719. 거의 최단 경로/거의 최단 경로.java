import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Node>> graph;
    static ArrayList<ArrayList<Integer>> sh;
    static int INF = 200000000;
    static int[] dist;
    static int[] point = new int[10000];
    static boolean[][] except;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            // 정점의 수
            int N = Integer.parseInt(st.nextToken());
            // 간선의 수
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            dist = new int[N];
            except = new boolean[N][N];

            st = new StringTokenizer(br.readLine());
            // 시작 정점
            int start = Integer.parseInt(st.nextToken());
            // 도착 정점
            int end = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            sh = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                graph.add(new ArrayList<>());
                sh.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                // 시작 정점
                int u = Integer.parseInt(st.nextToken());
                // 도착 정점
                int v = Integer.parseInt(st.nextToken());
                // 가중치
                int p = Integer.parseInt(st.nextToken());
                // 노드 추가
                graph.get(u).add(new Node(v, p));
            }

            // 다익스트라 진행
            djik(start);
            // 최단 경로인 친구들 제거
            remove(start, end);
            // 최단 경로 제거 후 다익스트라 재 실행
            djik(start);

            // 경로가 언다면 -1 출력 아니라면 최소값 출력 
            if (dist[end] == INF) {
                sb.append(-1).append("\n");
            } else {
                sb.append(dist[end]).append("\n");
            }
        }
        System.out.println(sb);
    }

    public static void djik(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        // 시작 정점 추가
        queue.offer(new Node(start, 0));
        // 배열 초기화
        Arrays.fill(dist, INF);
        dist[start] = 0;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            // 갱신이 필요 없다면 그냥 루프 진행
            if (now.cost > dist[now.v]) {
                continue;
            }
            // 노드 탐색
            for (Node next : graph.get(now.v)) {
            	if(!except[now.v][next.v]) {		
            		// 중복 최단 경로 처리
            		if(dist[next.v] == now.cost + next.cost) {
            			sh.get(next.v).add(now.v);
            		}
            		else if (dist[next.v] > now.cost + next.cost) {
            			dist[next.v] = now.cost + next.cost;
            			point[next.v] = now.v;
            			// 최단 경로라면 기존 경로 삭제 후 추가
            			sh.get(next.v).clear();
            			sh.get(next.v).add(now.v);
            			queue.offer(new Node(next.v, dist[next.v]));
            		}
            	}
            }
        }
    }
    
    // 최단 경로인 간선 제거
    static void remove(int s, int d) {
		if(s==d) return;
		for(int next : sh.get(d)) {
			if(!except[next][d]) {
				except[next][d] = true;
				remove(s, next);
			}
		}
	}

    static class Node {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}
