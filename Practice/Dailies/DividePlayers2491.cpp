// postive integer array 'skill' of even length 'n'
// divide players into n/2 teams of size 2 such that total skill is equal
// chemistry is equal to product of skills of players in a team
// return sum of chemistry of all teams or -1 if there is no way to divide player skill equally

#include <stdio.h>
#include <stdlib.h>
#include <vector>
#include <iostream>
#include <algorithm>
#include <numeric>

using namespace std;

class Solution {
    public:
        long long dividePlayers(vector<int>& skill) {
            int n = skill.size(), n2 = n / 2;
            long long sum = accumulate(skill.begin(), skill.end(), 0LL);
            if(sum % n2) return -1;
            int teamSkill = sum / n2;
            sort(skill.begin(), skill.end());
            long long chemistry = 0;

            for(int i = 0; i < n2; i++) {
                long long l = skill[i], r = skill[n - i - 1];
                if(l+r != teamSkill) return -1;
                chemistry += l * r;
            }

            return chemistry;
        }
};

// can be sped up with a frequency counter implementation in place of sort
// would go from O(nlogn) ==> O(n)

class FrequencyImplementation {
    public:
        long long dividePlayers(vector<int>& skill) {
            int n = skill.size(), n2 = n / 2;
            long long sum = 0;
            int freq[100001] = {0}, xMax=1, xMin=100000;
            for(int x : skill) {
                sum += x;
                freq[x]++;
                xMax = max(xMax, x);
                xMin = min(xMin, x);
            }
            if(sum % n2) return -1;
            int teamSkill = sum / n2;

            long long chemistry = 0;
            int l, r;
            for(l=xMin, r=xMax; l<r; l++, r--) {
                long long fL=freq[l], fR=freq[r];
                if (l+r != teamSkill || fL != fR) return -1;
                chemistry += l * r * fL;
            }
            chemistry += (l == r && l*2 == teamSkill) ? freq[l]/2LL*l*l : 0LL;

            return chemistry;
        }
};