package history.leetcode.tree.bst;

/**
 * @author 74281
 * @create 2020/09/21
 * @description: 538. 把二叉搜索树转换为累加树
 * https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 */
// TODO 待重写
public class SumBST {

    /**
     * 【BST的中序遍历就是从小到大, 那么反过来就是从大到小】
     * 以右->根->左的顺序遍历二叉树，
     * 将遍历顺序的前一个结点的累加值记录起来，和当前结点相加，得到当前结点的累加值。
     * @param root
     * @return
     */
    public int preSum = 0;
    public TreeNode convertBST(TreeNode root) {
        if ( root == null ){ return null; }

        convertBST(root.right);
        //第一次 root.val + 是右子树的底层,最大的那个
        root.val += preSum;
        preSum = root.val;
        convertBST(root.left);

        return root;
    }


}
