class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int deliver_needed = 0;
        int pickup_needed = 0;

        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] == 0 && pickups[i] == 0) {
                continue;
            }

            deliver_needed += deliveries[i];
            pickup_needed += pickups[i];
            
            while (deliver_needed > 0 || pickup_needed > 0) {
                answer += (i + 1) * 2;
                
                deliver_needed -= cap;
                pickup_needed -= cap;
            }
        }
        
        return answer;
    }
}