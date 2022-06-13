package history.leetcode.tree;

import java.util.Objects;

/**
 * @author 74281
 * @create 2020/09/17
 * @description:
 */
public class TreeNode {
    public Integer val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(Integer x) {
        val = x;
    }
    public TreeNode() {}
    public TreeNode(int x, TreeNode left, TreeNode right) {
        this.val = x;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString(){
        return String.format("TreeNode:[data = %d]", val);
    }

    public static TreeNode createBinaryTree(Integer[] root){
        // 按层序: [3,5,1,6,2,0,8,null,null,7,4]
        TreeNode[] nodes = new TreeNode[root.length];

        for (int i=0; i< root.length; i++){
            nodes[i] = new TreeNode(root[i]);
        }

        int maxIdx = root.length - 1;
        // 运行到上一层
        for (int i = 0; i < root.length/2 ; i++){
            int leftIdx = 2*i + 1;
            int rightIdx = leftIdx + 1;
            if ( leftIdx <= maxIdx ){ nodes[i].left = nodes[leftIdx]; }
            if ( rightIdx<= maxIdx){ nodes[i].right = nodes[rightIdx]; }
        }
        return nodes[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode node = (TreeNode) o;

        if (!Objects.equals(val, node.val)) return false;
        if (!Objects.equals(left, node.left)) return false;
        return Objects.equals(right, node.right);
    }

    @Override
    public int hashCode() {
        int result = val != null ? val.hashCode() : 0;
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
