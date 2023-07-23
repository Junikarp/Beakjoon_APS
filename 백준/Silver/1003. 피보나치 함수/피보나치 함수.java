import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int[] dp = new int[10000];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;

		int T = Integer.parseInt(br.readLine());

		for (int i = 2; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				sb.append(1 + " " + 0 +"\n");
			} else {
				sb.append(dp[N - 1] + " " + dp[N] + "\n");
			}
		}

		System.out.println(sb);
	}
}