class Solution {
    public int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        
        int[] result = solve(yellow,brown);
        
        result[0] += 2;
        result[1] += 2;
        
        return result;
    }
    
    public int[] solve(int yellow, int brown) {
        int loop = yellow;
        int[] result = new int[2];
        
        for(int i = yellow; i > 0; i--) {
            if(yellow % i == 0) {
                int x = yellow/i;
                int y = i;
                if(((x+y) * 2) + 4 == brown) {
                    result[0] = x;
                    result[1] = y;
                }
            }
        }

        return result;
    }
    // 1 4 각 변 곱하기 2 + 4 = brown
    // 2 2
}