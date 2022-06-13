package history.leetcode.greed;


import java.util.Arrays;

/**
 * @author 74281
 * @create 2020/10/07
 * @description: 学生分班算法
 * 穷举较复杂... 直接写回溯算法, 怕是顶不住;
 * 因此选择使用贪心算法,求取近似解
 *
 * 分班问题算法求解——动态规划 - SimonS的文章 - 知乎
 * https://zhuanlan.zhihu.com/p/20038650
 *
 */
public class studentScoreTwoClass {

    static int[] student_total = new int[]{
            635, 693, 223, 605, 186, 500, 322, 411, 175, 541,
            299, 185, 746, 525, 370, 217, 291, 188, 183, 290,
            168, 265, 253, 265, 300, 520, 470, 320, 398, 309,
            206, 188, 718, 514, 473, 199, 403, 166, 162, 644,
            515, 678, 530, 244, 331, 527, 395, 557, 314, 597,
            303, 570, 383, 242, 252, 686, 674, 617, 290, 482};

    static int[] student_class_a = new int[30];
    static int[] student_class_b = new int[30];


    void Distribute_Student(int[] np_student_class_a, int[] np_student_class_b, int[] np_student_score){
        int subAB = 0;

        Arrays.sort(np_student_score);

        for ( int i=0; i < np_student_score.length/2 ; i++ ){
            if ( subAB >= 0 ){
                np_student_class_a[i] = np_student_score[2*i];
                np_student_class_b[i] = np_student_score[2*i+1];
            }else{
                np_student_class_a[i] = np_student_score[2*i+1];
                np_student_class_b[i] = np_student_score[2*i];
            }
            subAB = np_student_class_a[i] - np_student_class_b[i];
        }
    }

    public static int getSum(int[] nums){
        int sum = 0;
        for ( int num: nums ){
            sum += num;
        }
        return sum;
    }

    public static void main(String[] args) {
        studentScoreTwoClass test = new studentScoreTwoClass();
        test.Distribute_Student(student_class_a, student_class_b, student_total);

        System.out.println(Arrays.toString(student_total));
        System.out.println(Arrays.toString(student_class_a));
        System.out.println(Arrays.toString(student_class_b));

        System.out.println(getSum(student_class_a));
        System.out.println(getSum(student_class_b));

    }
}
