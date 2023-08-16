import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited = new boolean[500001][2];
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈의 처음 위치
        int N = Integer.parseInt(st.nextToken());
        // 동생 처음 위치
        int K = Integer.parseInt(st.nextToken());

        // 처음 위치가 같다면 0 출력
        if (N == K) {
            System.out.print(0);
            System.exit(0);
        } else {
            // 수빈의 첫 위치 삽입
            queue.offer(N);
            visited[N][0] = true;
            // 탐색
            bfs(K);
        }
    }

    public static void bfs(int k) {
        // 어느 지점까지의 도달 시간
        int cnt = 0;
        int size = 0;

        while (!queue.isEmpty()) {
            size = queue.size();
            // 시간 1초 추가
            cnt++;
            // 동시간대 계산을 위해 큐 사이즈만큼씩 계산
            for (int i = 0; i < size; i++) {
                // 큐에서 빼낸 시간이 현재의 위치
                int start = queue.poll();
                // 다음 지점 도달 시간이 짝수인 경우
                if (cnt % 2 == 0) {
                    if (start - 1 >= 0 && !visited[start - 1][0]) {
                        // -1 지점으로 이동
                        visited[start - 1][0] = true;
                        queue.offer(start - 1);
                    }
                    if (start * 2 <= 500000 && !visited[start * 2][0]) {
                        // *2 지점으로 이동
                        visited[start * 2][0] = true;
                        queue.offer(start * 2);
                    }
                    if (start + 1 <= 500000 && !visited[start + 1][0]) {
                        // +1 지점으로 이동
                        visited[start + 1][0] = true;
                        queue.offer(start + 1);
                    }
                }
                // 다음 지점 도달 시간이 홀수인 경우
                else if (cnt % 2 == 1) {
                    if (start - 1 >= 0 && !visited[start - 1][1]) {
                        // -1 지점으로 이동
                        visited[start - 1][1] = true;
                        queue.offer(start - 1);
                    }
                    if (start * 2 <= 500000 && !visited[start * 2][1]) {
                        // *2 지점으로 이동
                        visited[start * 2][1] = true;
                        queue.offer(start * 2);
                    }
                    if (start + 1 <= 500000 && !visited[start + 1][1]) {
                        // +1 지점으로 이동
                        visited[start + 1][1] = true;
                        queue.offer(start + 1);
                    }

                }
            }
            // 이동 거리 추가
            k += cnt;
            // 동생이 범위 벗어나면 -1
            if (k > 500000) {
                System.out.print(-1);
                System.exit(0);
            }
            // 짝수 시간대 이미 방문한 장소라면 현재 시간 출력
            if (cnt % 2 == 0 && visited[k][0]) {
                System.out.print(cnt);
                System.exit(0);
            }
            // 홀수 시간대 이미 방문한 장소라면 현재 시간 출력
            else if (cnt % 2 == 1 && visited[k][1]) {
                System.out.print(cnt);
                System.exit(0);
            }
        }
    }
}

// 1. bfs 탐색을 미리 진행하여 각 좌표에 대해 최소치를 구함
// 2. +1, -1 을 반복하면 2초 뒤 제자리에 있을 수 있음
// 3. 따라서 각 위치에 대해 짝수, 홀수 시의 최소 도달 시간이 필요
// 4. +1, -1, *2 를 진행하면서 각 좌표에 대한 짝수, 홀수 값이 비어있다면 최소 시간 갱신(이동 시 1초씩 가중)
// 5. 각 좌표에 대한 모든 값이 찍힌 상태에서 동생이 이동했을 때 가장 작은 시간대를 선택 (할 필요 없어짐)
// 5-2. 5번 수정 -> 방문한 적이 있다면 2초마다 재방문 가능하므로 방문한 장소일시에는 그냥 현재 시간이 최소 시간이 된다.
// 6. 동생의 이동은 k 에서 1씩 가속하므로 변수를 이용해 이동 거리를 재야함
// 7. 동생의 좌표가 50만을 넘긴다면 -1 출력