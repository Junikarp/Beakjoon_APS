import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] visited;
	static int[] cheeze = new int[2000];
	static int day = 0;
	static Queue<Point> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 치즈 맵
		map = new int[N][M];
		// 공기 방문 표시
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 첫 시작 지점
		queue.offer(new Point(0, 0));
		
		while (true) {
			bfs();
			melt();
			// 탐색과 치즈 녹이기 이후 새로 0이된 부분 큐에 저장
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] == 0 && visited[i][j]) {
						queue.offer(new Point(i,j));
					}
				}				
			}
			
			// 치즈가 모두 녹으면 종료
			if(cheeze[day] == 0) {
				break;
			}
			day++;
		}
		sb.append(day).append("\n").append(cheeze[day-1]);
		
		System.out.println(sb);
	}

	public static void bfs() {
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			visited[p.x][p.y] = true;
			// 4방 탐색하며 공기가 있는 지점 방문 표시
			for (int i = 0; i < 4; i++) {
				int nc = p.x + dc[i];
				int nr = p.y + dr[i];
				if (nc >= map.length || nr >= map[0].length || nc < 0 || nr < 0) {
					continue;
				}
				if (!visited[nc][nr] && map[nc][nr] == 0) {
					visited[nc][nr] = true;
					queue.offer(new Point(nc, nr));
				}
			}
		}
	}

	public static void melt() {
		int chz = 0;
		// 탐색하면서 치즈의 갯수 세기
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 1) {
					// 치즈의 갯수 세기
					chz++;
					int cnt = 0;
					// 4방향 중 공기가 닿은 부분 확인
					for (int k = 0; k < 4; k++) {
						int nc = i + dc[k];
						int nr = j + dr[k];
						if (nc >= map.length || nr >= map[0].length || nc < 0 || nr < 0) {
							continue;
						}
						if (visited[nc][nr]) {
							cnt++;
						}
					}
					// 치즈와 닿은 부분은 0으로 전환
					if(cnt >= 1) {
						map[i][j] = 0;
					}
				}
			}
		}
		// 해당 일자의 치즈의 갯수 저장
		cheeze[day] = chz;
	}
}

// 1. 0,0 에서 출발하여 bfs 실행 0으로 연결된 부분은 방문표시
// 2. map 을 순회하면서 사방탐색하여 한칸에 방문표시 된 곳 2곳 이상 맞닿았다면 치즈 제거
// 3. 치즈가 모두 없어질 때까지 반복
