package problemset.leetcode.medium;

public class AddTwoNumbers {


    /**
     * 两数之和
     * - 会存在进位、连续进位的情况;
     * - 会出现 l1、l2 不同长度的情况（l1 更长 / l2 更长)
     * - 会存在两数较长的情况, 超过 long 长度等
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;

        while (node1 != null && node2 != null){
            boolean needCarry = node1.val + node2.val >= 10;
            int sum = (node1.val + node2.val) % 10;
            node1.val = sum;
            node2.val = sum;

            carryListNode(node1, needCarry);

            node1 = node1.next;
            node2 = node2.next;
        }
        return node1 == null ? l2 : l1;
    }

    private void carryListNode(ListNode node, boolean needCarry){
        if (needCarry){
            if (node.next != null){
                boolean needCarryAgain =  node.next.val + 1 >= 10;
                node.next.val = (node.next.val + 1) % 10;
                carryListNode(node.next, needCarryAgain);
            }else{
                node.next = new ListNode(1, null);
            }
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, null)));
        ListNode l2 = new ListNode(9, new ListNode(9, null));

        System.out.println(l1);
        System.out.println(l2);
        new AddTwoNumbers().addTwoNumbers(l1, l2);
        System.out.println(l1);
    }
}
