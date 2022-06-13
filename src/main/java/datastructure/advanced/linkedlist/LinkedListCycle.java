package datastructure.advanced.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * 判断链表是否有环
 *
 * 141.环形链表
 * https://leetcode.cn/problems/linked-list-cycle/
 *
 * 解题思路:
 *  ① 记录遍历过的节点 HashSet
 *  ② 快慢指针 - 双指针
 *
 */
public class LinkedListCycle {

    /**
     * 思路一: 记录遍历过的节点 HashSet
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public boolean hasCycleV1(ListNode head) {
        if (head == null){
            return false;
        }

        Set<ListNode> visitedNodeSet = new HashSet<>();
        ListNode curNode = head;

        while (curNode != null){
            if (visitedNodeSet.contains(curNode)){
                return true;
            }else {
                visitedNodeSet.add(curNode);
            }

            curNode = curNode.next;
        }

        return false;
    }

    /**
     * 思路二: 双指针 - 快慢指针
     * 快慢指针都从头结点出发:
     *      - 快节点、慢节点相遇, 则证明有环
     *      - 快节点提前结束了, 则证明无环
     * @return
     */
    public boolean hasCycleV2(ListNode head){
        boolean isCycle = false;

        if (head == null || head.next == null){
            return isCycle;
        }

        ListNode slowNode = head;
        ListNode fastNode = head;

        while (fastNode != null){
            slowNode = slowNode.next;
            fastNode = fastNode.next != null ? fastNode.next.next : null;

            if (fastNode == slowNode){
                isCycle = true;
                break;
            }
        }

        return isCycle;
    }
}
