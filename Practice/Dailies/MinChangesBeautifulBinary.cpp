/*
* Given 0-indexed binary string s having even length
* A string is beautiful if its possible to partition it into one or more substrings such that:
* - Each substring is even length
* - Each substring only contains only 1s or only 0s
* You can change any character in s to 0 or 1
* Return the minimum number of characters you need to change to make s beautiful
*/

#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
    public:
        int minChanges(string s) {
            // greedy solution
            int count = 0;
            for(int i = 0; i < s.size() - 1; i += 2) {
                if (s[i] != s[i + 1]) count++;
            }
            return count;
        }
};

// Runtime: O(n)
// Space: O(1)