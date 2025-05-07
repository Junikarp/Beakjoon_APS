import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        int N = clothes.length;
        
        for(int i = 0; i < clothes.length; i++) {
            if(!map.containsKey(clothes[i][1])) {
                map.put(clothes[i][1],1);
            } else {
                map.replace(clothes[i][1],map.get(clothes[i][1])+1);
            }
        }
        
        int answer = 1;
        
        Set<String> set = map.keySet();
        
        for(String val : set) {
            answer *= (map.get(val)+1);
        }
    
        return answer-1;
    }
}