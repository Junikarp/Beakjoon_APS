import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		loop : for (int t = 1; t <= T; t++) {
			int[] A = new int[5];
			int[] B = new int[5];

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<a; i++) {
				A[Integer.parseInt(st.nextToken())]++;
			}
			
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<b; i++) {
				B[Integer.parseInt(st.nextToken())]++;
			}


			for (int i = 4; i > 0; i--) {
				if (A[i] > B[i]) {
					System.out.println("A");
					continue loop;
				} else if (B[i] > A[i]) {
					System.out.println("B");
					continue loop;
				} 

			}
			System.out.println("D");
		}
	}
}

// 1. 별(4)> 동그라미(3)> 네모(2)> 세모(1) (모두 같을 시에는 무승부)
