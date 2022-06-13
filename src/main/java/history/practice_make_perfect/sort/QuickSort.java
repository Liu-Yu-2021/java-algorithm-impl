package history.practice_make_perfect.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author 74281
 * @create 2020/10/06
 * @description: 快速排序
 */
public class QuickSort {

    public static int[] quickSort(int[] nums){
        return quickSort(nums, 0, nums.length-1);
    }

    public static int[] quickSort(int[] nums, int startIdx, int endIdx){
        // startIdx >= endIdx, return 终止递归非常关键
        if ( startIdx >= endIdx ){
            return nums;
        }
        int pivot = partiation(nums, startIdx, endIdx);
        quickSort(nums, startIdx, pivot-1);
        quickSort(nums, pivot+1, endIdx);
        return nums;
    }


    private static int partiation(int[] nums, int startIdx, int endIdx) {
        int pivot = nums[startIdx];
        // 这么做的理由是,如果使用传入的参数直接 ++, --, 最后没保留 pivot 的索引
        // 没办法交换 pivot、和 left、right 边界
        int left = startIdx;
        int right = endIdx;

        // 循环终止条件 - left = right = pivot 的索引值
        while ( left < right ){
            while ( right > left && nums[right] > pivot ){
                right--;
            }
            while ( left < right && nums[left] <= pivot ){
                left++;
            }
            // 一轮排序结束,需要调换不满足条件的两个值 - 最后一轮 left == right,也会交换,但没有影响
            swap(nums, left, right);
        }
        swap(nums, startIdx, left); // left == right
        return left;
    }

     

    private static void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void testQuickSort(){
        int[] nums = new int[]{1,10,11,3,8,9};
        System.out.println(Arrays.toString(nums));
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }


}
