package history.datastructure;

import java.util.*;

/**
 * @author 74281
 * @create 2020/09/15
 * @description: 树的实现

 树,可由数组与链表实现;
    稀疏的二叉树,一般不适用数组存储, 冗余空间太多

 树的名词:
    根节点、内部节点、叶子节点
    节点的度(子树的数目)
    树的深度
    父节点、子节点、兄弟节点、左孩子、右孩子
    满二叉树
    完全二叉树
    BST - 二叉查找树 / 二叉排序树
    二叉树的自平衡 (红黑树、AVL树、树堆) -> 自平衡,为了避免树的一端过多,查找效率从 logn 退化到 n
    二叉堆、最大堆、最小堆、优先队列的实现
    前序遍历、中序遍历、后序遍历
    层序遍历
    深度优先(前、中、后) - 堆栈辅助实现
    广度优先(层序) - 队列实现
    赫夫曼树(带权路径和最小的是二叉树)

 【了解 ?】
    线索二叉树 / 树、二叉树的转化

 【定义】
1,BST - 二叉查找树 / 二叉排序树
它的定义是：一个二叉树中，
任意节点的值要大于等于左子树所有节点的值，
且要小于等于右边子树的所有节点的值。
 【注】是 "任何", 而非对称的节点
 */
class TreeNode {
   int data;
   TreeNode left;
   TreeNode right;
   TreeNode[] children = {left, right};

   TreeNode(int data) {
      this.data = data;
   }

   TreeNode(int data, TreeNode left, TreeNode right) {
      this.data = data;
      this.left = left;
      this.right = right;
   }

   @Override
   public String toString(){
      return "Node[data = "+this.data+"]";
   }
}

public class TreeImp {

   public static void main(String[] args) {
      /*
                 1
      2                    3
    4  5                null 6
null null null null

   前序:124536  >> 前序遍历的首位是根节点
   中序:425136  >> 中序遍历的根节点左:是左子树、右是右子树
   后序:452631
       */
      LinkedList<Integer> inputLists = new LinkedList<>(Arrays.asList(1,2,4,null,null,5,null,null,3,null,6));
      TreeNode root = createBinaryTree(inputLists);
      //preOrder(root);
      //midOrder(root);
      //postOrder(root);
      //System.out.println(levelOrderFromTop(root));
      //System.out.println(levelOrderFromBottom(root));
   }

   public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
      TreeNode node = null;
      if (inputList == null || inputList.isEmpty()){
         return null;
      }
      Integer data = inputList.removeFirst();
      if (data != null){
         node = new TreeNode(data);
         node.left = createBinaryTree(inputList);
         node.right = createBinaryTree(inputList);
      }
      return node;
   }

   public static void preOrder(TreeNode node){
      if (node == null){
         return;
      }
      //前序遍历
      System.out.println(node.data);
      preOrder(node.left);

      preOrder(node.right);
   }

   public static void midOrder(TreeNode node){
      if (node == null){
         return;
      }

      midOrder(node.left);
      //中序遍历
      System.out.println(node.data);
      midOrder(node.right);
   }

   public static void postOrder(TreeNode node){
      if (node == null){
         return;
      }

      postOrder(node.left);
      postOrder(node.right);
      //后序遍历
      System.out.println(node.data);
   }

   /*
   层序遍历: 使用队列辅助操作 - 从上往下遍历
    */
   public static List<Integer> levelOrderFromTop(TreeNode root){
      List<Integer> res = new ArrayList<>();
      Queue<TreeNode> queue = new LinkedList<TreeNode>();

      queue.offer(root);

      while ( !queue.isEmpty()){
         TreeNode node = queue.poll();
         res.add(node.data);
         //System.out.println(node.data);
         if (node.left != null) {
            queue.offer(node.left);
         }
         if (node.right != null) {
            queue.offer(node.right);
         }
      }

      return res;

   }

   /*
   取巧的方式: 与 FromTop 一致,但先不输出,
      拿一个容器存储数据,之后反转内部的顺序
      再遍历输出....
    */
   public static List<Integer> levelOrderFromBottom(TreeNode root){
      List<Integer> resFromBottom = levelOrderFromTop(root);
      Collections.reverse(resFromBottom);
      return resFromBottom;
   }


}
