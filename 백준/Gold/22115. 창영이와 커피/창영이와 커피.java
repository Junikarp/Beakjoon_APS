import java.util.*;
import java.io.*;

public class Main {
	static final int INIT_VALUE = 199_999;
	static int minVal = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[N+1][K+1];
		
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], INIT_VALUE);
		}
		
		for (int i = 0; i <= N; i++) {
			dp[i][0] = 0;
		}	
		
		for (int i = 1; i <= N; i++) {
			int coffee = arr[i-1];
			for (int j = 1; j <= K; j++) {
				if(j-coffee < 0) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-coffee] + 1);					
				}
			}
		}
		System.out.println(dp[N][K] == INIT_VALUE ? -1 : dp[N][K]);
    }
}