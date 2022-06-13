package history.leetcode.sliding_window;

import org.junit.Test;

import java.util.*;

/**
 * @author 74281
 * @create 2020/10/09
 * @description:
 */
public class MinimumWindowSubstring {
    /**
     * @param s 待匹配的长串(一般如此)
     * @param t 子串
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     */
    public String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length() || ( s.length() == t.length() && !s.equals(t) )) {
            return "";
        }

        Map<Character, Integer> window = new HashMap<>(); // 窗口内相关元素的计数
        Map<Character, Integer> need = new HashMap<>();   // 所需子串的元素计数
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left  = 0;
        int right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;

        while ( right < s.length() ){
            char c = s.charAt(right); // c 将移入窗口
            right++; // 右移窗口
            //窗口内数据操作
            if ( need.containsKey(c) ){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if ( window.get(c).equals(need.get(c)) ){
                    valid++; // 某个元素已经凑齐, 当然下次还能 window[c]++, 但不计入 valid
                }
            }

            // debug 输出的位置
            //System.out.printf("window: [%d, %d)\n", left, right);

            // 当所有元素已经凑齐, 所需子元素：windows 元素 >= need 元素
            while ( valid == need.size() ) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗口的字符
                char d = s.charAt(left);
                // 左移窗口
                left++;
                // 进行窗口内数据的一系列更新
                if ( need.containsKey(d) ) {
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                        // valid-- >> 某个元素上 window.count == need.count
                    }
                    window.put(d, window.get(d)-1);
                }
            }
        }
        //System.out.printf("len:%d, start=%d, end=%d\n", len, start, start+len);
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len);
    }


    @Test
    public void testWindowSubstring(){
        MinimumWindowSubstring test = new MinimumWindowSubstring();
        String s =  "AB";
        //String s = "ADOBECODEBANCDE";
        String t = "A";
        //String t = "ABC";
        System.out.println(test.minWindow(s, t));
    }

    @Test
    public void testToArray(){
        List<Integer> res = new ArrayList<Integer>();
        res.add(1);
        res.add(2);
        System.out.println(Arrays.toString(res.toArray()));
    }

}
