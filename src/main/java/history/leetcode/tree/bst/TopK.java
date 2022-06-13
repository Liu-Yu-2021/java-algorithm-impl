package history.leetcode.tree.bst;

/**
 * @author 74281
 * @create 2020/09/21
 * @description: 剑指 Offer 54. 二叉搜索树的第k大节点
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 *
 */
public class TopK {
    /**
     *
     * @param root
     * @param k
     * @return
     *
     * BST 中序遍历是有序的,从小到大 ( left、val、right )
     * 从大到小就是 ( right、val、left )
     */
    int count = 0;
    int topVal = 0;
    public int kthLargest(TreeNode root, int k) {
        if ( root == null ){ return 0;}

        kthLargest(root.right, k);

        count++;
        if ( count == k ){
            topVal = root.val;
        }

        kthLargest(root.left, k);

        return topVal;
    }
}
