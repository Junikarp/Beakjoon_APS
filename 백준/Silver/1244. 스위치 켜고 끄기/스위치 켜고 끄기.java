import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] swit = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < swit.length; i++) {
			swit[i] = Integer.parseInt(st.nextToken());
		}

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			if (u == 1) {
				for (int i = 1; i < swit.length; i++) {
					if (v * i < swit.length && swit[v * i] == 1) {
						swit[v * i] = 0;
					} else if (v * i < swit.length && swit[v * i] == 0) {
						swit[v * i] = 1;
					}
				}
			}
			if (u == 2) {
				int nx = 1;
				if (swit[v] == 1) {
					swit[v] = 0;
				} else {
					swit[v] = 1;
				}
				while (true) {
					if (v - nx > 0 && v + nx < swit.length)
						if (swit[v + nx] == swit[v - nx]) {
							if (swit[v + nx] == 1) {
								swit[v + nx] = 0;
								swit[v - nx] = 0;
							} else {
								swit[v + nx] = 1;
								swit[v - nx] = 1;
							}
						} else {
							break;
						}
					else {
						break;
					}
					nx++;
				}
			}
		}

		for (int k = 1; k < swit.length; k++) {
			System.out.print(swit[k] + " ");
			if (k % 20 == 0) {
				System.out.println();
			}
		}
	}
}
