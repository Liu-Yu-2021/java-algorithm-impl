package algorithm.basic.seek;

import org.junit.Test;

/**
 * @author liuyu
 * @create 2022年05月01日
 * @title: 二分查找
 * <p>
 * Q1: 左右区间的开闭问题 - 推荐: [left, right], 完整的闭区间
 * Q2: while 的循环结束条件 - 推荐: while (left <= right)
 * Q3: mid 的计算方式、防溢出
 *          mid = left + (right-left)/2 (√)
 *          mid = (left+right)/2        (X) >> 易溢出
 * Q4: 条件判断推荐使用
 * if - else if - else if 不要写 else >> 把所有条件都写出来, 逻辑清晰
 *
 * 【Tips】: 该写法的缺点 -> 不能查找边界值
 * 举例: nums = [1,2,2,2,3],  target 为 2, 返回值 2
 * 但 target = 2 的边界值: 左边界为 1, 右边界为 3
 *
 * 【如何找边界】: 在此基础上, 上下遍历去找
 */
public class BinarySearch {

    /**
     * @param nums   待查询的有序数组 - 从小到大
     * @param target 目标值
     * @return 目标数据的索引, 找不到返回 -1
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = left + (right - left) / 2;

        while (left <= right) {
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
            mid = left + (right - left) / 2;
        }
        return -1;
    }


    @Test
    public void testBinarySearch() {
        int[] nums = new int[]{2, 2, 2, 2, 27, 32};
        assert binarySearch(nums, 2) == 2;


        int[] nums2 = new int[]{0, 5, 8, 9, 13, 27, 32};
        assert binarySearch(nums2, 32) == nums2.length - 1;
        assert binarySearch(nums2, 99) == -1;
    }
}
