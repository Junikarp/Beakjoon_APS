import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long N = Long.parseLong(br.readLine());

		long cnt = 0;

		for (long i = 1; i <= N; i++) {
			if (N >= 0) {
				N = N - i;
				cnt++;
			} else
				cnt--;
		}
		System.out.print(cnt);
	}
}