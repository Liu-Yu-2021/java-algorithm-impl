package history.practice_make_perfect.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author warma926
 * @create 2020/11/3
 * @description: 计数排序
 *          - 1, 适合于一定范围内的整数排序, 而取值范围内较大的情况下, 不适合使用
 *          - 2, 适用于整数的排序, 而非整数排序, 如浮点数等不适合使用
 *
 * 计数排序优化版 - 演示动画： https://www.cs.usfca.edu/~galles/visualization/CountingSort.html
 *
 */
public class CountSort {


    /**
     * @param arr 待排序的数组
     * 计数排序初级版
     *            - 只适合于仅有该字段的情况, 但实际情况往往不是, 还有其他的属性，需要区分同样的排序字段值的对应的整体类、字段
     *            - 计数数组仅记录「值对应的词频」
     * 计数排序优化版
     *            - 对初级版的问题进行了解决
     *            - 在初级版基础上, 计数数组记录「每个元素都加上之前元素的最大值」
     *            - 需要创建额外数组, 因为直接使用下标赋值, 数据会被覆盖
     */
    public static void countSort(int[] arr){
        int max = arr[0];
        int min = arr[0];
        for (int num: arr) {
            max = Math.max(max, num); //max = max > num ? max : num;
            min = Math.min(min, num);//min = min < num ? min : num;
        }

        // 计数桶 / 计数数组
        int[] countArr = new int[max-min+1];
        for (int num: arr) {
            countArr[num-min] += 1;
        }
        /*
        计数初级版 - 数组位置记录「词频」

        // System.out.println(Arrays.toString(countArr));
        // [2, 0, 0, 1, 0, 2, 1]
        int curIdx = 0;
        for (int i = 0; i < countArr.length; i++) {
            for (int j = 0; j < countArr[i]; j++){
                arr[curIdx+j] = min + i;
            }
            curIdx += countArr[i];
        }
        */

        // 计数排序优化 - 每个元素都加上当前位置之前的最大值
        int curSum = countArr[0];
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += curSum;
            curSum = countArr[i];
        }
        int[] sortedArr = new int[arr.length];
        for (int i=arr.length-1; i >= 0; i--){
            // countArr[arr[i]-min] 每次赋值后，要减1, 因为这样相同值的元素, 会分配不同的顺序, 不然只能覆盖
//           int idx = countArr[arr[i]-min] - 1;
//           countArr[arr[i]-min]--;

            int idx = countArr[arr[i]-min]-- - 1;
            sortedArr[idx] = arr[i];
        }
        System.out.println(Arrays.toString(sortedArr));
    }

    @Test
    public void testCountSort(){
        int[] arr = new int[]{1,4,1,6,7,6};
        CountSort.countSort(arr);
        // primary   ： [2, 0, 0, 1, 0, 2, 1]
        // high level:  [2, 2, 2, 3, 3, 5, 6]
        System.out.println(Arrays.toString(arr)); // [1, 1, 4, 6, 6, 7]

        Student s1 = new Student(1, "s1");
        Student s2 = new Student(1, "s1");
        Student s3 = new Student(4, "s1");
        Student s4 = new Student(6, "s1");
        Student s5 = new Student(6, "s1");
        Student s6 = new Student(7, "s1");
        Student[] students = new Student[]{s1,s2,s3,s4,s5,s6};

    }

}

class Student implements Comparable<Student>{
    int score;
    String name;

    public Student(int score, String name) {
        this.score = score;
        this.name = name;
    }
    public Student(){}

    @Override
    public String toString() {
        return String.format("Student[name=%s, score=%d]", name, score);
    }

    @Override
    public int compareTo(Student o) {
        return this.score - o.score >= 0 ? 1 : -1 ;
    }
}