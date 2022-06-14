package history.leetcode.important;

import history.leetcode.tree.TreeNode;

import java.util.HashMap;

/**
 * @author 74281
 * @create 2020/09/17
 * @description: 剑指 Offer 07. 重建二叉树
 * https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 题解:
 *      前序遍历特点： 节点按照 [ 根节点 | 左子树 | 右子树 ] 排序，以题目示例为例：[ 3 | 9 | 20 15 7 ]
 *      中序遍历特点： 节点按照 [ 左子树 | 根节点 | 右子树 ] 排序，以题目示例为例：[ 9 | 3 | 15 20 7 ]
 *      根据题目描述输入的前序遍历和中序遍历的结果中都不含重复的数字，
 *      其表明树中每个节点值都是唯一的。

  根据以上特点，可以按顺序完成以下工作：

(1)
  前序遍历的首个元素即为根节点 root 的值；
  在中序遍历中搜索根节点 root 的索引 ,可将中序遍历划分为 [ 左子树 | 根节点 | 右子树 ] 。
  根据中序遍历中的左（右）子树的节点数量，可将前序遍历划分为 [ 根节点 | 左子树 | 右子树 ]
                前序: [ 3 | 9 2 | 20 15 7 ]
                中序：[2 9 | 3 | 15 20 7 ]

(2)子树特点： 子树的前序和中序遍历仍符合以上特点，以题目示例的右子树为例：
                前序遍历：[20 | 15 | 7]，中序遍历 [ 15 | 20 | 7 ] 。

(3) 依然可以如此切割...故,可递归实现操作

 * 作者：jyd
 * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-di-gui-fa-qin/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class RebuildTree {
    /*
    TODO 之前错误 - 虽然不清楚在错误基础如何修改, 但肯定的是我遗漏或错误使用了左右边界的索引
                    我只使用前序的左右范围索引, 但递归时参数难以传递对
                    参考 labuladong 的代码
                    函数的控制参数,
                        包含: 前序的start、end; 中序的 start、end; 根节点的前序索引
                    我忽略了中序的索引,但实际上两者是不对齐的

    输入：
    [1,2,3]  >>   [2,3] index:(1,2)
    [3,2,1]  >> [3,2]   index:(0,1)

    输出： [1,2,null,null,3]
    预期： [1,2,null,3]
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = new TreeNode();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        root = buildTree(preorder, inorder, root, map, 0,0,preorder.length-1, 0 , inorder.length-1);
        return root;
    }

    /**
     * @param node 当前递归的根节点
     * @param getInMap 查找根节点在中序的索引
     *
     * ① 函数是处理什么的? -> 处理 start, end 的前、中序子数组, 当前根节点赋值,递归...
     * ② 递归结束的条件  -> start = end 处理完当前节点的赋值, 结束
     */
    public TreeNode buildTree(int[] preorder, int[] inorder, TreeNode node, HashMap getInMap,int nodePreIdx , int preStart, int preEnd, int inStart, int inEnd){

        if ( preStart > preEnd || inStart > inEnd ){
            return null;
        }


        node = new TreeNode(preorder[preStart]);
        int inRoot = (int) getInMap.get(node.val);
        int leftNums = inRoot - inStart;

        // start = nodeIdx >> 子序列的左子树是空的

        node.left = buildTree(preorder, inorder, node.left, getInMap, nodePreIdx-1, preStart + 1, preStart+leftNums, inStart, inRoot-1);
        node.right = buildTree(preorder, inorder, node.right, getInMap, nodePreIdx+1, preStart+leftNums+1, preEnd, inRoot+1, inEnd );
        return node;
    }


    public static void main(String[] args) {
        RebuildTree test = new RebuildTree();
        //一般情况
        //int[] preorder = new int[]{3,9,20,15,7};
        //int[] inorder =new int[]{9,3,15,20,7};

        //根节点+只有左子树
        //int[] preorder = new int[]{1,2};
        //int[] inorder =new int[]{2,1};

        //根节点+只有右子树
        //int[] preorder = new int[]{1,2};
        //int[] inorder =new int[]{1,2};
        int[] preorder = new int[]{1,2,3};
        int[] inorder = new int[]{3,2,1};
        TreeNode root = test.buildTree(preorder, inorder);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.right);
        System.out.println(root.left.left);
        System.out.println(root.left.right);
    }

}
