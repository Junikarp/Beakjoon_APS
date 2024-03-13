import java.util.Stack;

public class Solution {
    public String solution(String number, int k) {
        // 스택 선언
        Stack<Character> stack = new Stack<>();
        
        // number의 길이만큼 반복
        for (char num : number.toCharArray()) {
            // 제거할 수 k가 남았고, 스택이 비어있지 않고, 스택의 맨 위 값이 현재 값보다 작을 때까지 반복
            while (k > 0 && !stack.isEmpty() && stack.peek() < num) {
                stack.pop(); // 스택에서 값을 제거
                k--; // 제거할 수를 1 감소
            }
            stack.push(num); // 현재 값을 스택에 추가
        }
        
        // k가 남아있는 경우 (예: number가 "93939"이고 k가 2인 경우)
        while (k > 0 && !stack.isEmpty()) {
            stack.pop(); // 스택에서 값을 제거
            k--; // 제거할 수를 1 감소
        }
        
        // 스택에 있는 값을 문자열로 변환하여 반환
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
