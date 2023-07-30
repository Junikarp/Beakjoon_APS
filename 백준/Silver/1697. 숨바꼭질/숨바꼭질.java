import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] location;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈의 처음 위치
        int N = Integer.parseInt(st.nextToken());
        // 동생의 위치
        int K = Integer.parseInt(st.nextToken());

        // 지점까지 도달한 시간 배열
        location = new int[100001];
        // 방문 여부 확인 배열
        visited = new boolean[100001];

        // 탐색
        bfs(N);

        System.out.print(location[K]);

    }

    public static void bfs(int start) {
        // 수빈의 처음 위치 방문 표시
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int end = 100001;

        while(!queue.isEmpty()) {
            int v = queue.poll();
            int nx1 = v+1;
            int nx2 = v-1;
            int nx3 = v*2;
            // 처음 위치 + 1 지점 시간 기록
            if(nx1>=0 && nx1<end && !visited[nx1]) {
                visited[nx1] = true;
                location[nx1] = location[v]+1;
                queue.offer(nx1);
            }
            // 처음 위치 - 1 지점 시간 기록
            if(nx2>=0 && nx2<end && !visited[nx2]) {
                visited[nx2] = true;
                location[nx2] = location[v]+1;
                queue.offer(nx2);
            }
            // 처음 위치 * 2 지점 시간 기록
            if(nx3>=0 && nx3<end && !visited[nx3]) {
                visited[nx3] = true;
                location[nx3] = location[v]+1;
                queue.offer(nx3);
            }
        }
    }
}
// 1. 시작 지점에서 출발하여 탐색(너비 우선 탐색)
// 2. +1, -1, x2 지점이 방문하지 않은 지점이라면 이전 지점의 시간 + 1
// 3. 동생이 있는 위치 K 의 시간 출력