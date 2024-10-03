# You are given an integer array nums and two integers minK and maxK
# A fixed-bound subarray of nums is a subarray that satsifies the following conditions:
# The minimum value in the subarray is equal to minK
# The maximum value in the subarray is equal to maxK
# Return the number of fixed-bound subarrays
# A subarray is a contiguous sequence of elements in an array

class Solution:
    def countSubarrays(self, nums: List[int], mink: int, maxK: int) -> int:
        res = 0
        bad_idx = left_idx = right_idx = -1

        for i, num in enumerate(nums) :
            if not mink <= num <= maxK:
                bad_idx = i

            if num == mink:
                left_idx = i

            if num == maxK:
                right_idx = i

            res += max(0, min(left_idx, right_idx) - bad_idx)
        return res