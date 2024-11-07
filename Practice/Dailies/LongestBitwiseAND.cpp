/*
* The bitwise AND of an array nums is the bitwise AND of all integers in nums.
* You are given an array of positive integers candidates. Evaluate the bitwise AND of every combination of numbers of candidates. 
* Each number in candidates may only be used once in each combination.
* Return the size of the largest combination of candidates with a bitwise AND greater than 0.
*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
    public:
        int largestCombination(vector<int>& candidates) {
            // bitwise AND > 0 iff all candidates have atleast one common bit set to 1
            // find max number of candidates that can have a common bit set to 1

            // if size is 1 return 1
            if(candidates.size() == 1) return 1;

            // count number of 1s in each bit
            // each int in candidates is 24 bits
            vector<int> bitCount(24, 0);
            for(int candidate : candidates) {
                for(int i = 0; i < 24; i++) {
                    if(candidate & (1 << i)) bitCount[i]++;
                }
            }

            // find max number of candidates that can have a common bit set to 1
            // if even 1 candidate has a bit set to 1, add it to the count as that bit is common with itself thanks to the problem description
            int maxCount = 0;
            for(int i = 0; i < 24; i++) {
                if(bitCount[i] >= 1) maxCount = max(maxCount, bitCount[i]);
            }

            return maxCount;
        }
};