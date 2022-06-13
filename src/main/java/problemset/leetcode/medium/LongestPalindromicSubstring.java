package problemset.leetcode.medium;

import org.junit.Test;

/**
 * 最长回文子串
 * https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {

    /**
     * 基于动态规划:
     *  - 初始条件: 空字符串、单个字符串均为回文串
     *  - 转移方程: isPalindrome(str, i, j) = isPalindrome(str, i+1, j-1) && str[i] == str[j]
     *                 即[i, j] 区间字符串是否为回文串 =  [i+1, j-1] 区间是否为回文串 and [i] == [j]
     *
     *
     * @param inputStr
     * @return
     */
    public String longestPalindrome(String inputStr) {
        if (inputStr == null) {
            return null;
        }

        int strLen = inputStr.length();
        boolean[][] isPalindrome = new boolean[strLen][strLen];
        int resStart = strLen - 1;
        int resEnd = resStart;

        // 从状态转移方程、初始条件可判断, i 依赖 i+1, j 依赖 j-1, 如果从头开始 i 应该是从 length -1 开始, j 从 i 开始
        for (int i = 0; i < isPalindrome.length; i++) {
            isPalindrome[i][i] = true;
        }

        for (int i = 0; i < isPalindrome.length - 1; i++) {
            isPalindrome[i][i + 1] = inputStr.charAt(i) == inputStr.charAt(i + 1);
            if (isPalindrome[i][i + 1]) {
                resStart = i;
                resEnd = i + 1;
            }
        }

        for (int i = strLen - 1; i >= 0; i--) {
            for (int j = i + 2; j < strLen; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && inputStr.charAt(i) == inputStr.charAt(j);

                if (isPalindrome[i][j] && (j - i + 1) > (resEnd - resStart + 1)) {
                    resStart = i;
                    resEnd = j;
                }
            }
        }

        return inputStr.substring(resStart, resEnd + 1);
    }


    private boolean isPalindrome(String str, int left, int right){
        if (str == null){
            return false;
        }

        boolean charEquals = str.charAt(left) == str.charAt(right);
        // left=right 对应奇数长度回文串 如 aba、right=left+1 对应偶数回文串 abba
        if (left == right || right == left + 1){
            return charEquals;
        }else if (left < right){
            return charEquals && isPalindrome(str, left + 1, right - 1);
        }

        return false;
    }

    @Test
    public void testIsPalindrome(){
        // case 1 - 空字符串
        assert !isPalindrome(null, 0, 0);

        // case 2 - left == right
        assert isPalindrome("abcba", 2, 2);
        assert isPalindrome("abcba", 1, 1);
        assert isPalindrome("abcde", 4, 4);

        // case 3 - left < right
        assert isPalindrome("abcba", 0, 4);
        assert isPalindrome("abbbba", 0, 5);
        assert !isPalindrome("abhba", 0, 3);

    }

    @Test
    public void testLongestPalindrome(){
        assert longestPalindrome(null) == null;

        assert longestPalindrome("abcba").equals("abcba");
        assert longestPalindrome("abbbba").equals("abbbba");
        assert longestPalindrome("xbhba").equals("bhb");
        assert longestPalindrome("cbbd").equals("bb");

    }

}