package history.leetcode.tree;

/**
 * @author 74281
 * @create 2020/09/16
 * @description: 100. 相同的树
 *
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的
 */
public class EqualsTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 都为空的话，显然相同
        if (p == null && q == null) return true;
        // 一个为空，一个非空，显然不同
        /*
        这条的逻辑在于,
            如果 p,q 都 null, 则程序走不到这里
            如果 p,q 没有都null( 1个 null,或没有 null)
                1个null "或" -> 判为 true
         */
        if (p == null || q == null) return false;
        // 两个都非空，但 val 不一样也不行
        if (p.val != q.val) return false;

        // p 和 q 该比的都比完了
        return isSameTree(p.left, q.left)
                && isSameTree(p.right, q.right);
        
    }


}
