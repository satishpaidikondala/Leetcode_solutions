class Solution {
    public int[] productExceptSelf(int[] nums) {
        int product = 1,index = -1,zero = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i]!= 0) product*= nums[i];
            else{
                index =i;
                zero++;
            }
        }
        for(int i=0;i<nums.length;i++){
            if(zero == 0) nums[i] = product/nums[i];
            else if(zero == 1){
                if(i == index)nums[i] = product;
                else nums[i] = 0;
            }
            else nums[i] = 0;
        }
        return nums;
    }
}