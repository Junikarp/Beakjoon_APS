import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			double N = Integer.parseInt(br.readLine());
			double sum = 0;
			
			for(int i = 1; i<=N; i++) {
				sum += Math.log10(i);
			}
			
			sum += 1;
			
			sb.append((int)sum).append("\n");
		}
		
		System.out.println(sb);

	}
}

