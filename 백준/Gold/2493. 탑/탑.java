import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 신호를 수신하는 탑의 위치
        int[] tower = new int[N+1];
        // 탑의 높이를 기록한 맵
        Map<Integer, Integer> tower_num = new HashMap<>();
        // 탑의 위치를 기록한 맵
        Map<Integer, Integer> tower_index = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 탑 높이 입력
        for(int i = 1; i <= N; i++) {
            int tmp =Integer.parseInt(st.nextToken());
            tower_num.put(i, tmp);
            tower_index.put(tmp, i);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = N; i > 0; i--) {
            // 현재 탑에서 수신 받은 신호 갯수
            int cnt = 0;
            // 다음 타워의 높이
            int next_tower = tower_num.get(i);
            // 스택이 비어있거나 이전 탑의 위치보다 다음 탑이 낮으면 스택에 삽입
            if(stack.empty() || stack.peek()>next_tower) {
                stack.push(next_tower);
            } else {
                // 스택이 비어있지 않고 이전 탑이 현재 탑보다 낮은 동안 스택에서 꺼내고 카운팅
                while(!stack.empty() && stack.peek()<next_tower) {
                    int num = stack.pop();
                    /// 뽑아낸 타워의 위치 찾아서 입력
                    tower[tower_index.get(num)] = i;
                }
                // 이전 탑중 더 낮은 탑이 없다면 스택에 삽입
                stack.push(next_tower);
            }
            // 카운팅 한 만큼 수신한 탑의 위치 입력
        }

        for(int i=1; i<tower.length; i++) {
            sb.append(tower[i]).append(" ");
        }

        System.out.print(sb);

    }
}

// 1. 입력받은 순서대로 map 에 저장 (탑의 위치 기록)
// 2. 가장 왼쪽의 탑부터 스택에 저장
// 3. 만일 다음 탑이 자신보다
// 3-1. 작다면 스택에 입력
// 3-2. 크다면 더 큰 원소가 남을 때까지 스택에서 원소를 꺼내고 기록
// 4. 전파 수신 입력 배열 출력