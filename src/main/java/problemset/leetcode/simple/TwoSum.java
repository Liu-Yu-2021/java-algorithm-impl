package problemset.leetcode.simple;

import java.util.HashMap;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，
 * 请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。
 * 但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 */
public class TwoSum {

    /**
     * 遍历法: 时间复杂度 O(n^2), 空间复杂度 O(1)
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 哈希表: 以空间换时间
     * 时间复杂度 O(n)、空间复杂度 O(n)
     * <p>
     * - 思路一: 先构建哈希表, 再查询, 相当于遍历两边
     * - 思路二: 构建哈希表的同时查询(如下)
     */
    public int[] twoSum(Integer[] nums, Integer target) {
        // key = 数组元素值, value = 对应数组元素索引
        HashMap<Integer, Integer> numsMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numsMap.containsKey(target - num)) {
                return new int[]{numsMap.get(target - num), i};
            }
            numsMap.put(num, i);
        }
        return new int[0];
    }

}
