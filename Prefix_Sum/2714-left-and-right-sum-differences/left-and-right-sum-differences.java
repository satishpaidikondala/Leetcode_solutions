class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n=nums.length;
        int[] leftsum=new int[n];
        int[] rightsum=new int[n];
        int lsum=0,rsum=0;
        for(int i=0;i<n;i++){
            lsum+=nums[i];
            leftsum[i]=lsum;
            rsum+=nums[n-1-i];
            rightsum[i]=rsum;
        }
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Math.abs(leftsum[i]-rightsum[n-1-i]);
        }
        
        return arr;
    }
}