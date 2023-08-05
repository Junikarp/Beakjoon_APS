import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] arr2 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 기존 배열과 정렬할 배열을 분리
        for(int i=0; i<arr.length; i++) {
             arr[i] = Integer.parseInt(st.nextToken());
             arr2[i] = arr[i];
        }

        // 배열 정렬
        Arrays.sort(arr);

        Map<Integer, Integer> map = new HashMap<>();

        // map에 중복된 키값이 없다면 자신의 순서 기록
        for(int i=0, index = 0; i<arr.length; i++) {
            if(!map.containsKey(arr[i])){
                map.put(arr[i], index++);
            }
        }

        // 정렬 전의 배열의 순서대로 값 찾아오기
        for(int i=0; i<arr2.length; i++) {
            sb.append(map.get(arr2[i])).append(" ");
        }

        System.out.print(sb);
    }
}
// 1. 주어진 숫자들을 정렬
// 2. map 에 순서대로 넣으며 값부여 => 1씩 추가
// 3. 순위를 출력
