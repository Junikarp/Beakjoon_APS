import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int cnt;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 테스트 케이스 갯수
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			// 배추밭의 가로길이
			int M = Integer.parseInt(st.nextToken());
			// 배추밭의 세로길이
			int N = Integer.parseInt(st.nextToken());
			// 배추의 개수
			int K = Integer.parseInt(st.nextToken());
			// 배추가 심어진 밭
			int[][] baechu = new int[M][N];
			// 배추밭 방문 여부
			boolean[][] visited = new boolean[M][N];
			// 배추가 심어진 좌표 확인
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				// 배추가 심어진 위치
				baechu[u][v] = 1;
			}
			
			// 벌레가 없는 배추무리 카운팅, 접해있는 배추들 탐색 및 방문 확인
			for(int i=0; i<baechu.length; i++) {
				for(int j=0; j<baechu[i].length; j++) {
					// 배추가 심어져 있고 벌레가 없는 배추밭이라면
					if(baechu[i][j]==1&&!visited[i][j]) {
						// 배추 무리 갯수 카운팅
						cnt++;
						// 깊이 우선 탐색
						dfs(i,j,visited,baechu);
					}
				}
			}
			sb.append(cnt).append("\n");
			// 다음 테스트 케이스를 위한 초기화
			cnt = 0;
		}
		System.out.println(sb);
	}
	
	public static void dfs(int x, int y, boolean[][] visited, int[][] baechu) {
		// 방문 시작점 방문 체크
		visited[x][y] = true;
		int nx = 0;
		int ny = 0;
		for(int i=0; i<4; i++) {
			// 좌우 탐색
			nx = x + dx[i];
			// 상하 탐색
		    ny = y + dy[i];
		    // 배열의 범위를 벗어나지 않으면서 방문하지 않은 배추밭일 경우 방문체크
		    if(nx>=0 && ny>=0 && nx<baechu.length && ny<baechu[i].length && !visited[nx][ny] && baechu[nx][ny]==1) {
		    	// 배추 무리에 남은 배추가 없을 때까지 탐색 재개
		    	dfs(nx,ny,visited,baechu);
		    }
		}
	}
}

// 1. 가로길이 , 세로길이  크기의 배열 생성
// 2. 주어진 좌표에 맞춰 배추가 심어진 땅 표시
// 3. 배추 심어진 여부 표시
// 4. 좌표를 이동하면서 배추를 찾은 경우 상하좌우로 이동하며 방문표시 (dfs)
// 5. 새로운 배추무리를 발견할 때 마다 카운팅