package problemset.leetcode.medium;

import java.util.HashMap;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 *
 * "abcabcbb" -> 3
 * "bbbb" -> 1
 * "pwwkew" -> 3
 *
 * 解题思路: 滑动窗口
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }

        // key - 字符, value - 索引
        int maxLen = 0;
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(left, map.get(character) + 1);
            }
            map.put(character, i);
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }
}
