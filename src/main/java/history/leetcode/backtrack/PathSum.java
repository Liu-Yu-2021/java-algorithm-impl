package history.leetcode.backtrack;

import history.leetcode.tree.TreeNode;
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
        pathSum(root, sum, new ArrayList<Integer>());
        return res;
    }

    public void pathSum(TreeNode root, int sum, List<Integer> track) {

        track.add(root.val);
        // 题目说，到叶子节点, [1,2]没有从根到叶子节点的和为1的路径
        // [1,2] 1 返回值是 [] 而非 [[1]]
        // 因此单个根节点,不算,需要检测是否此时根节点, 根节点不算...
        if (getSum(track) == sum && root.left == null && root.right == null) {
            res.add(new ArrayList<>(track));
            return;
        }

        if ( root.left != null ) {
            // 修改 new ArrayList<>(track) >> track + track.remove(track.size()-1)
            // 减少对象的创建, 略微降低空间开销, 降低内存的消耗;
            pathSum(root.left, sum, track);
            //pathSum(root.left, sum, new ArrayList<>(track));
            track.remove(track.size()-1);
        }
        if ( root.right != null) {
            pathSum(root.right, sum, track);
            track.remove(track.size()-1);
        }
    }

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