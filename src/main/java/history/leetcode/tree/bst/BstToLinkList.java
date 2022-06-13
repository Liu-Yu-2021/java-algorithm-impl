package history.leetcode.tree.bst;

import java.util.ArrayList;

/**
 * @author 74281
 * @create 2020/09/21
 * @description: 剑指 Offer 36. 二叉搜索树与双向链表
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 *
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 * 双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
 * 还需要返回链表中的第一个节点的指针。
 */
public class BstToLinkList {
    /**
     * BST 的中序遍历是有序的, 从小到大
     * 思路:
     *      ① 直接在中序遍历递归的过程中,操纵左右子树完成逻辑的实现
     *      ② 将遍历的结果保存到 ArrayList / Queue,再遍历修改指针的指向即可
     * @param root
     * @return
     */
    ArrayList<Node> nodes = new ArrayList<>();
    Node head;

    public Node treeToDoublyList(Node root) {
        inOrder(root);
        if (nodes.size() == 0) {
            return null;
        }

        for (int i=0; i < nodes.size(); i++){
            nodes.get(i).right = nodes.get(i + 1);
            nodes.get(i).left = nodes.get(i - 1);
        }
        head = nodes.get(0);
        int maxIdx = nodes.size()-1;

        nodes.get(maxIdx).right = nodes.get(0);
        nodes.get(0).left = nodes.get(maxIdx);

        return head;

    }

    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            inOrder(node.left);
        }
        nodes.add(node);
        if (node.right != null) {
            inOrder(node.right);
        }
    }

}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}


