import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());

		if (N == 1 || N == 0) {
			System.out.println(N);
			return;
		}

		N--;

		long[][] arr1 = { { 1, 1 }, { 1, 0 } };
		long[][] arr2 = { { 1, 0 }, { 0, 1 } };

		while (N > 0) {

			if (N % 2 == 1) {
				arr2 = solve(arr2, arr1);
			}
			arr1 = solve(arr1, arr1);
			N /= 2;
		}

		System.out.println(arr2[0][0]);

	}

	public static long[][] solve(long[][] arr1, long[][] arr2) {

		long[][] arr = new long[2][2];

		arr[0][0] = ((arr1[0][0] * arr2[0][0]) + (arr1[0][1] * arr2[1][0])) % 1000000007;
		arr[0][1] = ((arr1[0][0] * arr2[0][1]) + (arr1[0][1] * arr2[1][1])) % 1000000007;
		arr[1][0] = ((arr1[1][0] * arr2[0][0]) + (arr1[1][1] * arr2[1][0])) % 1000000007;
		arr[1][1] = ((arr1[1][0] * arr2[0][1]) + (arr1[1][1] * arr2[1][1])) % 1000000007;

		return arr;
	}
}