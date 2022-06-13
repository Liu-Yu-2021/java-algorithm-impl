package history.leetcode.sliding_window;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author 74281
 * @create 2020/10/09
 * @description: 滑动窗口,窗口内最大值
 */
public class maxSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        // k 可以等于0，数组也为空
        if ( k == 0 || nums.length == 0){
            return null;
        }else if ( k == 1 ){
            return nums;
        }

        // 窗口 = [left, right] 闭区间
        int left = 0;
        int right = k-1;

        int winMaxIdx = getMaxIndex(nums, left, right);
        int[] res = new int[nums.length-k+1];


        while ( right < nums.length ){
            if ( winMaxIdx < left ){
                winMaxIdx = getMaxIndex(nums, left, right);
            }else if ( nums[right] > nums[winMaxIdx] ){
                winMaxIdx = right;
            }
            res[left] = nums[winMaxIdx];
            left++;
            right++;
        }
        return res;
    }

    public static int getMaxIndex(int[] nums, int start, int end){

        int maxIdx = end;
        for( int i = start; i< end; i++){
            if ( nums[maxIdx] < nums[i]){
                maxIdx = i;
            }
        }
        return maxIdx;
    }

    @Test
    public void testMaxSlidingWindow(){
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow.maxSlidingWindow(nums, k)));
    }
}
