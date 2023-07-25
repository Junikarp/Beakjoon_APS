import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static int seq[];
    static int cnt;
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

        // 방문 순서 저장용 배열
        seq = new int[N+1];

        // 그래프 생성
        graph = new ArrayList<>();

        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        // 그래프 정점 사이 간선 추가
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // 그래프에 연결된 정점 내림차순으로 정렬
        for(int i=1; i<graph.size(); i++) {
            graph.get(i).sort(Collections.reverseOrder());
        }

        // 방문 순서 카운팅용 변수
        cnt = 1;

        // 탐색 실행
        bfs(start);

        // 정점 방문 순서대로 출력
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<seq.length; i++) {
            sb.append(seq[i]).append("\n");
        }

        System.out.println(sb);
    }
    public static void bfs(int start) {
        // bfs 를 위한 큐 생성
        queue = new LinkedList<>();
        seq[start] = cnt;
        queue.offer(start);

        // 큐가 전부 빌때까지 정점의 원소 poll
        // 해당 정점에 연결된 원소 중 순서배열에 기록되지 않은 원소 offer 후 순서기록
        while(!queue.isEmpty()) {
            int Node = queue.poll();
            for(int i=0; i<graph.get(Node).size(); i++) {
                int NewNode = graph.get(Node).get(i);
                if(seq[NewNode] == 0) {
                    cnt++;
                    seq[NewNode] = cnt;
                    queue.offer(NewNode);
                }
            }
        }
    }



    // 구현할 목록
    // 1. 그래프 구현 -> ArrayList<ArrayList<Integer>> 로 구현 무방향 그래프
    //    그래프는 내림차순으로 정렬할 것  (완료)
    // 2. 순서 저장용 배열 -> int seq[N+1] // 정점과 일치하는 인덱스에 방문 순서 저장 (완료)
    // 3, 방문한 정점 카운팅용 변수 int cnt // (완료)
    // 4. 방문 노드 저장용 Queue<Integer>
    // 5. bfs 메소드 매개변수 시작정점-> 방문 정점 offer 후에 poll 하면서 연결된 모든 정점 offer
    // 6. 현재 정점 표시 후, Queue 에서 poll -> 해당 정점의 원소 전부 offer
    // 7. 새로운 정점 NewNode 의 순서가
    // 기록되어 있다면 다음 정점으로 // 기록되어 있지 않다면 기록후 queue 에 삽입
    // 8. seq 배열의 원소 차례로 출력

}
