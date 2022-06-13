package history.practice_make_perfect.sort;

/**
 * @author 74281
 * @create 2020/10/06
 * @description: 堆排序
 */
public class HeapSort {

    public static void heapSort(int[] nums){
        for (int i = nums.length / 2; i >= 0; i--) {
            autoAdjust(nums, i, nums.length);
        }

        for ( int i = nums.length - 1; i > 0 ; i-- ) {
            // 删除 - 交换
            swap(nums, i, 0);
            // 自调整
            autoAdjust(nums, 0, i-1);
        }

    }

    private static void autoAdjust(int[] nums, int parentIdx, int length) {
        int tmp = nums[parentIdx];
        int childIdx = 2 * parentIdx + 1; // 默认左孩子最大

        while ( childIdx < length ){
            if ( nums[childIdx + 1] > nums[childIdx] ){
                childIdx++;//右子树更大
            }

            if ( tmp > nums[childIdx] ){
                break; //原父节点更大
            }
            nums[parentIdx] = nums[childIdx];
            parentIdx = childIdx;
            childIdx = 2 * childIdx + 1;
        }
        nums[parentIdx] = tmp;
    }

    private static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
