package history.leetcode.greed;

/**
 * @author 74281
 * @title 变态跳台阶 - 剑指offer
 * @url https://www.nowcoder.com/practice/22243d016f6b47f2a6928b4313c85387?tpId=13&&tqId=11162&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * @description 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
 *              求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class JumpSteps{

}

class JumpStepsSolution {
    /*
    1 - 1 - 1
    2 - 11 2 - 2
    3 - 111 12 21 3 - 4
    4 - 1111 112 13 121 211 22 31 4 - 8
     */
    public int JumpFloorII(int target) {
        return (int) Math.pow(2, target-1);
    }
}
