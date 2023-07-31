import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int[][] tomato;
	static boolean[][] visited;
	static Queue<Point> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 토마토가 담겨진 상자
		tomato = new int[M][N];
		// 토마토가 익었는지 여부
		visited = new boolean[M][N];

		// 토마토 위치 입력
		for (int i = 0; i < tomato.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < tomato[0].length; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 토마토가 없는 칸이라면 방문 표시 && 토마토가 있는 칸이라면 방문 표시 후 큐에 삽입
		for (int i = 0; i < tomato.length; i++) {
			for (int j = 0; j < tomato[0].length; j++) {
				if(tomato[i][j] == -1) {
					visited[i][j] = true;
				}
				if(tomato[i][j] == 1) {
					visited[i][j] = true;
					queue.offer(new Point(i,j));
					tomato[i][j] = 0;
				}
			}
		}
		// 탐색 시작
		bfs();
		
		int max = 0;
		
		// 안익은 토마토가 있다면 최댓값을 -1 로 갱신 && 그렇지 않다면 최댓값 갱신
		loop1 : for (int i = 0; i < tomato.length; i++) {
			for (int j = 0; j < tomato[0].length; j++) {
				if(!visited[i][j]) {
					max = -1;
					break loop1;
				} else {
					max = Math.max(tomato[i][j], max);
				}
			}
			
		}
			
		System.out.println(max);
		
	}
	public static void bfs() {
		// 큐가 빌때까지 반복
		while(!queue.isEmpty()) {
			// 큐에서 이전 토마토 위치 꺼내기
			Point p = queue.poll();
			// 4방으로 탐색
			for(int i=0; i<4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 토마토 창고를 벗어나지 않으면서 익지않은 토마토가 있다면 익힌 후 큐에 삽입 , 날짜 입력
				if(nx>=0 && ny>=0 && nx<tomato.length && ny<tomato[0].length && !visited[nx][ny]) {
					visited[nx][ny] = true;
					tomato[nx][ny] = tomato[p.x][p.y] + 1;
					queue.offer(new Point(nx,ny));
				}
			}
		}
	}
}

// 1. 토마토가 심어진 창고 배열 생성 / 방문 여부 표시 배열까지 생성
// 2. 만일 토마토가 들어있지 않은 칸이 있다면 방문표시
// 3. 시작 지점에서 4방을 탐색하면서 카운팅
// 4. 모든 지점이 방문표시 되어있다면 가장 배열에서 가장 큰 카운팅값 출력
// 5. 방문하지 않은 지점이 존재한다면 -1 출력
