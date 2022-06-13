package algorithm.basic.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {


    @Test
    public void testQuickSort(){
        int[] numArr = new int[]{20, 15, 30, 43, 7, 78, 93, 13, 22, 35};

        System.out.println(Arrays.toString(numArr));
        quickSort(numArr, 0, numArr.length - 1);
        System.out.println(Arrays.toString(numArr));
    }

    /**
     * Lomuto 快排: 小堆、pivotIdx、大堆
     * @param arr
     * @param startIdx
     * @param endIdx
     */
    public static void quickSort(int[] arr, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return;
        }

        int pivotIdx = partition(arr, startIdx, endIdx);
        quickSort(arr, startIdx, pivotIdx - 1);
        quickSort(arr, pivotIdx + 1, endIdx);
    }

    /*
    分片排序

    left 指针左侧: 比 pivot 小(含相等)的值
    right 指针右侧: 比 pivot 大(含相等)的值
    left、right 重合, 代表已经分完,根据 pivot, 交换基准值与 指针的边界,
        这样形成了局部的小排列: 小 < pivot < 大

    [pivot的位置正好是left、right指针重合处]
     */
    private static int partition(int[] arr, int startIdx, int endIdx) {
        int pivot = arr[startIdx];
        int left = startIdx;
        int right = endIdx;

        while (left != right) {
            //比较成功,指针向中间靠拢
            while (right > left && arr[right] > pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            // left、right 两者均比较失败, 对应值【交换】
            int tmpInt = arr[left];
            arr[left] = arr[right];
            arr[right] = tmpInt;
        }
        // left=right, 交换基准值与 指针的边界, 这样形成了局部的小排列: 小 < pivot < 大
        // [pivot的位置正好是left、right指针重合处]
        arr[startIdx] = arr[left];
        arr[left] = pivot;

        return left;
    }

}
