package history.leetcode.sort_and_find;

import java.util.Arrays;

public class MergeSort {


    /**
     * 合并两个有序数组, 归并排序子内容
     * @param A
     * @param m
     * @param B
     * @param n
     * 注意⚠️：A 的长度不一定恰好为 m+n, 但一定是大于等于 m+n， 因此数组的末尾索引应该使用 m+n-1, 而非 A.length-1
     */
    public void merge(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (k != -1) {
            if (i >= 0 && j >= 0) {
                if (A[i] >= B[j]) {
                    A[k] = A[i];
                    i--;
                } else {
                    A[k] = B[j];
                    j--;
                }
                k--;
            }else if ( i < 0 && j > 0){
                A[k] = B[j];
                k--;
                j--;
            }else if ( j < 0 && i > 0 ){
                break;
            }
        }
        System.out.println(Arrays.toString(A));
    }

    public static void main(String[] args) {
        int[] A = new int[6];
        A[0] = 0; A[1] = 1; A[2] = 3; A[3] = 5;
        int[] B = new int[]{4,9};
        new MergeSort().merge(A, 4, B, 2);
    }

}
