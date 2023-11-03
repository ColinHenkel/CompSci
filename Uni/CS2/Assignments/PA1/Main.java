import java.util.*;

            /* COP3503 Assignment 1 */
/* This program is written by: Colin James Henkel */

public class Main {
    public static Scanner in = new Scanner(System.in);

    // finding candidate pair in sorted array
    public static Pair getCandidatePair(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;

        while (left < right) {
            int currentSum = A[left] + A[right];
            if (currentSum == target) {
                if(A[left] > A[right])
                    return new Pair(A[right], A[left]);
                else
                    return new Pair(A[left], A[right]);
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new Pair(0, 0);
    }

    // finding candidate pair in unsorted array
    public static Pair unsortedCandidatePair(int[] A, int target) {
        HashSet<Integer> set = new HashSet<>();
        Pair result = new Pair(0, 0);
        int minDiff = Integer.MAX_VALUE;
        
        for (int point : A) {
            int complement = target - point;
            if (set.contains(complement)) {
                int currentDiff = Math.abs(point - complement);
                if (currentDiff < minDiff) {
                    result = new Pair(Math.min(point, complement), Math.max(point, complement));
                    minDiff = currentDiff;
                }
            }
            set.add(point);
        }
        return result;
    }

    public static void main(String[] args) {
        // read number of test cases
        int k = in.nextInt();

        // read input for each test case and find and print the candidate pair for each case
        for (int testCase = 1; testCase <= k; testCase++) {
            int sortedStatus = in.nextInt();
            int n = in.nextInt();
            int[] points = new int[n];

            // game points
            for (int i = 0; i < n; i++) {
                points[i] = in.nextInt();
            }
            // target points
            int target = in.nextInt();
            
            // find candidate pair or lack thereof
            Pair result;
            if (sortedStatus == 1) { // array is sorted, call external method for no hashset
                result = getCandidatePair(points, target);
            } else {
                result = unsortedCandidatePair(points, target); // array is unsorted, call external method with hashset
            }

            // print result
            if (result.first != 0 || result.second != 0) {
                System.out.println("Test case #" + testCase + ": Play the games with " + result.first + " points and " + result.second + " points to finish your " + target + " points.");
            } else {
                System.out.println("Test case #" + testCase + ": Cannot finish your " + target + " points.");
            }
        }
        
        in.close();
    }
}

// class for candidate pairs
class Pair {
    int first, second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
