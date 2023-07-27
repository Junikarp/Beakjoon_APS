import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 갯수
        int T = Integer.parseInt(br.readLine());
        // 소수 구분용 배열 생성
        boolean[] isPrime = new boolean[1000001];
        isPrime[0] = true;
        isPrime[1] = true;
        // 에라토스테네스의 체로 소수 판별
        for(int i=2; i<Math.sqrt(isPrime.length); i++) {
            for(int j =i*i; j<isPrime.length; j+=i){
                if(!isPrime[j]){
                    isPrime[j] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        // 두 소수의 합이 주어진 짝수인지 확인
        for(int i = 0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            int A = N/2;
            int B = N/2;
            int cnt = 0;
            while(A>0) {
                // 두 소수의 합이 주어진 짝수라면 카운팅
                if(!isPrime[A] && !isPrime[B]){
                    cnt++;
                }
                // 두 수를 1씩 더하고 뺌
                    A--;
                    B++;
            }
            sb.append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}

// 1. 소수 구분용 배열 생성 (boolean 타입)
// 2. 짝수를 반으로 나누고 한 쪽은 ++, 한 쪽은 -- 하면서 둘 다 소수인 경우 카운팅
