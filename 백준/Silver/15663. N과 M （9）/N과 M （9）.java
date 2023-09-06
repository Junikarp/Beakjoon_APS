import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int[] arr,res;
	static boolean[] visited;
	static LinkedHashSet<String> set = new LinkedHashSet<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		visited = new boolean[N];
		res = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		dfs(0);
		
		for(String str : set) {
			System.out.println(str);
		}
	}
	
	public static void dfs(int d) {
		if(d==M) {
			StringBuilder sb = new StringBuilder();
			for (int p : res) {
				sb.append(p).append(" ");
			}
			set.add(sb.toString());
			return;
		}
		
		for(int i = 0; i<arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				res[d] = arr[i];
				dfs(d+1);
				visited[i] = false;
			}
		}
	}
}


