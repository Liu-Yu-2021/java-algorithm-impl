package history.leetcode.tree.bst;

/**
 * @author 74281
 * @create 2020/09/21
 * @description: 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 */
// TODO 待重写
public class JudgePostOrder {
    /*
    【单调栈实现】
    二叉搜索树是left < root < right的，后序遍历的顺序是left->right->root，
    乍一看，好像没有办法保证单调性，不过我们可以做一个变化，后序遍历的逆序是什么呢？

    root->right->left

    发现什么了吗？

    是的，这是换了一个方向的先序遍历，
    从root开始，先遍历右子树，再遍历左子树。
    怎么做到先root，然后right，最后left呢，只要我们反向遍历数组，这样我们就可以利用单调栈了

作者：burning-summer
链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/dan-diao-di-zeng-zhan-by-shi-huo-de-xia-tian/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

代码实现思路:
*/

    /**
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/solution/di-gui-he-zhan-liang-chong-fang-shi-jie-jue-zui-ha/
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return helper(postorder, 0, postorder.length - 1);
    }

    boolean helper(int[] postorder, int left, int right) {
        //如果left==right，就一个节点不需要判断了，如果left>right说明没有节点，
        //也不用再看了,否则就要继续往下判断
        if (left >= right)
            return true;
        //因为数组中最后一个值postorder[right]是根节点，这里从左往右找出第一个比根节点大的值，
        // 他后面的都是根节点的右子节点（包含当前值，不包含最后一个值，因为最后一个是根节点），
        // 他前面的都是根节点的左子节点
        int mid = left;
        int root = postorder[right];
        while (postorder[mid] < root)
            mid++; // 找到 左节点+1 的索引值
        int temp = mid;
        //因为 postorder[mid] 前面的值都是比根节点root小的，
        //我们还需要确定 postorder[mid]后面的值都要比根节点 root 大，
        //如果后面有比根节点小的直接返回 false
        while (temp < right) {
            if (postorder[temp++] < root)
                return false;
        }
        //然后对左右子节点进行递归调用
        return helper(postorder, left, mid - 1) && helper(postorder, mid, right - 1);
    }




}
