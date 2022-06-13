package datastructure.advanced.string;

import org.junit.Test;

/**
 * C语言内置函数 strStr()
 *
 * 在一个字符串中查询另一个字符串第一次出现的位置
 *
 * 思路一: 暴力解法 O(n^2)
 * 思路二: KMP 算法
 *        KMP 替代算法 - Rabin-Karp - 本质是比较 HashCode
 */
public class implIndexOf {

    /**
     * Rabin-Karp 算法 - 本质是比较 HashCode
     *
     * 子串匹配: 每个元素比较, 是 O(m) 时间复杂度, 如果有办法把这部分逻辑用 O(1) 时间复杂度, 则可以提高效率
     * 则让字符串转化为一个整数, 同时不同的字符串对应不同的整数, Hash 函数正好与之对应
     *
     * Rabin-Karp 算法
     * 先比较 hashCode, 如果 hashCode 不同, 则子串一定不同;
     *                 如果 hashCode 相同, 则子串可能相同(哈希冲突), 需要再匹配逐字符匹配一次
     *
     *
     * 最简单的 HashCode: 进制转换
     * abcdee = a * 31^4 + b * 31^4 + c * 31^3 + d * 31^2 + e * 31^0
     * 上面这数据可能过大, 可以对结果进行取模, 取模的数据越大, 哈希冲突概率越小
     *
     * Q: 为什么 hashcode 方法/ IDEA 自动生成的 hashcode 普遍使用 31
     * A: 31 是质数, 且  31 有个很好的性能，即用移位和减法来代替乘法，可以得到更好的性能 - 位运算 31 * i == (i << 5）- i
     *
     * abc
     * bcd = abcd - a
     */
    public Integer indexOfV2(String source, String target){
        if (source == null || target == null) {
            return -1;
        }

        int targetLen = target.length();
        if (targetLen == 0){
            return 0;
        }

        int base = 1000000;

        int targetHashCode = computeHashCode(target, base, 0, targetLen);

        int sourcePartialHashCode;
        for (int i = 0; i < source.length(); i ++){
            int end = Math.min(i + targetLen, source.length());
            sourcePartialHashCode = computeHashCode(source, base, i, end);
            if (targetHashCode == sourcePartialHashCode){
                return i;
            }
        }

        return -1;
    }

    private int computeHashCode(String target, int base, int start, int end){
        int targetHashCode = 0;
        for (int i = start; i < end; i++){
            targetHashCode = (targetHashCode * 31 + target.charAt(i)) % base;
        }

        return targetHashCode;
    }

    /**
     * 暴力解法
     * 额外空间浪费
     * @param source
     * @param target
     * @return
     */
    public int indexOfV1(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }

        for (int i = 0; i < source.length(); i++) {
            // 使用内置函数 subString、startsWith 显然是面试官不希望看到的, 而且手写 subString 等逻辑会有额外空间浪费
            // 同时 i + target.length() 可能会有下标越界问题
             if (isSubString(source, i, i + target.length(), target)) {
                return i;
            }
        }

        return -1;
    }

    public boolean isSubString(String source, int start, int end, String target) {
        if (source == null || start >= end) {
            throw new IllegalArgumentException("");
        }

        if (end > source.length()){
            return false;
        }

        boolean isSubString = true;
        for (int i = start; i < end; i++) {
            isSubString = isSubString && source.charAt(i) == target.charAt(i - start);
        }

        return isSubString;
    }


    @Test
    public void testIndexOf(){
        assert indexOfV1("abcdefg", "mmm") == -1;
        assert indexOfV1("abcdefg", "g") == 6;
        assert indexOfV1("abcdefg", "ab") == 0;
        assert indexOfV1("abcdefg", "bc") == 1;

        assert indexOfV2("abcdefg", "mmm") == -1;
        assert indexOfV2("abcdefg", "g") == 6;
        assert indexOfV2("abcdefg", "ab") == 0;
        assert indexOfV2("abcdefg", "bc") == 1;

    }

}
