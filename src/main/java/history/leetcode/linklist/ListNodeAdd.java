package history.leetcode.linklist;

import org.junit.Test;

/**
 * @author 74281
 * @create 2020/10/04
 * @description: https://leetcode-cn.com/problems/add-two-numbers/
 */
public class ListNodeAdd {

    /*
    输入：(2 -> 4 -> 3)
        + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode lastNode = null;
        ListNode headNode = null;

        int carry = 0;
        // 进位标志 - 每位的和 = node1.val+node2.val+carry
        // 如果加完了,最后 carry ！= 0, 还要新建一个节点
        while ( l1 != null || l2 != null ){
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int bitSum = carry + num1 + num2;

            if ( headNode == null ){
                headNode = lastNode = new ListNode(bitSum % 10);
            }else{
                lastNode.next = new ListNode(bitSum % 10 );
                lastNode = lastNode.next;
            }
            carry =  bitSum / 10;
            if ( l1 !=  null){
                l1 = l1.next;
            }
            if ( l2 !=  null){
                l2 = l2.next;
            }
        }
        if ( carry ==  1 ){
            lastNode.next = new ListNode(1);
            lastNode = lastNode.next;
        }
        return headNode;
    }

    @Test
    public void testNull(){
        ListNode node1 = null;
        ListNode node2 = null;
        System.out.println(node1 == node2);
    }


}
