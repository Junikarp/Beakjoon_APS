import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	static int N, max;
	static int[] arr, comb;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		max = -1;
		arr = new int[N];
		comb = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0);
		
		System.out.println(max);
	}
	
	public static void combination(int d) {
		if(d == N) {
			int sum = 0;
			for (int i = 0; i < N-1; i++) {
				sum += Math.abs(comb[i]-comb[i+1]);				
			}
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) {
				continue;
			}
			comb[d] = arr[i];
			visited[i] = true;
			combination(d+1);
			visited[i] = false;
		}
	}
}