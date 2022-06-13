package history.leetcode.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 74281
 * @create 2020/09/12
 * @url https://www.nowcoder.com/practice/57d85990ba5b440ab888fc72b0751bf8?tpId=13&&tqId=33257&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * @description: 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1，m<=n），
 * 每段绳子的长度记为k[1],...,k[m]。请问k[1]x...xk[m]可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 输入一个数n，意义见题面。（2 <= n <= 60）
 */

public class CutRope {

    public static void main(String[] args) {

        List<Integer> res = new ArrayList<>();

        CutRopeSolution test = new CutRopeSolution();
        System.out.println(test.cutRopeMath(15));
    }
}

class CutRopeSolution {

    /**
     *
     * @param target
     * @return res
     */
    /*
    【暂时有些费解】 - 关于长度不等分的情况

    剪绳子后面的数学原理

    Q1: 周长一定为n，这时候长length与宽width在什么情况下，达到面积s最大
    s = length * width
    在长度x=n/4的时候，S的面积最大, width = n/2 - x = n/2 - n/4 = n/4
    A: width = length 的时候 s最大

    Q2: 绳子长度为 n，分成 m 分，那先设每分长度为x, 份数m=n/x
    A: 结果就是 n/x个 x 相乘, f(x)=x^(n/x)

    求导可知 x = e, f(x) = max, 但由于 x 只能取整数,
    所以比较 f(2), f(3)  ==> f(3)>f(2)

    问题就回到了n/3的个数上面
        - 当n能被3整除的时候，乘积=3^(n/3)
        - 当n除3余1的时候，这时候发现多了一个1,
            这个1是不是很鸡肋，但是把前面的一个3拿出来，
            把这个一个1和前面一个3 分解为2和2，
            就变大了，所以乘积为 3^(n/3 - 1) * 4
        - 当n除3余2的时候，乘积为3^(n/3) * 2
     */
    public int cutRopeMath(int target) {
        if(target<=0) return 0;
        if(target==1 || target == 2) return 1;
        if(target==3) return 2;
        int m = target % 3;
        switch(m){
            case 0 :
                return (int) Math.pow(3, target / 3);
            case 1 :
                return (int) Math.pow(3, target / 3 - 1) * 4;
            case 2 :
                return (int) Math.pow(3, target / 3) * 2;
        }
        return 0;
    }




}
