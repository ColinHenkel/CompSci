/*
* You are given a 0-indexed 2D integer array pairs where pairs[i] = [start_i, end_i]
* An arrangement of pairs is valid if for every index i where 1<=i<=pairs.length, we have end_i-1 == start_i
* In other words, the end of the i-th pair is equal to the start of the (i+1)-th pair
* Return any valid arrangement of pairs
*/

#include <vector>
#include <algorithm>
#include <unordered_map>
#include <stack>
using namespace std;

class Solution {
public:
    vector<vector<int>> validArrangement(vector<vector<int>>& pairs) {
        // Hierholzer's Algorithm solution
        unordered_map<int, vector<int>> graph;
        unordered_map<int, int> inDegree;

        // build graph and track in/out degrees
        for (auto& pair : pairs) {
            graph[pair[0]].push_back(pair[1]);
            inDegree[pair[0]]++;
            inDegree[pair[1]]--;
        }

        // find start node
        int start = pairs[0][0];
        for (auto& [node, degree] : inDegree) {
            if (degree == 1) {
                start = node;
                break;
            }
        }

        // Hierholzer's Algorithm
        stack<int> st;
        st.push(start);
        vector<int> res;
        while (!st.empty()) {
            int curr = st.top();
            if (graph[curr].empty()) {
                res.push_back(curr);
                st.pop();
            } else {
                st.push(graph[curr].back());
                graph[curr].pop_back();
            }
        }

        // build result by reversing path
        vector<vector<int>> result;
        for (int i = res.size() - 1; i > 0; i--) {
            result.push_back({res[i], res[i - 1]});
        }
        return result;
    }
};

// Time Complexity: O(n)
// Space Complexity: O(n)