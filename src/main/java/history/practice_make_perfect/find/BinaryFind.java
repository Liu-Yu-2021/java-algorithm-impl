package history.practice_make_perfect.find;

import org.junit.Test;

/**
 * @author 74281
 * @create 2020/10/06
 * @description: 二分查找练习
 */
public class BinaryFind {

    /**
     * @param nums   待查询的数组
     * @param target 带查询的值
     * @return 返回的索引值, 查不到返回 -1
     * ( 该值若重复出现,返回一个索引就行 )
     */
    public static int binaryFind(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;

        // 要点一: 防止相加溢出
        int mid = left + (right - left) / 2;

        // 要点二:循环条件 + 搜索区间(闭区间)
        while (left <= right) {
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                //闭区间要减一,因为 mid 搜索过了
                right = mid - 1;
            } else if (nums[mid] < target) {
                //闭区间要加一,因为 mid 搜索过了
                left = mid + 1;
            }

            // 要点三: 要更新 mid
            mid = left + (right - left) / 2;
        }
        return -1;
    }

    @Test
    public void testBinaryFind() {
        int[] nums = new int[]{1, 3, 8, 9, 10, 11};
        //int[] nums = new int[]{8,8};
        //int[] nums = new int[]{8};
        //int[] nums = new int[]{}; // nums.length = 0
        System.out.println(binaryFind(nums, 8));
    }


}
