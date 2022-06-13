package history.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 74281
 * @create 2020/09/16
 * @description: 剑指 Offer 55 - I. 二叉树的深度
 *
输入一棵二叉树的根节点，求该树的深度。
从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class LengthBinaryTree {
    /*
    【深度优先策略】
    求树的深度,一般需要每遍历一层计数

    如果是三个节点的树
        if (root == null){ return 0;}

        int leftLen = maxDepth(root.left);    == 1
        int rightLen = maxDepth(root.right);  == 1

        len = getMax(leftLen, rightLen) + 1;  == 2

     函数的返回值 len >> 一定是树的深度
     */
    int len;

    public int maxDepth(TreeNode root) {
        if (root == null){ return 0;}

        int leftLen = maxDepth(root.left);
        int rightLen = maxDepth(root.right);

        len = getMax(leftLen, rightLen) + 1;

        return len;
    }

    public int getMax(int a, int b){
        return Math.max(a, b);
    }

    /*
    广度优先策略 / 层序遍历
     */
    public int maxDepthLevel(TreeNode root) {
        if ( root == null ){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        int len = 1;
        int count;
        TreeNode node;

        // 外层循环一次,代表遍历了一层
        while ( !queue.isEmpty() ){
            count = queue.size();

            while ( count > 0 ){
                node = queue.poll();

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                count--;
            }
            len++;
        }
        return len;
    }

}
