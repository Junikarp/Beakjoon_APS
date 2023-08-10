import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		boolean[][] paper = new boolean[102][102];

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			// 색종이의 위치
			for (int i = u; i < u + 10; i++) {
				for (int j = v; j < v + 10; j++) {
					paper[i][j] = true;
				}
			}
		}

		int cnt = 0;
		
		// 둘레구하기 (덮어진 부분에서 4방 탐색하여 0인 부분의 갯수가 둘레)
		for (int i = 1; i < paper.length - 1; i++) {
			for (int j = 1; j < paper.length - 1; j++) {
				if (paper[i][j]) {
					for(int k = 0; k < 4; k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];			
						if(!paper[nx][ny]) {
							cnt++;
						}
					}
				}
			}
		}	
		System.out.println(cnt);
	}
}
