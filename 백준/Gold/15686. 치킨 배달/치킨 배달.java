import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static ArrayList<Point> ch, home;
	static boolean[] visited;
	static int chsize, homesize,N,M,dmin;
	static Point[] res;
	static class Point {
		int x;
		int y;
		int dist;
	
		public Point(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		res = new Point[M];
		ch = new ArrayList<>();
		home = new ArrayList<>();
		dmin = Integer.MAX_VALUE;
		
		for(int i = 1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					home.add(new Point(i,j));
				} else if(map[i][j] == 2) {
					ch.add(new Point(i,j));
				}
			}
		}
		
		chsize = ch.size();
		homesize = home.size();
		
		dfs(0,0);
		
		System.out.println(dmin);
	}
	
	public static void dfs(int d, int idx) {
		if(d == M) {
			dmin = Math.min(dmin, solve());
			return;
		}
		
		for(int i = idx; i<ch.size(); i++) {
			res[d] = ch.get(i);
			dfs(d+1, i+1);
			res[d] = null;
		}
	}
	
	public static int solve() {
		int sum = 0;
		for(int i = 0; i < home.size(); i++) {
			int min = Integer.MAX_VALUE;
			for(int j = 0; j < M; j++) {
				int d = Math.abs(home.get(i).x - res[j].x) + Math.abs(home.get(i).y - res[j].y);
				min = Math.min(d, min);
			}
			sum += min;
		}
		
		return sum;
	}
	
	public static void print() {
		for(int i = 0; i<M; i++) {
			System.out.print(res[i].toString()+" ");
		}
		System.out.println();
	}
}
