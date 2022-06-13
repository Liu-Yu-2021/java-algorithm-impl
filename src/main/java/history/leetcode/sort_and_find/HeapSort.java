package history.leetcode.sort_and_find;

import java.util.Arrays;

/**
 * @author 74281
 * @create 2020/10/04
 * @description: 堆排序
 * 二叉堆的特性:
 *          是完全二叉树,且具备一定的额外顺序
 *
 *          最大堆的堆顶, 是整个堆的最大元素 - 大顶堆：每个节点的值都大于或等于其子节点的值，在堆排序算法中用于升序排列
 *          最小堆的堆顶, 是整个堆的最小元素
 *
 * 堆排序算法:
 *          1, 从无序数组构建成二叉堆。从小打到排序, 构成最大堆; 从大到小排序, 构成最小堆;
 *          2, 循环删除堆顶元素, 替换到二叉堆的末尾, 调整堆, 以产生新的堆顶
 *
 * 复杂度:
 *         空间复杂度 O(1) - 没有开辟额外的空间
 *         时间复杂度 O(n*log2n)  - 最坏的时间复杂度 O(n*log2n)
 *         不稳定排序算法
 *
 *  Q1: 为什么建立一个二叉堆的时间为O(N)而不是O(Nlog(N))?
 *  https://www.zhihu.com/question/264693363/answer/291397356
 */
public class HeapSort {
    /**
     * @param array 待排序数组
     *
     * 1, 无序数组构建二叉堆
     * 2, 循环删除堆顶元素, 移到集合末尾, 调整堆, 产生新的堆顶
     */
    public static void heapSort(int[] array){
        // 1, 构建二叉堆
        // Q:为什么不从 0 -> len 或 len -> 0
        // A:该节点都是有子节点的,如 len=5, for(int i=2; i>=0; i--) ->  2、1、0 都有子节点
        for (int i = (array.length) / 2; i >= 0; i--) {
            downAdjust(array, i, array.length);
        }

        //System.out.println(Arrays.toString(array));
        // 2, 循环删除 + 自调整
        for ( int i = array.length - 1; i > 0 ; i-- ) {
            // 删除 - 交换
            swap(array, i, 0);
            // 自调整
            downAdjust(array, 0, i-1);
        }
        //System.out.println(Arrays.toString(array));

    }
    /**
     * @param array 待调整的堆
     * @param parentIdx 堆顶的索引
     * @param length 堆的长度       
     */
    private static void downAdjust(int[] array, int parentIdx, int length) {
        // 1, 保留父节点
        int tmp = array[parentIdx];
        int childIdx = 2 * parentIdx + 1; // 默认左孩子最大, = 左孩子索引

        while ( childIdx < length ){
            // 如果 右孩子 > 左孩子, childIdx = 右孩子索引
            if ( array[childIdx + 1] > array[childIdx] ){
                childIdx++;
            }
            
            if ( tmp >= array[childIdx] ){
                break;
            }
            array[parentIdx] = array[childIdx];
            parentIdx = childIdx;
            childIdx = 2 * childIdx + 1;
        }
        array[parentIdx] = tmp;
        
    }

    public static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,71, 66, 19, 99};
        System.out.println(Arrays.toString(nums));

        heapSort(nums);

        System.out.println(Arrays.toString(nums));
    }


}
