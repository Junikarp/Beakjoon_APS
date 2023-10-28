import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static class Room implements Comparable<Room> {
		int start;
		int end;

		public Room(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Room o) {
			if (this.start == o.start) {
				return this.end - o.end;
			}
			return this.start - o.start;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		List<Room> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.add(new Room(u, v));
		}

		Collections.sort(list);
		Queue<Integer> queue = new PriorityQueue<>();
		int end = 0;
		for (Room now : list) {
			end = now.end;

			if (!queue.isEmpty() && queue.peek() <= now.start) {
				queue.poll();
			}
			queue.add(end);
		}
		System.out.println(queue.size());
	}
}