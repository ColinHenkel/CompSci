struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

#include <queue>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <iostream>

using namespace std;

/*
* You are given the root of a binary tree and positive integer k
* Level sum in the tree is the sum of the values of the nodes at the same level in the tree
* Return the kth largest level sum in the tree
* If there are fewer than k levels in the tree, return -1
*/

class Solution {
public:
    long long kthLargestLevelSum(TreeNode* root, int k) {
        // base case
        if (root == nullptr) return -1;

        // level order traversal
        queue<TreeNode*> q;
        q.push(root);
        unordered_map<int, long long> levelSum;
        int level = 0;

        while (!q.empty()) {
            int size = q.size();
            long long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode* node = q.front();
                q.pop();
                sum += node->val;
                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);
            }
            levelSum[level++] = sum;
        }

        // find kth largest level sum
        vector<long long> sums;
        for (auto& [_, sum] : levelSum) {
            sums.push_back(sum);
        }
        sort(sums.begin(), sums.end(), greater<long long>());
        return k <= sums.size() ? sums[k - 1] : -1;
    }
};

// Time Complexity: O(N)
// Space Complexity: O(N)

/* Explanation */
// We can use level order traversal to calculate the sum of each level
// We can store the sum of each level in a map
// We can then sort the sums in descending order and return the kth element
// If k is greater than the number of levels, we return -1

int main() {
    TreeNode* root = new TreeNode(1);
    root->left = new TreeNode(7);
    root->right = new TreeNode(0);
    root->left->left = new TreeNode(7);
    root->left->right = new TreeNode(-8);
    Solution s;
    cout << s.kthLargestLevelSum(root, 2) << endl; // 0
    return 0;
}
