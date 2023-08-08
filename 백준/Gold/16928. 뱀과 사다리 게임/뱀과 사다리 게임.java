import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] board = new int[101];
	static Map<Integer, Integer> up_down = new HashMap<>();
	static Queue<Integer> queue = new LinkedList<>();
	static boolean[] visited = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		// 사다리의 갯수
		int N = Integer.parseInt(st.nextToken());
		// 뱀의 갯수
		int M = Integer.parseInt(st.nextToken());

		// 사다리와 뱀을 타서 갈 수 있는 위치와 도착지점
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			up_down.put(u, v);

		}
		
		// 탐색 시작
		bfs();

		System.out.println(board[100]);

	}

	public static void bfs() {
		// 1번칸에서 출발
		queue.offer(1);
		visited[1] = true;

		// 너비 우선 탐색
		loop: while (!queue.isEmpty()) {
			int start = queue.poll();
			// 1~6까지 주사위를 굴려 이동
			for (int i = 1; i < 7; i++) {
				// 시작지점에서 주사위를 굴려서 이동한 거리
				int nx = start + i;
				// 100을 넘어가거나 방문한 장소는 continue;
				if(100 < nx || visited[nx]) {
					continue;
				}
				// 사다리와 뱀이 없을 경우 그 지점으로 이동 후 주사위 횟수 +1
				if (!visited[nx] && !up_down.containsKey(nx) && board[nx]==0) {
					visited[nx] = true;
					board[nx] = board[start] + 1;
					queue.offer(nx);
				}
				// 사다리가 있을 경우 사다리로 이동 후 주사위 횟수 +1
				else if (!visited[nx] && up_down.containsKey(nx) && !visited[up_down.get(nx)]) {
					visited[nx] = true;
					board[up_down.get(nx)] = board[start] + 1;
					queue.offer(up_down.get(nx));
				}
		
				// 100 번째 칸 도착 시 반복문 탈출 및 100번째 칸 주사위 횟수 갱신
				else if (nx == 100) {
					board[nx] = board[start] + 1;
					break loop;
				} 
			}
		}
	}
}

// 1. 1~100 까지 입력한 10x10 보드 생성
// 2. 1~6까지 현재 지점에서 이동하면서 주사위 횟수 기록
// 3. 만일 지름길이 있다면 즉시 그 위치로 이동 (Map 활용)
// 4. 최종적으로 100 지점에 도달 시 주사위 횟수 출력