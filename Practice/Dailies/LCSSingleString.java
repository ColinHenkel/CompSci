import java.util.*;

public class LCSSingleString {
    public static void main(String[] args) {
        // main method to test maxLengthBetweenEqualCharacters
        String s = "abcdefga";
        System.out.println(maxLengthBetweenEqualCharacters(s));
    }

    public static int maxLengthBetweenEqualCharacters(String s) {
        // Given a string s, return the length of the longest substring between two equal characters, excluding the two characters.
        // If there is no such substring return -1.
        // A substring is a contiguous sequence of characters within a string.
        // Example 1:
        // Input: s = "aa"
        // Output: 0
        // Explanation: The optimal substring here is an empty substring between the two 'a's.
        // Example 2:
        // Input: s = "abca"
        // Output: 2
        // Explanation: The optimal substring here is "bc".
        // Example 3:
        // Input: s = "cbzxy"
        // Output: -1
        // Explanation: There are no characters that appear twice in s.

        // hash implementation
        // O(n) time complexity, O(n) space complexity
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c))
                map.put(c, i);
            else
                maxLen = Math.max(maxLen, i - map.get(c) - 1);
        }
        return maxLen;
    }
}
