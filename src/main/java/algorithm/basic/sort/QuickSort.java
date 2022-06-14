package algorithm.basic.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {


    @Test
    public void testQuickSort(){
        int[] numArr = new int[]{20, 15, 30, 43, 7, 78, 93, 13, 22, 35};
        int[] sortedArr = new int[]{7, 13, 15, 20, 22, 30, 35, 43, 78, 93};

        assert !Arrays.toString(numArr).equals(Arrays.toString(sortedArr));
        quickSort(numArr, 0, numArr.length - 1);
        assert Arrays.toString(numArr).equals(Arrays.toString(sortedArr));
    }

    /**
     *
    分片排序
        Q: 为什么 partition 按 pivot 分割, <= pivot 在左边, >= pivot 在右边,
           而不是单个方向等于 pivot
        A: 避免极端情况, 数组绝大多数都是一个元素, 会让分片不均匀, 退化接近于 O(n^2), 比如 1,0,1,1,1,1,1,2,22,2

    left  指针左侧: 比 pivot 小(含相等)的值
    right 指针右侧: 比 pivot 大(含相等)的值

    left、right 重合, 代表已经分完,根据 pivot, 交换基准值与 指针的边界,
        这样形成了局部的小排列: 小 < pivot < 大

    [pivot的位置正好是left、right指针重合处]
     * @param arr
     * @param startIdx
     * @param endIdx
     */
    public static void quickSort(int[] arr, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return;
        }

        int left = startIdx;
        int right = endIdx;
        int pivot = arr[(left + right) / 2];

        while (left <= right){
            while (left <= right && arr[left] < pivot){
                left++;
            }
            while (left <= right && arr[right] > pivot){
                right--;
            }

            if (left <= right){
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;

                left++;
                right--;
            }
        }


        quickSort(arr, startIdx, right);
        quickSort(arr, left, endIdx);
    }

}
