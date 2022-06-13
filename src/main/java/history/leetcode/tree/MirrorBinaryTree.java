package history.leetcode.tree;

import org.junit.Test;

/**
 * @author 74281
 * @create 2020/09/16
 * @description: 镜像二叉树
 *
 * https://leetcode-cn.com/problems/er-cha-shu-de-jing-xiang-lcof/
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 */
public class MirrorBinaryTree {
    /*
    算法可分成两部分,
    ① 给 int[] 如何创建树
        >> 但 leetcode 给的函数参数是 Treenode... 相当于不用自己处理了
        >> 但还是需要自己手写,练一练, 参见下面函数

    ② 拿到树 root, 如何做镜像? >> 反转 / 交换
     */
    public TreeNode mirrorTree(TreeNode root) {
        if ( root == null){
            return null;
        }

        TreeNode leftNode = mirrorTree(root.left);
        TreeNode rightNode = mirrorTree(root.right);

        root.left = rightNode;
        root.right = leftNode;

        return root;
    }

    @Test
    public void testCreateBinaryTree(){
        /*
        输入是完全二叉树
            输入：root = [4,2,7,1,3,6,9]
            输出：[4,7,2,9,6,3,1]
        输入是非完全二叉树,空缺节点补null
            输入：root = [4,2,7,1,null,null,null]
            输出：[4,7,2,null,null,null,1]
         */
        int[] rootSimple = new int[]{4,2,7,1,3,6,9};
        Integer[] rootDifficult = new Integer[]{4,2,7,1,null,null,null};
        // 因为基本数据类型, 没有 null, 转为包装类, 可以写 null

    }

}
