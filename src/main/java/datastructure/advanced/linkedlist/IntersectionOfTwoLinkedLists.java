package datastructure.advanced.linkedlist;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 相交链表
 * <p>
 * 160. 相交链表
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 * <p>
 * 进阶：你能否设计一个时间复杂度 O(m + n) 、仅用 O(1) 内存的解决方案？
 */
public class IntersectionOfTwoLinkedLists {

    /**
     * 思路一: 记录存储过的节点, 判断 A 的节点在不在 B 中
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeV1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> headAVisitedNode = new HashSet<>();
        ListNode curNodeA = headA;
        while (curNodeA != null) {
            headAVisitedNode.add(curNodeA);
            curNodeA = curNodeA.next;
        }

        ListNode curNodeB = headB;
        while (curNodeB != null) {
            if (headAVisitedNode.contains(curNodeB)) {
                break;
            }
            curNodeB = curNodeB.next;
        }

        return curNodeB;
    }


    /**
     * 双指针:
     * 由于是单链表节点, 如果两者相交, 则最终尾部节点一定相同;
     * <p>
     * 设链表A独立长度为a、链表B独立长度为b, 公共长度为 c
     * - len(A) = a+c
     * - len(B_ = b+c
     * <p>
     * 则 A 指针走 a+c 步, B 指针走 b+c 步
     * 如果让 A 指针之后再走一段 b, 让 B 再走一段 a,
     * 则两者走的路程相等
     * <p>
     * 如果 c > 0, 则两者会在相交节点相遇
     * 如果 c = 0, 则两者最终会在 NULL 处相遇, 则可判断对应是否相交
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode curNodeA = headA;
        boolean switchA = false;

        ListNode curNodeB = headB;
        boolean switchB = false;

        while (curNodeA != curNodeB) {
            if (curNodeA == null) {
                if (!switchA) {
                    switchA = true;
                    curNodeA = headB;
                }
            } else {
                curNodeA = curNodeA.next;
            }

            if (curNodeB == null) {
                if (!switchB) {
                    switchB = true;
                    curNodeB = headA;
                }
            } else {
                curNodeB = curNodeB.next;
            }
        }

        return curNodeA;
    }


    @Test
    public void testGetIntersectionNode() {
        ListNode headA = new ListNode(3, null);
        ListNode headB = new ListNode(2, headA);

        assert getIntersectionNodeV1(headA, headB).equals(headA);
        assert getIntersectionNodeV2(headA, headB).equals(headA);
    }


}