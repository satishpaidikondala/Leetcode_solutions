class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int[] ps = new int[n];
        ps[0] = nums[0];
        for(int i = 1; i < n; i++) ps[i] = ps[i - 1] + nums[i];
        int len = Integer.MAX_VALUE;
        int left = 0, right = 0, sum = 0;
        while(left <= right  && right < n){
            if(left > 0) sum = ps[right] - ps[left - 1];
            else sum = ps[right];
            if(sum >= target) len = Math.min(len, right - left + 1);
            if(sum < target) right++;
            else left++;
        }
        if(len == Integer.MAX_VALUE) return 0;
        return len;
    }
}