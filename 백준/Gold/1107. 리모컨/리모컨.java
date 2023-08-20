import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 고장난 버튼 확인용
    static boolean[] broken = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 이동하려는 채널
        int N = Integer.parseInt(br.readLine());
        // 고장난 버튼 수
        int M = Integer.parseInt(br.readLine());
        // 숫자 버튼 제외하고 이동할 거리
        int target = Math.abs(N - 100);
        int move = 0;

        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        loop1:
        for (int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            int len = str.length();
            for (int j = 0; j < len; j++) {
                // 고장난 버튼을 눌러야 하면 처음으로
                if (broken[str.charAt(j) - '0']) {
                    continue loop1;
                }
            }
            // 이동 숫자 + '+','-'버튼 누르는 횟수와 기존 값 비교
            target = Math.min(len + Math.abs(N - i), target);
        }

        System.out.println(target);

    }
}

// 1. 타겟에 가장 가까운 위치까지 이동 -> 타겟 숫자의 길이가 누르는 숫자 버튼 수
// 2. 타겟에서 가장 가까운 숫자를 뺀 절댓값이 +, - 버튼을 누르는 횟수
// 3. 범위는 500000 채널까지 이므로 1부터 999999 까지 탐색