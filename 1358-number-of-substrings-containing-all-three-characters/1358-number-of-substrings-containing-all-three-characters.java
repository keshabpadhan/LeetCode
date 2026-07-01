class Solution {
    public int numberOfSubstrings(String s) {
        int lastA = -1;
        int lastB = -1;
        int lastC = -1;
        int count = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            
            if (ch == 'a') {
                lastA = right;
            } else if (ch == 'b') {
                lastB = right;
            } else if (ch == 'c') {
                lastC = right;
            }
            
            if (lastA != -1 && lastB != -1 && lastC != -1) {
                int minIndex = Math.min(lastA, Math.min(lastB, lastC));
                
                count += minIndex + 1;
            }
        }
        
        return count;
    }
}