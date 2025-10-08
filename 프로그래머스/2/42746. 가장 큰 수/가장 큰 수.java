import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] stringNumbers = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .toArray(String[]::new);
        
        Arrays.sort(stringNumbers, (a,b) -> (b+a).compareTo(a+b));
        
        if(stringNumbers[0].equals("0")) {
            return "0";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(String s : stringNumbers) {
            sb.append(s);
        }
        
        return sb.toString();
    }
}