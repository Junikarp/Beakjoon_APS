import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visited;
    static int cnt;
    static ArrayList<ArrayList<Integer>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 수
        int N = Integer.parseInt(br.readLine());
        // 간선의 수
        int M = Integer.parseInt(br.readLine());
        // 그래프 생성
        graph = new ArrayList<>();
        // 방문 지점 기록용 배열
        visited = new boolean[N+1];

        for(int i=0; i<=N; ++i){
           graph.add(new ArrayList<>());
        }

        // 정점에 연결된 정점들 추가(간선 추가)
        for(int i = 1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        // 1번에서 출발
        int start = 1;
        // 감염된 컴퓨터 수 기록
        cnt = 0;
        // 깊이 우선 탐색
        dfs(start);

        System.out.print(cnt);
    }

    public static void dfs(int start) {
        // 방문 정점 표시
        visited[start] = true;
        
        // 해당 정점과 연결된 정점 중 방문하지 않은 정점 탐색
        for(int i=0; i<graph.get(start).size(); i++) {
            if(!visited[graph.get(start).get(i)]) {
                cnt++;
                dfs(graph.get(start).get(i));
            }
        }
    }
}

// 구현 목록
// 1. ArrayList 로 그래프 생성
// 2. N 만큼 정점, M 만큼 간선 생성
// 3. visited 배열로 방문 여부 확인
// 4. 정점 방문 시마다 변수 cnt +1
