package datastructure.advanced.linkedlist;

/**
 * 删除升序链表中的重复元素
 *
 * 83. 删除排序链表中的重复元素
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 */
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null){
            return null;
        }

        ListNode preNode = null;
        ListNode curNode = head;

        while (curNode != null){
            while (preNode != null && curNode != null && preNode.val == curNode.val){
                curNode = curNode.next;
                preNode.next = curNode;
            }

            preNode = curNode;
            curNode = curNode != null ? curNode.next : null;
        }

        return head;
    }
}
