import java.io.*;
import java.util.*;

public class Main {
	static class Product {
		int v;
		int c;

		public Product(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Product> product = new ArrayList<>();
		product.add(new Product(0, 0));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int tmp = 1;
			while (tmp <= K) {
				product.add(new Product(tmp * V, tmp * C));
				K -= tmp;
				tmp *= 2;
			}
			if (K != 0) {
				product.add(new Product(K * V, K * C));
			}
		}

		int[][] dp = new int[product.size() + 1][M + 1];

		for (int i = 1; i < product.size(); i++) {
			for (int j = 1; j <= M; j++) {
				if (j < product.get(i).v) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - product.get(i).v] + product.get(i).c);
				}
			}
		}

		System.out.println(dp[product.size() - 1][M]);
	}
}