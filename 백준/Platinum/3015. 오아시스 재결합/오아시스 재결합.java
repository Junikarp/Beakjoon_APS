import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class person {
	int h;
	int cnt;

	public person(int h, int cnt) {
		this.h = h;
		this.cnt = cnt;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stack<person> stack = new Stack<>();
		long cnt = 0;
		for (int i = 1; i <= N; i++) {
			int now = Integer.parseInt(br.readLine());

			person next = new person(now, 1);

			while (!stack.isEmpty() && stack.peek().h <= now) {
				person p = stack.pop();
				cnt += p.cnt;
				if (p.h == now) {
					next.cnt += p.cnt;
				}
			}

			if (!stack.isEmpty()) {
				cnt++;
			}
			stack.push(next);
		}
		System.out.print(cnt);
	}
}