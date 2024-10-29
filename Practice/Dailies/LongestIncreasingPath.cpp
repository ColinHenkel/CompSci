/*
* You are given a 0-indexed m x n matrix grid consisting of positive integers.
* You can start at any cell in the first column of the matrix, and traverse the grid in the following way:
* From a cell (row, col), you can move to any of the cells: (row - 1, col + 1), (row, col + 1) and (row + 1, col + 1) such that the value of the cell you move to, should be strictly bigger than the value of the current cell.
* Return the maximum number of moves that you can perform.
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Solution {
public:
    int maxMoves(vector<vector<int>>& grid) {
        // get dimensions of the grid
        int m = grid.size();    // number of rows
        int n = grid[0].size(); // number of columns
        
        int res = 0;
        
        // dp array stores the maximum number of moves possible to reach each cell 
        // in current column
        vector<int> dp(m);
        
        // iterate through each column from left to right
        for (int j = 1; j < n; ++j) {
            int leftTop = 0;
            bool found = false;
            
            // iterate through each row in current column
            for (int i = 0; i < m; ++i) {
                // cur will store the maximum moves to reach current cell
                int cur = -1;
                // store dp[i] for next iteration's leftTop
                int nxtLeftTop = dp[i];
                
                if (i - 1 >= 0 && leftTop != -1 && grid[i][j] > grid[i - 1][j - 1]) {
                    cur = max(cur, leftTop + 1);
                }
                if (dp[i] != -1 && grid[i][j] > grid[i][j - 1]) {
                    cur = max(cur, dp[i] + 1);
                }
                if (i + 1 < m && dp[i + 1] != -1 && grid[i][j] > grid[i + 1][j - 1]) {
                    cur = max(cur, dp[i + 1] + 1);
                }
                
                // update dp array for current cell
                dp[i] = cur;
                // update found flag
                found = found || (dp[i] != -1);
                // update leftTop
                leftTop = nxtLeftTop;
            }
            
            // can't reach = break
            if (!found) break;
            res = j;
        }
        
        // return result
        return res;
    }
};
