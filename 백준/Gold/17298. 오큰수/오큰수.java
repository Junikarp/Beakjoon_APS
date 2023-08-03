import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		Stack<Integer> stack = new Stack<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());			
		}
		
		for(int i=0; i<arr.length; i++) {
			while(!stack.empty()&&arr[stack.peek()]<arr[i]) {
				arr[stack.pop()] = arr[i];
			}
			
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			arr[stack.pop()] = -1;
		}
		
		for(int i=0; i<arr.length; i++) {
			sb.append(arr[i]+" ");
		}
		
		System.out.print(sb);
		
	}
}