package history.leetcode.tree.bst;

/**
 * @author 74281
 * @create 2020/09/22
 * @description: 面试题 04.02. 最小高度树
 * https://leetcode-cn.com/problems/minimum-height-tree-lcci/
 */
public class MinLength {
    /**
     * @param nums
     * @return
     * 思路:
     *      一个节点时, 空节点时, 直接就对应一个 BST 树
     *      两个节点时, 可对应 左+根、根+右
     *      三个节点时, 对应   左+根+右
     *      N个节点时 >> 三个节点
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = helper(nums, 0, nums.length-1);
        return root;
    }

    /**
     *
     * @param nums 传入数组
     * @param left 数组的左边界
     * @param right 数组的右边界
     * @return
     * input: [-10,-3,0,5,9]
     * output: [0,-3,9,-10,null,5]
     * code: [0,-10,5,null,-3,null,9] >> 对比题目的图,可知道, 题目是两个节点,优先以左子树构建
     * 我这个 code 都是右子树
     */
    public TreeNode helper(int[] nums, int left, int right){
        if ( left > right ){ return null; }

        int rootIdx;
        if ((left + right) % 2 == 0) {
            rootIdx = (left + right) / 2;
        } else {
            rootIdx = (left + right) / 2 + 1;
        }

        TreeNode root = new TreeNode(nums[rootIdx]);

        root.left = helper(nums, left, rootIdx-1);
        root.right = helper(nums, rootIdx+1, right);

        return root;
    }
}
