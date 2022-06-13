package history.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author 74281
 * @create 2020/09/16
 * @description: 226. 翻转二叉树

输入
     4
   /   \
  2     7
 /  \  / \
1   3 6   9

输出
4
/   \
7     2
/ \   / \
9   6 3   1
 */



public class ReverseBinaryTree {
    /*
    ① 辅助思考
    如果是三个节点,
        根节点+左右子树,
        应该直接写
            Treenode left = root.left;
            Treenode right = root.right;
            root.left = right;
            root.right = left;
        前两行必须写...因为交换值,起码要保存一个中间变量

     ②函数的返回值 >> 传入的参数是 ROOT, 返回值最终应该是一个反转好的 ROOT 根节点
        因此需要有一句 return root;

    ③ 同时要排除特殊情况, 如 root == null, 可先判断
     */
    public TreeNode invertTree(TreeNode root) {
        if ( root == null ){
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    //利用前序遍历
    static class PreSolution {
        // 先序遍历--从顶向下交换
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            // 保存右子树
            TreeNode rightTree = root.right;
            // 交换左右子树的位置
            root.right = invertTree(root.left);
            root.left = invertTree(rightTree);
            return root;
        }
    }

    //利用中序遍历
    static class MidSolution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;

            invertTree(root.left); // 递归找到左节点

            TreeNode rightNode= root.right; // 保存右节点
            root.right = root.left;
            root.left = rightNode;
            // 递归找到右节点 继续交换 : 因为此时左右节点已经交换了,
            // 所以此时的右节点为root.left

            invertTree(root.left);
            return root;
        }
    }

    //利用后序遍历
    static class PostSolution {
        public TreeNode invertTree(TreeNode root) {
            // 后序遍历-- 从下向上交换
            if (root == null) return null;
            TreeNode leftNode = invertTree(root.left);
            TreeNode rightNode = invertTree(root.right);
            root.right = leftNode;
            root.left = rightNode;
            return root;
        }
    }

    //利用层次遍历
    static class CengSolution {
        public TreeNode invertTree(TreeNode root) {
            // 层次遍历--直接左右交换即可
            if (root == null) return null;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                TreeNode rightTree = node.right;
                node.right = node.left;
                node.left = rightTree;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }

}

