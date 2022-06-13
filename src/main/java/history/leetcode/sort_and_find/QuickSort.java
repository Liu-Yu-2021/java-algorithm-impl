package history.leetcode.sort_and_find;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author 74281
 * @create 2020/09/18
 * @description: 快速排序 - 递归、非递归实现
 * - 1, 递归方式 Recursion -> 分为双边、单边的实现方式, 参见《漫画算法 - 小灰》
 * - 2, 非递归方式【重要】
 * TODO 绝大多数的递归逻辑,都可以使用堆栈完成。
 * TODO 递归,程序也是使用了方法调用栈, 栈中存储每一次方法调用的参数即可。
 *
 * 分治法
 *          可以转化为求 TOP K 问题
 *
 *
 */
public class QuickSort {
    public static int[] numArr = new int[]{20, 15, 4, 43, 7, 78, 93, 13, 22, 35};

    /*
    非递归的实现方式
        需要思考几个问题:
            1, 堆栈存什么信息
            2, 递归不用,应该要用循环, 使用什么循环,什么判断条件

     */
    public static int[] QuickSortNoRecursion(int[] arr){
        if ( arr == null ){ return null; }
        if ( arr.length == 0 ){ return new int[0]; }

        Stack<Integer> paramStack = new Stack<>();
        int startIdx = 0;
        int endIdx = arr.length-1;


        paramStack.push(startIdx);
        paramStack.push(endIdx);

        while ( !paramStack.isEmpty() ){
        endIdx = paramStack.pop();
        startIdx = paramStack.pop();

        int pivotIdx = partition(arr, startIdx, endIdx);
        /*
        QuickSortDouble(arr, startIdx, pivotIdx - 1);
        QuickSortDouble(arr, pivotIdx + 1, endIdx);
         */
        if ( startIdx != pivotIdx-1 ){
        paramStack.push(startIdx);
        paramStack.push(pivotIdx-1);}
        if ( pivotIdx+1 != endIdx){
        paramStack.push(pivotIdx+1);
        paramStack.push(endIdx);}
    }
        return arr;
    }


    @Test
    public void testQSStack() {
        QuickSort.QuickSortNoRecursion(numArr);
        System.out.println(Arrays.toString(numArr));
    }

    @Test
    public void testQSDouble() {
        QuickSort.QuickSortDouble(numArr, 0, numArr.length - 1);
        System.out.println(Arrays.toString(numArr));
    }


    public static int[] QuickSortDouble(int[] arr, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return arr;
        }

        int pivotIdx = partition(arr, startIdx, endIdx);
        QuickSortDouble(arr, startIdx, pivotIdx - 1);
        QuickSortDouble(arr, pivotIdx + 1, endIdx);
        return arr;
    }

    /*
    left指针左侧: 比 pivot 小(含相等)的值
    right指针右侧: 比 pivot 大的值
        比较成功,指针向中间靠拢 >> 两者比较失败, left、right 指针对应值不在该区域,【交换】

    left = right >> 已经分完,根据 pivot, 交换基准值与 指针的边界,
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
            // left、right两者均比较失败,
            // left、right 指针对应值不在该区域,【交换】
                /*
                分析: if left<right 要不要加上...
                答案:
                    不需要加,
                        如果 while 内层循环的某次跳出,是因为 left = right,
                        那么交换 left、right 的操作,仅仅是多运行了三行代码,
                        而实际是自己与自己交换,值不变;
                    若加上,每次需要判断一下, 判断的时间应该比交换的额外三行,时间要多;

                if ( left < right ){
                    int tmp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = tmp;
                }
                 */
            int tmpInt = arr[left];
            arr[left] = arr[right];
            arr[right] = tmpInt;
        }
        //此时 left=right, 交换基准值与 指针的边界,
        // 这样形成了局部的小排列: 小 < pivot < 大
        // [pivot的位置正好是left、right指针重合处]
        arr[startIdx] = arr[left];
        arr[left] = pivot;

        return left;
    }

}
