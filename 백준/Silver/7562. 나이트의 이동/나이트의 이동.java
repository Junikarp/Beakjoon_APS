import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {2,2,1,1,-2,-2,-1,-1};
	static int[] dy = {1,-1,2,-2,1,-1,2,-2};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			// 체스판 배열
			int[][] chess = new int[N][N];
			// 방문 여부 확인
			boolean[][] visited = new boolean[N][N];
			
			// 출발 좌표 입력
			st = new StringTokenizer(br.readLine());
			int start_x = Integer.parseInt(st.nextToken());
			int start_y = Integer.parseInt(st.nextToken());
			// 도착 좌표 입력
			st = new StringTokenizer(br.readLine());
			int end_x = Integer.parseInt(st.nextToken());
			int end_y = Integer.parseInt(st.nextToken());
			
			sb.append(bfs(start_x,start_y,end_x,end_y,visited,chess)).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int bfs(int start_x, int start_y, int end_x, int end_y, boolean[][] visited, int[][] chess) {
		// 큐 생성
		Queue<Point> queue = new LinkedList<>();
		// 시작 좌표 큐에 삽입
		queue.offer(new Point(start_x,start_y));
		// 시작 좌표 방문 표시
		visited[start_x][start_y] = true;
		
		// 탐색 시작
		while(!queue.isEmpty()) {
			// 탐색 시작점
			Point p = queue.poll();
			// 나이트가 움직이는 좌표 구현
			for(int i=0; i<8; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				// 체스판의 크기를 넘어가지 않으면서 방문하지 않은 지점일 경우 방문
				if(nx>=0 && ny>=0 && nx<chess.length && ny<chess.length && !visited[nx][ny]) {
					visited[nx][ny] = true;
					// 이전 지점에서 1만큼 더 움직였으므로 +1
					chess[nx][ny] = chess[p.x][p.y]+1;
					// 새로운 지점 큐에 삽입
					queue.offer(new Point(nx,ny));
				}
			}
		}
		// 도착점까지의 이동 거리 반환
		return chess[end_x][end_y];
	}
}

// 1. 체스판 배열 구현
// 2. 나이트의 이동 구간을 표현하기 위한 dx, dy 배열 구현
// 3. visited 배열로 방문지점 확인
// 4. 나이트가 이동할 때마다 카운팅 -> 해당 지점 +1
// 5. 지정된 좌표의 변수 출력