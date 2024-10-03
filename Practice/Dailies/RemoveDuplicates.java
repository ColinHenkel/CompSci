public class RemoveDuplicates {
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        // Given integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once
        // return the number of unique elements in nums
        // nums = [1,1,2]
        // output = 2, nums = [1,2]
        // nums = [0,0,1,1,1,2,2,3,3,4]
        // output = 5, nums = [0,1,2,3,4]
        // 0 <= nums.length <= 3 * 10^4
        // -10^4 <= nums[i] <= 10^4

        // two pointers implementation
        int i = 0;
        for(int j = 1; j < nums.length; j++) {
            if(nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j]; 
            }
        }
        return i+1;
    }
}
