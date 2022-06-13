package history.datastructure;

import org.junit.Test;

/**
 * @author 74281
 * @create 2020/09/13
 * @description: 数据结构 - 链表的CRUD
 * <p>
 * 链表 - 内存存储: 物理上非连续的数据结构
 * - 使用特点: 适合频繁插入和删除
 * <p>
 * 链表的分类:
 * 单向链表
 * 双向链表
 */
public class LinkListImp {

    public ListNode head;
    public ListNode last;
    private int len = 0;//初始化为0

    // 构造器初始化 - 链表
    public LinkListImp(int data) {
        //ListNode[data = 0] -> ListNode[data = 0] ->
        // 由于整型数据是存在默认值的
        head = new ListNode(data, last);
        last = head;
        len++;
    }

    public int getData(int index) {
        return getNode(index).data;
    }

    public ListNode getNode(int index) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException("list of out range.");
        }
        int curCount = 0;
        ListNode iterNode = this.head;
        while (iterNode != null) {
            if (index == curCount) {
                break;
            }
            curCount++;
            iterNode = iterNode.next;
        }
        return iterNode;
    }


    // 头插法
    public void insertToHead(ListNode element) {
        element.next = head;
        head = element;
        len++;
    }

    public void insertToLast(ListNode element) {
        last.next = element;
        last = element;
        len++;
    }

    /**
     * @param element 待插入的节点
     * @param index   待插入节点的期望索引值
     *                index = 0 >> 插入头部
     *                index = len >> 插入尾部
     *                0~len 插入中间某点
     *                其余情况 throw IndexOutOfBoundsException
     */
    public void insertToIndex(ListNode element, int index) {
        if (index < 0 || index > len) {
            throw new IndexOutOfBoundsException("Index out of range!");
        } else if (index == 0) {
            insertToHead(element);
        } else if (index == len) {
            insertToLast(element);
        } else {
            /*
            while ((nextNode = nextNode.next) != null){
                curNodeIdx++;
            }
            第一次 while
                nextNode = head.next
                curNodeIdx = 1
                if index == 1 >> 此时 nextNode.next = element >> 相当于插在的第一个节点的后面
                与我们设想的不太一致, 我们希望插在第一个节点的前面,然后该节点作为新节点
             */
            int curNodeIdx = 0;
            ListNode nextNode = head;
            while (nextNode != null) {
                curNodeIdx++;
                if (curNodeIdx == index) {
                    element.next = nextNode.next;
                    nextNode.next = element;
                    len++;
                    break;
                }
                nextNode = nextNode.next;
            }
        }
    }

    /**
     * @param index 删除值对应的索引
     * @return retNode
     */
    public ListNode removeNode(int index) {
        ListNode retNode = null;
        if (index == 0) {
            retNode = head;
            head = head.next;
        } else {
            ListNode curNode = getNode(index);
            ListNode preNode = getNode(index - 1);
            preNode.next = curNode.next;
            retNode = curNode;
        }
        return retNode;
    }

    public StringBuilder IterLinkList() {
        ListNode IterNode = this.head;
        StringBuilder res = new StringBuilder();

        if (IterNode == null) {
            throw new NullPointerException("The LinkList is not defined.");
        }

        while (IterNode != null) {
            res.append(IterNode.toString());

            System.out.print(IterNode.toString());

            IterNode = IterNode.next;
        }
        System.out.println();

        return res;
    }


    @Test
    public void testInsert() {
        LinkListImp linkList = new LinkListImp(0);
        linkList.insertToLast(new ListNode(1));
        linkList.insertToLast(new ListNode(2));
        linkList.insertToLast(new ListNode(3));
        linkList.insertToHead(new ListNode(-1));

        linkList.insertToIndex(new ListNode(66), 2);

        linkList.IterLinkList();
    }

    @Test
    public void testGet() {
        LinkListImp linkList = new LinkListImp(99);
        linkList.insertToLast(new ListNode(10));
        linkList.insertToLast(new ListNode(20));
        linkList.insertToLast(new ListNode(30));
        linkList.insertToHead(new ListNode(-10));
        linkList.insertToIndex(new ListNode(66), 2);

        linkList.IterLinkList();

        System.out.println(linkList.getNode(1));
        System.out.println(linkList.getData(1));
    }

    @Test
    public void testRemove() {
        LinkListImp linkList = new LinkListImp(55);
        linkList.insertToLast(new ListNode(10));
        linkList.insertToLast(new ListNode(20));
        linkList.insertToLast(new ListNode(30));
        linkList.insertToHead(new ListNode(-10));
        linkList.insertToIndex(new ListNode(66), 2);

        linkList.IterLinkList();

        System.out.println("\nafter delete head node: " + linkList.removeNode(0));
        linkList.IterLinkList();

        System.out.println("\nafter delete index=1 node: " + linkList.removeNode(1));
        linkList.IterLinkList();

    }


}

class ListNode {
    /*
    由于data -> int默认值为0
    且基本数据类型无法赋值为 null
    同时我们希望初始值为 null
    因此改 int 为 Integer
    */
    public Integer data;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int data) {
        this.data = data;
    }

    public ListNode(int data, ListNode node) {
        this.data = data;
        this.next = node;
    }

    @Override
    public String toString() {
        return String.format("ListNode[data = %d] -> ", this.data);
    }
}
