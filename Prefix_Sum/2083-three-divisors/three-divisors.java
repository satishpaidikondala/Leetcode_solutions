class Solution {
    public boolean isThree(int n) {
        int root = (int) Math.sqrt(n);
        
        // check perfect square
        if (root * root != n) return false;
        
        // check prime
        return isPrime(root);
    }
    
    private boolean isPrime(int x) {
        if (x < 2) return false;
        
        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }
        
        return true;
    }
}