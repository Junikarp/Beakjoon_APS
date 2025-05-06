import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < participant.length; i++) {
            if(!map.containsKey(participant[i])) {
                map.put(participant[i],1);
            } else {
                map.replace(participant[i],map.get(participant[i])+1);
            }
        }
        
        for(int i = 0; i < completion.length; i++) {
            map.replace(completion[i],map.get(completion[i])-1);
            if(map.get(completion[i]) == 0) {
                map.remove(completion[i]);
            }
        }
        
        String answer = "";

        
        for(int i = 0; i < participant.length; i++) {
            if(map.containsKey(participant[i])) {
                answer = participant[i];
                break;                                
            }
        }

        return answer;
    }
}