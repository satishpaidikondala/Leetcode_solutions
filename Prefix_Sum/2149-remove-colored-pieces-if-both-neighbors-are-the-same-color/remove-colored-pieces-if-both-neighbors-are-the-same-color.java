class Solution {
    public boolean winnerOfGame(String colors) {
        int aliceMoves = 0;
        int bobMoves = 0;
        
        // We iterate from index 1 to n-2 because edges cannot be removed
        for (int i = 1; i < colors.length() - 1; i++) {
            char prev = colors.charAt(i - 1);
            char curr = colors.charAt(i);
            char next = colors.charAt(i + 1);
            
            if (curr == 'A' && prev == 'A' && next == 'A') {
                aliceMoves++;
            } else if (curr == 'B' && prev == 'B' && next == 'B') {
                bobMoves++;
            }
        }
        
        // Alice wins only if she has more moves than Bob
        return aliceMoves > bobMoves;
    }
}