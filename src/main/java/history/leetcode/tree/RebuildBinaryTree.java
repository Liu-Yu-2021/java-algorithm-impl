package history.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author 74281
 * @create 2020/09/16
 * @description: 根据遍历输出序列, 还原二叉树
 * <p>
 * ① 层序遍历, 空节点使用 null占位
 * 输入是完全二叉树
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 * <p>
 * 输入是非完全二叉树,空缺节点补null
 * 输入：root = [4,2,7,1,null,null,null]
 * 输出：[4,7,2,null,null,null,1]
 */
public class RebuildBinaryTree {

    /*
    思路一: root 数组给予的方式, 其实跟生成树的层序遍历一致,
        写一个逆算法,
            层序遍历: 迭代树,把节点放入队列,迭代输出值
            逆算法: 把值放入队列或...,迭代/递归 生成树
    */

    /**
     * 思路二: 可使用层序遍历输出的特点
     * [1,2,3,4,5,6,7]
     * 左孩子索引 = 父节点索引*2 + 1
     * 右孩子索引 = 父节点索引*2 + 2
     * @param root
     */
    public static TreeNode createTreeLevel(Integer[] root) {
        if (root == null) {
            return null;
        }

        TreeNode[] nodes = new TreeNode[root.length];
        for (int i = 0; i < root.length; i++) {
            nodes[i] = new TreeNode(root[i]);
        }

        for (int i = 0; i < root.length / 2; i++) {
            nodes[i].left = nodes[i * 2 + 1];
            nodes[i].right = nodes[i * 2 + 2];
        }
        return nodes[0];

    }

    /*
    输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
    假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    剑指 Offer 07. 重建二叉树
     */
    //利用原理,先序遍历的第一个节点就是根。在中序遍历中通过根 区分哪些是左子树的，哪些是右子树的
    //左右子树，递归
    HashMap<Integer, Integer> map = new HashMap<>();//标记中序遍历 - 相当于 getIndex
    int[] preorder;//保留的先序遍历


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < preorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recursive(0,0,inorder.length-1);
    }

    /**
     * @param pre_root_idx  先序遍历的索引
     * @param in_left_idx  中序遍历的索引
     * @param in_right_idx 中序遍历的索引
     */
    public TreeNode recursive(int pre_root_idx, int in_left_idx, int in_right_idx) {
        //相等就是自己
        if (in_left_idx > in_right_idx) {
            return null;
        }
        //root_idx是在先序里面的
        TreeNode root = new TreeNode(preorder[pre_root_idx]);
        // 有了先序的,再根据先序的，在中序中获 当前根的索引
        int idx = map.get(preorder[pre_root_idx]);

        //左子树的根节点就是 左子树的(前序遍历）第一个，就是+1,左边边界就是left，右边边界是中间区分的idx-1
        root.left = recursive(pre_root_idx + 1, in_left_idx, idx - 1);

        //由根节点在中序遍历的idx 区分成2段,idx 就是根

        //右子树的根，就是右子树（前序遍历）的第一个,就是当前根节点 加上左子树的数量
        // pre_root_idx 当前的根  左子树的长度 = 左子树的左边-右边 (idx-1 - in_left_idx +1) 。最后+1就是右子树的根了
        //TODO 右子树的根索引 = 左子树根索引 + 左子树数目(idx)   pre_root_idx + 1 + (idx-1 - in_left_idx+1)
        root.right = recursive(pre_root_idx + 1 + idx, idx + 1, in_right_idx);

        return root;
    }


    @Test
    public void testNull() {
        ArrayList<Integer> numList = new ArrayList<>();
        System.out.println(numList.isEmpty()); // true
        System.out.println(numList == null); // false

        int[] nums = new int[5];
        System.out.println(nums); //地址
        System.out.println(Arrays.toString(nums)); // 初始值0的数组,长度5
    }

    public static void main(String[] args) {
        //TreeNode root = createTreeLevel(new int[]{1, 2, 3, 4, 5, 6, 7});
        //System.out.println(root.left.right);

//        TreeNode root = createTreeLevel(new Integer[]{1, 2, 3, 4, null, 6, 7});
//        System.out.println(root.left.right);
    }

}
