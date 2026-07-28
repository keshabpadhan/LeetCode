class Solution {
    public static  String smallestPalindrome(String s) {

        int len = s.length();

        boolean flag = len % 2 == 0;

        if(flag) {
            String sub = s.substring(0, len/2);
            char[] crr = sub.toCharArray();
            Arrays.sort(crr);
            return String.valueOf(crr) + new StringBuilder(String.valueOf(crr)).reverse();
        } else {
            String sub = s.substring(0, len/2);
            char[] crr = sub.toCharArray();
            Arrays.sort(crr);
            return String.valueOf(crr) + s.charAt(len/2) +  new StringBuilder(String.valueOf(crr)).reverse();
        }

    }
}