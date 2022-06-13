package history.leetcode.linklist;

import org.junit.Test;

/**
 * @author 74281
 * @create 2020/09/22
 * @description: 剑指 Offer 22. 链表中倒数第k个节点
 * https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 * 思路有二:
 *          ① 两次遍历,第一次遍历拿到链表长度, 第二次遍历计数确定位置
 *          ② 双指针(快慢指针), 快指针先多走K步,慢指针指向开头, 迭代,
 *              快指针到尾节点, 慢指针的就是答案
 */
public class GetKthFromEnd {
    // 双指针 - 快慢指针
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode quickNode;
        ListNode slowNode = head;
        ListNode iterNode = head;
        int countK = 0;
        while ( iterNode != null ){
            countK++;
            if ( countK == k ){
                quickNode = iterNode;
            }

            iterNode = iterNode.next;
            if ( countK > k ){
                slowNode = slowNode.next;
            }
        }
        return  slowNode;
    }


    /**
     *
     * @param head
     * @param n
     * @return
     * 删除第k个节点, 一般情况、
     *                  特殊情况 1个节点删除 n=1
     *                          n个节点删除 n => 删除头节点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if ( head == null ){
            return null;
        }

        ListNode deletePreNode = head;
        ListNode fastNode = head;
        for(int i=0; i<n; i++){
            fastNode = fastNode.next;
        }
        // n个节点删除 n => 删除头节点
        if ( fastNode == null ){
            head = deletePreNode.next;
            return head;
        }

        while ( fastNode.next != null ){
            fastNode = fastNode.next;
            deletePreNode = deletePreNode.next;
        }

        deletePreNode.next = deletePreNode.next.next;

        return head;
    }

    @Test
    public void testRemoveNth(){
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(4);

        ListNode resNode = removeNthFromEnd(node, 2);
        while ( resNode != null ){
            System.out.println(resNode.val);
            resNode = resNode.next;
        }
    }

}
