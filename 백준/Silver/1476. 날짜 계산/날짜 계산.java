import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 1;
		
		// 입력된 숫자와 E,S,M 이 각각 같다면 0으로 변환
		if(E == 15) {
			E = 0;
		}
		if(S == 28) {
			S = 0;
		}
		if(M == 19) {
			M = 0;
		}
		// 입력 값과 각각의 수로 나눈 나머지가 다르다면 카운팅
		// 같다면 출력
		while (true) {
			if (cnt % 15 == E && cnt % 28 == S && cnt % 19 == M) {
				System.out.print(cnt);
				break;
			} else {
				cnt++;
				
			}
		}
	}

}

// 1. 입력값은 E,S,M으로 나눈 나머지
// 2. 값을 찾을때까지 while 문을 돌리며 숫자 카운팅