import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] arr1;
	static int[] arr2;

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine());
		arr1 = new int[N];
		arr(arr1, N);

		int M = Integer.parseInt(br.readLine());
		arr2 = new int[M];
		arr(arr2, M);

		Arrays.sort(arr1);

		for (int i = 0; i < arr2.length; i++) {
			System.out.print(merge(arr2[i], 0, N - 1) + " ");
		}
	}

	public static void arr(int[] arr, int N) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}

	public static int merge(int key, int start, int end) {

		int mid;

		if (start <= end) {
			mid = (start + end) / 2;
			if (arr1[mid] == key) {
				return 1;
			} else if (arr1[mid] > key) {
				return merge(key, start, mid - 1);
			} else {
				return merge(key, mid + 1, end);
			}
		}
		return 0;
	}
}