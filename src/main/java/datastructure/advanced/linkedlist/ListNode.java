package datastructure.advanced.linkedlist;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
        this(0, null);
    }

    ListNode(int val) {
        this(val, null);
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}