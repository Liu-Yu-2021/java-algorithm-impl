package history.bst;

import leetcode.tree.bst.TreeNode;

import java.util.*;


/**
 * @author 74281
 * @create 2020/09/20
 * @description: 剑指 Offer 68 - II. 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * 二叉树、BST树
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 */
public class MostNearestParent {

    /**
     *
     * @param root 二叉树的根节点
     * @param p 节点1
     * @param q 节点2
     * @return 最近公共祖先
     *
     * 二叉树参见这个图
     * https://pic.leetcode-cn.com/0f72cfd7bb18b5310a619cbeb313f6c968fdf95d904bafed86018bd429725099-image.png
     *
     * 我们看到6和7公共祖先有5和3，但最近的是5。
     * 我们只要往上找，找到他们第一个相同的公共祖先节点即可，
     * 【但怎么找到每个节点的父节点呢，】
     * 【我们只需要把每个节点都遍历一遍，然后顺便记录他们的父节点存储在Map中。】
     * 我们先找到其中的一条路径，比如6→5→3，
     * 然后在另一个节点往上找，由于7不在那条路径上，我们找7的父节点是2，2也不在那条路径上，
     * 我们接着往上找，2的父节点是5，5在那条路径上，所以5就是他们的最近公共子节点。
     *
     *
     * DFS（Deep First Search）深度优先搜索
     * BFS（Breath First Search）广度优先搜索
     *
     * 可使用 BFS 降低遍历的数据量 -> 添加遍历至 p、q 即可
     * 且每次添加时,都是添加 node.left、 node.right, 方便记录父节点
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //记录遍历到的每个节点的父节点。 key: Node - value: ParentNode
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        parent.put(root, null);//根节点没有父节点，所以为空
        queue.add(root);

        //直到两个节点都找到为止。
        while ( !parent.containsKey(p) || !parent.containsKey(q) ) {
            //队列是一边进一边出，这里poll方法是出队，
            TreeNode node = queue.poll();
            if ( node.left != null ) {
                //[关键之处]左子节点不为空，记录下他的父节点
                parent.put(node.left, node);
                queue.add(node.left);
            }
            //右节点同上
            if (node.right != null) {
                parent.put(node.right, node);
                queue.add(node.right);
            }
        }
        
        Set<TreeNode> ancestors = new HashSet<>();
        //记录下p和他的祖先节点，从p节点开始一直到根节点。
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }
        //查看p和他的祖先节点是否包含q节点，如果不包含再看是否包含q的父节点……
        while ( !ancestors.contains(q) )
            q = parent.get(q);
        return q;
    }

    /**
     * 二叉排序树,查找最近祖先节点
     * @param root
     * @param p
     * @param q
     * @return
    1, 二叉树本身为空，root == null ，return root

    2, p.val == q.val ,一个节点也可以是它自己的祖先

    3, p.val 和 q.val 都小于 root.val
    (两个子节点的值都小于根节点的值，说明它们的公共节点只能在二叉树的左子树寻找）

    4, p.val 和 q.val 都大于 root.val
    (两个子节点的值都大于根节点的值，说明它们的公共节点只能在二叉树的右子树寻找）

    5, 如果上述的情况皆不满足，说明其公共节点既不在左子树也不在右子树上，只能为最顶端的公共节点，return root
    p.val < root.val && q.val > root.val 或 p.val > root.val && q.val < root.val
     */
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if ( root == null ){ return null; } // 1. root == null

        if ( q.val == p.val ){ return q; } // 2. q.val = p.val

        if ( p.val < root.val && q.val < root.val ) { // 3, 两者均左子树节点
            return lowestCommonAncestorBST(root.left, p, q);
        } else if ( p.val > root.val && q.val > root.val ) { // 4, 两者均右子树节点
            return lowestCommonAncestorBST(root.right, p, q);
        }
        // 5, 两者在 root两侧, 返回 root
        return root;
    }


}
