/*
* Given an integer num you can swap two digits at most once to get maximum valued number
* Return the maximum valued number you can get
*/

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maximumSwap(int num) {
        // math & greedy approach
        string numStr = to_string(num);
        int n = numStr.size();
        vector<int> lastIdx(10, -1);
        for (int i = 0; i < n; i++) {
            lastIdx[numStr[i] - '0'] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int d = 9; d > numStr[i] - '0'; d--) {
                if (lastIdx[d] > i) {
                    swap(numStr[i], numStr[lastIdx[d]]);
                    return stoi(numStr);
                }
            }
        }
        
        return num;
    }
};