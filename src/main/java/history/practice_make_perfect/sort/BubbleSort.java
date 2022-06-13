package history.practice_make_perfect.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author 74281
 * @create 2020/10/06
 * @description: 冒泡排序
 * 要点:
 *      外层循环 i=0; i < nums.length
 *      内部循环 j=0; j < nums.length-i-1
 */
public class BubbleSort {

    public static void bubbleSort(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        for (int i = 0; i < nums.length; i++) { //nums.length
            for (int j = 0; j < nums.length -i-1; j++) { //nums.length-i-1
                if (nums[j] > nums[j + 1]) { // swap
                    swap(nums, j, j + 1);
                }
            }
        }

    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    @Test
    public void testBubble(){
        int[] nums = new int[]{1,10,11,3,8,9};
        System.out.println(Arrays.toString(nums));
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }


}
