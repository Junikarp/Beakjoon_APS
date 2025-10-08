import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        int n = citations.length;
        int h = 0;
        
        Arrays.sort(citations);
        
        for(int i = 0; i < n; i++) {
            if((n-i) <= citations[i]) {
                h = n-i;
                break;
            }
        }
        
        return h;
    }
}