package history.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 74281
 * @create 2020/09/21
 * @description: 剑指 Offer 34. 二叉树中和为某一值的路径
 * https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 */
public class PathSum {
    /**
     * 典型的回溯式写法
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if ( root == null ){ return res; }
        pathSum(root, sum, new ArrayList<Integer>(), 0);
        return res;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> track, int total) {

        track.add(root.val);
        total += root.val;
        // 题目说，到叶子节点, [1,2]没有从根到叶子节点的和为1的路径
        // [1,2] 1 返回值是 [] 而非 [[1]]
        // 因此单个根节点,不算,需要检测是否此时根节点, 根节点不算...

        // if (getSum(track) == sum && root.left == null && root.right == null) {
        if ( total == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(track));
            //注意别忘了把最后加入的结点值给移除掉，因为下一步直接return了，
            //不会再走最后一行的remove了，所以这里在 return之前提前把最后
            //一个结点的值给remove掉。
            track.remove(track.size()-1);
            return;
        }


        if ( root.left != null ) {
            pathSum(root.left, sum,  new ArrayList<>(track), total);
            //track.remove(track.size()-1);
        }
        if ( root.right != null) {
            pathSum(root.right, sum,  new ArrayList<>(track), total);
            //track.remove(track.size()-1);
        }

        track.remove(track.size()-1);

    }

    /**
     * 如果每次使用 getSum, 对 List 进行迭代求和,
     * 平均的时间复杂度为 O(n), 如果每次把计算出的和,作为参数进行值传递, 就是 O(1)
     * RES: 确实降低了时间消耗, 从原来的平均 8-11 ms,降低至 3-4 ms
     * @param track
     * @return
     */
    private int getSum(List<Integer> track){
        int sum = 0;
        for (Integer num: track){
            sum += num;
        }
        return sum;
    }

    @Test
    public void testSum(){
        /*
        [5,4,8,11,null,13,4,7,2,null,null,5,1]
        22
         */

        TreeNode root = TreeNode.createBinaryTree(new Integer[]{5,4,8});
        System.out.println( pathSum(root, 9) );
    }
}