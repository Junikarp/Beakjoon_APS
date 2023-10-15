import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Block implements Comparable<Block> {
	int x;
	int y;
	int size;
	int rainbow;

	public Block(int x, int y, int size, int rainbow) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.rainbow = rainbow;
	}

	@Override
	public String toString() {
		return "Block [x=" + x + ", y=" + y + ", size=" + size + "]";
	}

	@Override
	public int compareTo(Block o) {

		if (o.size == this.size) {
			if (o.rainbow == this.rainbow) {
				if (this.x == o.x) {
					return o.y - this.y;
				}
				return o.x - this.x;
			}
			return o.rainbow - this.rainbow;
		}
		return o.size - this.size;
	}
}

public class Main {
	static int[][] map;
	static boolean[][] visit;
	static int N, M;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int sum, cnt;
	static int result = 0;
	static ArrayList<Block> blockSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 오토플레이 반복
		while (true) {
			blockSize = new ArrayList<Block>();
			visit = new boolean[N][N];

			// 가장 큰 블록 찾기
			// 각 블록마다 bfs 크기 찾아서 2이상이면 ArrayList에 다 넣음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0) {
						sum = 1;
						bfs(new Point(i, j));
						if (sum >= 2) {
							blockSize.add(new Block(i, j, sum, cnt));
						}
						sum = 1;
					}
				}
			}
			if (blockSize.size() == 0) {
				break;
			}

			// 정렬 후 기준블록 찾음 삭제 하면 싸그리 없어지니 상관없을것같다는게 내 논리
			Collections.sort(blockSize);
			int blockR = blockSize.get(0).x;
			int blockC = blockSize.get(0).y;
			visit = new boolean[N][N];
			sum = 1;
			remove(new Point(blockR, blockC));
			// 점수 획득
			result += Math.pow(sum, 2);

			gravity();
			rotate();
			gravity();

		}
		System.out.println(result);
	}

	// 블록 찾기
	// sum으로 블록 갯수 판별
	static void bfs(Point p) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(p);
		visit[p.x][p.y] = true;
		cnt = 0;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.x + dr[i];
				int nc = now.y + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) {
					continue;
				}
				if (map[nr][nc] == map[p.x][p.y] || map[nr][nc] == 0) {
					queue.add(new Point(nr, nc));
					sum++;
					visit[nr][nc] = true;
					if (map[nr][nc] == 0) {
						cnt++;
					}
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					visit[i][j] = false;
				}
			}
		}
	}

	// 블록 삭제
	// 삭제된 칸은 -2로 표시함
	static void remove(Point p) {
		int block = map[p.x][p.y];
		Queue<Point> queue = new LinkedList<Point>();
		queue.add(p);
		map[p.x][p.y] = -2;
		visit[p.x][p.y] = true;
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int i = 0; i < 4; i++) {
				int nr = now.x + dr[i];
				int nc = now.y + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visit[nr][nc]) {
					continue;
				}
				if (map[nr][nc] == block || map[nr][nc] == 0) {
					queue.add(new Point(nr, nc));
					map[nr][nc] = -2;
					sum++;
					visit[nr][nc] = true;
				}
			}
		}
	}

	// 중력
	static void gravity() {
		for (int i = 0; i < N; i++) {

			int block = 0;
			for (int j = N - 1; j >= 0; j--) {
				if (map[j][i] == -2 || map[j][i] == -1) {
					continue;
				}
				int index = j + 1;
				block = map[j][i];
				map[j][i] = -2;
				while (index < N && map[index][i] == -2) {
					index++;
				}
				map[index - 1][i] = block;
			}
		}
	}

	// 회전
	static void rotate() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[j][N - 1 - i];
			}
		}
		for (int i = 0; i < N; i++) {
			map[i] = temp[i].clone();
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}