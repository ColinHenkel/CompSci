import java.util.*;

public class MaxProductArray {
    public static void main(String[] args) {
        int[] nums = {3,4,5,2};
        System.out.println(maxProduct(nums));
    }

    public static int maxProduct(int[] nums) {
        // given the array of integers nums, choose two different indicies i and j of that array, return the maximum value of nums[i]-1 * nums[j]-1
        // 1 <= nums.length <= 500 
        // 1 <= nums[i] <= 10^3
        // 0 <= i < j < nums.length
        // nums = [3,4,5,2]
        // output = 12
        // nums = [1,5,4,5]
        // output = 16
        // nums = [3,7]
        // output = 12
        
        // minheap implmentaion
        int max = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

        for(int i = 0; i < nums.length; i++) {
            pq.add(nums[i]-1);
        }
        
        max = pq.poll() * pq.poll();
        return max;
    }
}
