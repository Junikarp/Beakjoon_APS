import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[N+1];
		int[] dp = new int[M+1];
		
		for (int i = 1; i <= N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE-20);
		
		dp[0] = 0;
		
		for (int i = 1; i <= N; i++) {
			for(int j = coin[i]; j <= M; j++) {
				dp[j] = Math.min(dp[j], dp[j-coin[i]] + 1);
			}
		}
		
		if(dp[M] == Integer.MAX_VALUE-20) {
			System.out.println(-1);
		} else {
			System.out.println(dp[M]);			
		}
	}
}