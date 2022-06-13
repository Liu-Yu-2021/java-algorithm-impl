package datastructure.advanced.linkedlist;

/**
 * 反转链表
 * <p>
 * 206.反转链表
 * https://leetcode.cn/problems/reverse-linked-list/
 * <p>
 * - 递归解法
 * - 循环解法
 */
public class ReverseLinkedList {


    /**
     * 双指针解法
     * <p>
     * 反转链表:
     * - 如果链表无元素, 直接返回即可
     * - 如果链表一个元素, 也不需要翻转
     * - 如果链表两个元素: head.next.next = head; head.next = null;
     * - 如果链表三个元素及以上: 就需要引入中间变量临时存储节点
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode preNode = null;
        ListNode curNode = head;
        ListNode tempNode;

        while (curNode != null) {
            tempNode = curNode.next;
            curNode.next = preNode;

            preNode = curNode;
            curNode = tempNode;
        }

        return preNode;
    }


    /**
     * 递归法
     */
    public ListNode revereListNode(ListNode head) {
        return reverseNode(null, head);
    }

    public ListNode reverseNode(ListNode pre, ListNode cur) {
        if (cur == null) {
            return pre;
        }

        ListNode tempNode = cur.next;
        cur.next = pre;
        pre = cur;
        cur = tempNode;

        return reverseNode(pre, cur);
    }


}
