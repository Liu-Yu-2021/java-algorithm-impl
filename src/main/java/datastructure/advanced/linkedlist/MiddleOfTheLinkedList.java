package datastructure.advanced.linkedlist;

/**
 * 获取链表的中间结点
 * 如果有两个中间结点，则返回第二个中间结点。
 * <p>
 * 876. 链表的中间结点
 * https://leetcode.cn/problems/middle-of-the-linked-list/
 * <p>
 * 思路: 快慢指针
 */
public class MiddleOfTheLinkedList {


    /**
     * 自测case:
     * [1,2,3,4,5] -> 3
     * [1,2,3,4,5,6] -> 4
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slowNode = head;
        ListNode fastNode = head;

        while (fastNode != null && fastNode.next != null) {
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
        }

        return slowNode;
    }
}
