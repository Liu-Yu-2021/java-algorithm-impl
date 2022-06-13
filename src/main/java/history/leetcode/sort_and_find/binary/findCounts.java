package history.leetcode.sort_and_find.binary;

import org.junit.Test;

/**
 * @author 74281
 * @create 2020/10/04
 * @description: https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 * 【面试官出这题的话肯定是想让你写二分的啦 其他没用】
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 */
public class findCounts {

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int mid = left + (right - left)/2;
        boolean isFind = false;

        while ( left <= right ){
            if ( nums[mid] == target ){
                isFind = true;
                break;
            }else if ( nums[mid] < target ){
                left = mid + 1;
            }else if ( nums[mid] > target){
                right = mid - 1;
            }
            mid = left + (right - left)/2;
        }

        if ( !isFind ){ return 0; }
        //find - left - bounds
        int leftIdx = mid;
        for (int i=mid; i>=0; i--){
            if ( nums[i] == target ){
                leftIdx = i; // 第一个非 target, 右侧一定是 target
            }else{ break; }
        }
        //find - right - bounds
        int rightIdx = mid;
        for (int i=mid; i<=nums.length-1; i++){
            if ( nums[i] == target ){
                rightIdx = i; // 第一个非 target, 左侧一定是 target
            }else{ break; }
        }
        return rightIdx-leftIdx+1;
    }

    @Test
    public void testSearch(){
        //int[] nums = new int[]{5, 7,7,8,8,10};
        int[] nums = new int[]{6,6};
        System.out.println(search(nums, 6));
    }


}
