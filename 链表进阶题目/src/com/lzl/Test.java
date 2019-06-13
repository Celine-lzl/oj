package com.lzl;
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}
class Node {
public int val;
public Node next;
public Node random;
public Node(int val) {}
public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
        }
};
public class Test {
    /*
    *  复制带随机指针的链表*/
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        // 遍历原链表的每一个节点，创建新节点
        // 把新结点插入到原节点的后面
        Node cur = head;
        while(cur != null){
            Node newNode = new Node(cur.val);
            // 把newNode插入到Cur后面
            newNode.next = cur.next;
            cur.next = newNode;
            // 让cur走向原链表的下一个节点
            cur = cur.next.next;
        }
        // 设置新结点的random
        cur = head;
        while(cur != null){
            Node newNode = cur.next;
            if(cur.random == null){
                newNode.random = null;
            }else{
                newNode.random = cur.random.next;
            }
            // 让cur走到下一个老节点
            cur = cur.next.next;
        }
        // 拆
        cur = head;
        Node rhead = head.next;
        while(cur != null){
            Node newNode = cur.next;
            cur.next = newNode.next;
            if(newNode.next != null){
                newNode.next = newNode.next.next;
            }
            cur = cur.next;
        }
        return rhead;
    }

    /*
    *  判断链表是否带环，带环返回环的入口点
    *  否则返回null
    *  数学公式及推导，相遇关系
    *  了解结论即可*/
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        do{
            fast = fast.next;
            // 快的每走一步都可能遇到空，要判断
            if(fast != null){
                fast = fast.next;
                slow = slow.next;
            }
            // 相遇点
        }while(fast != null && fast != slow);
        if(fast == null){
            return null;
        }
        ListNode p1 = head;
        ListNode p2 = slow;
        while(p1 != p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /*
     *  求两个链表的第一个公共节点
     *  长的链表长度-短的长度
     *  快的先走长度差步
     *  两个一起走，相遇就是第一个公共节点
     *  */
    private int getLength(ListNode head) {
        int length = 0;
        for (ListNode cur = head; cur != null; cur = cur.next) {
            length++;
        }
        return length;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int lenA = getLength(headA);
            int lenB = getLength(headB);
            ListNode longer = headA;
            ListNode shorter = headB;
            int diff = lenA - lenB;
            if(lenA < lenB){
                diff = lenB - lenA;
                longer = headB;
                shorter = headA;
            }
            for(int i = 0; i < diff; i++){
                longer = longer.next;
            }
            while(longer != shorter){
                longer = longer.next;
                shorter = shorter.next;
            }
            return longer;
        }
    }

