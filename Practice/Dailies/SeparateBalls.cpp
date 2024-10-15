/*
* You are given a 0-indexed binary string s of length n
* 1 and 0 represent black and white balls respectively
* In each step you can choose two adjacent balls and swap them
* Return minimum number of steps to group all black balls to the right and white balls to the left
*/

#include <iostream>
#include <stdlib.h>
#include <vector>

using namespace std;

class GreedySeparateBalls {
public:
    long long minimumSteps(string s) {
        // greedy approach
        int n = s.size();

        // count number of black balls to the right
        int blackToRight = 0;
        long long steps = 0;
        for (int i = 0; i < n; i++) {
            if (s[i] == '1') {
                blackToRight++;
            } else {
                steps += blackToRight;
            }
        }

        return steps;
    }
};