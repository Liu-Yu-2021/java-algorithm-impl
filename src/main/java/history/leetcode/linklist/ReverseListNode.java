package history.leetcode.linklist;

/**
 * Definition for singly-linked list.
 */
public class ReverseListNode{
}

class ListNodeTestSolution {
    /**
     * 循环方式: 实现链表反转
     */
    /*
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp;
        while (cur != null){
            tmp = cur.next;

            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }
    */

    /**
     * 递归方式：实现反转链表
     *
     * 基线条件: 准备的节点是 null 时,退出
     * 一般条件:
     *          tmp = cur.next
     *          cur.next = pre
     */
    public ListNode reverseList(ListNode head) {
        if ( head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}