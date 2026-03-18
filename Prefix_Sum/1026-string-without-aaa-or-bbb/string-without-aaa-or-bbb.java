class Solution {
    public String strWithout3a3b(int a, int b) {
        StringBuilder sb = new StringBuilder();
        
        while (a > 0 || b > 0) {
            boolean writeA = false;
            int len = sb.length();
            
            // Check if we already have two 'a's or two 'b's at the end
            if (len >= 2 && sb.charAt(len - 1) == sb.charAt(len - 2)) {
                // If the last two are the same, we MUST write the other character
                if (sb.charAt(len - 1) == 'b') {
                    writeA = true;
                } else {
                    writeA = false;
                }
            } else {
                // Otherwise, just write the character we have more of
                if (a >= b) {
                    writeA = true;
                } else {
                    writeA = false;
                }
            }
            
            if (writeA) {
                a--;
                sb.append('a');
            } else {
                b--;
                sb.append('b');
            }
        }
        
        return sb.toString();
    }
}