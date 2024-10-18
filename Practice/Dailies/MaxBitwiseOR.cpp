/*
* Given integer array nums find the maximum possible bitwise OR of a subset of nums
* Return the number of different non-empty subsets with the maximum bitwise OR
* An array a is a subset of an array b if a can be obtained from b by deleting some (possibly, zero or all) elements
* Two subsets are considered different if the indices of the elements chosen are different
* The bitwise OR of an array a is equal to a[0] OR a[1] OR ... OR a[a.length - 1] where OR represents the bitwise-OR operation
*/

#include <iostream>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

// recursion and backtracking approach
class Solution {
public:
    int countMaxOrSubsets(vector<int>& nums) {
        // compute the maximum bitwise OR
        int maxOr = 0;
        for (int num : nums) {
            maxOr |= num;
        }
        // backtracking to explore all possible subsets
        int count = 0;
        backtrack(nums, 0, 0, maxOr, count);
        return count;
    }

    void backtrack(vector<int>& nums, int index, int currentOr, int maxOr, int& count) {
        if (index == nums.size()) {
            if (currentOr == maxOr) {
                count++;
            }
            return;
        }
        // include the current element
        backtrack(nums, index + 1, currentOr | nums[index], maxOr, count);
        // exclude the current element
        backtrack(nums, index + 1, currentOr, maxOr, count);
    }
};