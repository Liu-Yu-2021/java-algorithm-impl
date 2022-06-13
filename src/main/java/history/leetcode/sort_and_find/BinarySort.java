package history.leetcode.sort_and_find;

import org.junit.Test;

/**
 * @author 74281
 * @create 2020/09/22
 * @description: 二分查找
 *
 * Q1: 左右区间的开闭问题 ?
 *              推荐: [left, right], 完整的闭区间
 * Q2: while 的循环结束条件
 *              推荐: while (left <= right)
 * Q3: mid 的计算方式、防溢出
 *              mid = left + (right-left)/2 (√)
 *              mid = (left+right)/2        (X) >> 易溢出
 * Q4: 条件判断推荐使用
 *              if - else if - else if 不要写 else >> 把所有条件都写出来, 逻辑清晰
 *
 * 【Tips】: 该写法的缺点 -> 不能查找边界值
 * 举例: nums = [1,2,2,2,3],  target 为 2, 返回值 2
 * 但 target = 2 的边界值: 左边界为 1, 右边界为 3
 *
 * 【如何找边界】: 要么修改二分查找 / 要么在此基础上, 上下遍历去找...
 */
public class BinarySort {
    /**
     * @param nums 待查询数组
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
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
            mid = left + (right - left) / 2;
        }
        return -1;
    }

    /**
     * 右边界的查找也是一样, 但这里就不写了...
     * 注意几点:
     *          ① 下标越界
     *          ② 终止条件
     * @param nums
     * @param target
     */
    public static int findLeftBounds(int[] nums, int target){
        int index = binarySearch(nums, target);
        int left = index;

        if ( left == 0 ){ return left; }
        // 找左边界... 反向循环
        for (int i = index; i >= 0; i-- ){
            if ( nums[i] == target ){
                left = i;
            }else if ( nums[i] != target ){
                break;
            }
        }
        return left;
    }


    @Test
    public void testBinarySearch(){
        int[] nums = new int[]{2, 2, 2, 2, 27, 32};
        //int[] nums = new int[]{0, 5, 8, 9, 13, 27, 32};
        int index = binarySearch(nums, 2); // 2
        System.out.println(index);
        int leftIdx = findLeftBounds(nums, 2);
        System.out.println(leftIdx);
    }

}
