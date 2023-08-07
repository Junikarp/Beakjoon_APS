import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static ArrayList<Integer> set;
	static boolean[] exist;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		exist = new boolean[21];
		set = new ArrayList<>();
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			
			if(str.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				add(x);
			} else if (str.equals("remove")) {
				int x = Integer.parseInt(st.nextToken());
				remove(x);
			} else if(str.equals("check")) {
				int x = Integer.parseInt(st.nextToken());
				check(x);
			} else if(str.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				toggle(x);
			} else if(str.equals("all")) {
				all();
			} else if(str.equals("empty")) {
				empty();
			}
		}
		System.out.print(sb);
	}
	
	public static void add(int x) {
		if(!exist[x]) {
			set.add(x);
			exist[x] = true;
		}	
	}
	
	public static void remove(int x) {
		if(exist[x]) {
			for(int i=0; i<set.size(); i++) {
				if(set.get(i) == x) {
					set.remove(i);
					exist[x] = false;
				}
			}
		}
	}
	
	public static void check(int x) {
		if(exist[x]) {
			sb.append(1).append("\n");
		} else {
			sb.append(0).append("\n");			
		}
	}
	
	public static void toggle(int x) {
		if(!exist[x]) {
			set.add(x);
			exist[x] = true;
		} else {
			for(int i=0; i<set.size(); i++) {
				if(set.get(i)==x) {
					set.remove(i);
					exist[x] = false;
				}
			}
		}
	}
	
	public static void all() {
		set.clear();
		
		for(int i=1; i<=20; i++) {
			set.add(i);
			exist[i] = true;
		}
	}
	
	public static void empty() {
		set.clear();
		
		for(int i=1; i<=20; i++) {
			exist[i] = false;
		}
	}
}