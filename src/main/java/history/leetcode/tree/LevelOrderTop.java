package history.leetcode.tree;

import java.util.*;

/**
 * @author 74281
 * @create 2020/09/16
 * @description:
 * 从上到下层序遍历 102.
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * 自底向上的层次遍历 107.
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
 * 是102的变式, 只需要把 res 的添加方式...把每次的结果尾插就OK了...
 *
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 思考:
 * leetcode评论:
 * 很多朋友应该会选择将每次得到的层元素insert到结果的最前面，
 * 其实这样的方式很耗时间，时间复杂度为o(n^2)
 * 而直接push_pack到最后面，然后用reverse调换前后，更省时间，时间复杂度为o(n)
 */

public class LevelOrderTop {
    /*
    102. 从上往下
    107. 从下往上 >> res的添加方式改成头插,或者最后将102的res反转即可。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null)
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        queue.add(root);

        //内层循环执行结束, 代表完成一层的遍历
        //外层循环走了一次, 代表完成一层的遍历
        while(!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<Integer>();

            //内层循环的跳出: 证明一层数据的出队 + 下一层数据的入队
            while(count > 0){
                TreeNode node = queue.poll();
                list.add(node.val);

                if(node.left != null)
                    queue.add(node.left);
                if(node.right != null)
                    queue.add(node.right);
                count--;
            }
            // 107. 从底向上添加
            //res.add(0, list);
            res.add(list);
        }
        //107. 从底向上添加
        Collections.reverse(res);
        return res;
    }

    // 剑指 Offer 32 - III. 从上到下打印二叉树 III
    public List<List<Integer>> levelOrderReverse(TreeNode root) {
        if ( root == null){
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();

        queue.offer(root);

        int countOneLoop = 0;
        int lineIdx = -1;
        TreeNode node;

        while ( !queue.isEmpty() ){
            ArrayList<Integer> OneLevel = new ArrayList<>();

            countOneLoop = queue.size();
            // 这行代码写错位置...
            //node = queue.poll();

            while ( countOneLoop > 0 ){
                node = queue.poll();
                OneLevel.add(node.val);

                if ( node.left != null  ){
                    queue.add(node.left);
                }
                if ( node.right != null  ){
                    queue.add(node.right);
                }
                countOneLoop--;
            }
            lineIdx++;
            // lineIdx = 0,2 > 正序
            // lineIdx = 1,3 > 逆序 -> %2 == 1
            if ( lineIdx % 2 == 1 ){
                Collections.reverse(OneLevel);
            }
            res.add(OneLevel);
        }
        return res;
    }

}
