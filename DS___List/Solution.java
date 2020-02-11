package DS___List;
import java.util.HashSet;
import java.util.Set;
import java.util.*;
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
public class Solution {
    public static void main(String[] args) {
        ListNode head = new ListNode(6);ListNode head1 = new ListNode(5);ListNode head2 = new ListNode(4);
        ListNode head3 = new ListNode(3);ListNode head4 = new ListNode(2);ListNode head5 = new ListNode(1);
        head.next = head1;  head1.next = head2; head2.next = head3;
        head3.next = head4; head4.next = head5;
        partition(head, 4);
    }

    /**
     * 复制带随机指针的链表
     */
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node cur = head;
        // 复制原有链表不带随机指针的部分
        while(cur != null){
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = cur.next.next;
        }
        // 复制random
        cur = head;
        while(cur != null){
            Node newNode = cur.next;
            if(cur.random == null){
                newNode.random = null;
            }else{
                newNode.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // 拆分
        cur = head;
        Node res = cur.next;
        while(cur != null){
            Node newNode = cur.next;
            cur.next = newNode.next;
            if(newNode.next != null){
                newNode.next = newNode.next.next;
            }
            cur = cur.next;
        }
        return res;
    }


    /**
     * 环形链表II
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        do{
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
                slow = slow.next;
            }
        }while(fast != null && fast != slow);
        // 跳出while循环时有可能fast为空了，也有可能fast = slow了，也就是相遇了
        if(fast == null){
            return null; // 说明链表没有环
        }
        ListNode tmp = head;
        while(tmp != fast){
            fast = fast.next;
            tmp = tmp.next;
        }
        return fast;
    }

    //
    public ListNode detectCycle2(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode cur = head;
        Set<ListNode> set = new HashSet();
        while(cur != null){
            if(set.contains(cur)){
                return cur;
            }
            set.add(cur);
            cur = cur.next;
        }
        return null;
    }

    /**
     * 141 环形链表
     */
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
                slow = slow.next;
            }
            if(fast == slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 160 相交链表
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cura = headA;
        ListNode curb = headB;
        // 如果二者相交的话，我们让两个链表都从头开始走
        // 如果a走到空了，让a = b的头，同理
        // 由于二者走的距离都是a+b，若相交，则一定会遇到
        while(cura != curb){
            cura = cura == null ? headB : cura.next;
            curb = curb == null ? headA : curb.next;
        }
        return cura;
    }

    // 分别求长度，让长的先走个二者的差值步，然后一起走，一定会相遇
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        int lenA = getLen(headA);
        int lenB = getLen(headB);
        ListNode fast = headA;
        ListNode slow = headB;
        if(lenA < lenB){
            fast = headB;
            slow = headA;
        }
        int diff = lenA - lenB;
        if(diff < 0) diff *= -1;
        for(int i = 0; i < diff; i++){
            fast = fast.next;
        }
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        if(fast == null){
            return null;
        }
        return fast;
    }
    public int getLen(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }

    /**
     * 链表的回文结构
     */
    public boolean chkPalindrome(ListNode A) {
        // write code here
        // 1,先找到链表的中间节点
        // 2,对中间节点到结尾的链表逆序
        // 3,将逆序过的链表和原链表的前半段对比
        if(A == null || A.next == null){
            return true;
        }
        ListNode cur = A;
        ListNode mid = middleNode(cur);
        ListNode rv = reverseList(mid);
        while(rv != null && cur != null){
            if(rv.val != cur.val){
                return false;
            }
            rv = rv.next;
            cur = cur.next;
        }
        return true;
    }

    /**
     * 删除链表中重复的节点
     */
    public ListNode deleteDuplication(ListNode pHead){
        if(pHead == null || pHead.next == null){
            return pHead;
        }
        ListNode cur = pHead;
        ListNode pre = new ListNode(-1);
        ListNode res = pre;
        pre.next = pHead;
        //boolean flag = false;
        while(cur != null){
            ListNode next = cur.next;
            if(next == null){
                break;
            }
            if(cur.val == next.val){
                while(next != null && cur.val == next.val){
                    next = next.next;
                }
                pre.next = next;
                cur = next;
            }else{
                //if(!flag){
                //  res.next = cur;
                //flag = true;
                //}
                pre = cur;
                cur = next;
            }
        }
        return res.next;
    }



    /**
     * 链表分割
     */
    public static ListNode partition(ListNode pHead, int x) {
        // write code here
        if(pHead == null){
            return null;
        }
        ListNode cur = pHead;
        ListNode small = new ListNode(-1);
        ListNode sHead = small;
        ListNode big = new ListNode(-1);
        ListNode bHead = big;
        while(cur != null){
            if(cur.val < x){
                small.next = cur;
                small = small.next;
            }else{
                big.next = cur;
                big = big.next;
            }
            cur = cur.next;
        }
        // 如果链表是6-5-4-3-2-1，如果不把big.next置空，最后结果就是3-2-1-6-5-4-3-2-1
        big.next = null;
        small.next = bHead.next;
        return sHead.next;
    }

    /**
     * 21 合并两个有序链表
     * 利用递归
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ph = new ListNode(-1);
        ListNode pre = ph;
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }else if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l2.next,l1);
            return l2;
        }
    }

    // 创建虚拟头结点
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode ph = new ListNode(-1);
        ListNode pre = ph;
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else{
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        pre.next = l1 == null ? l2 : l1;
        return ph.next;
    }

    // 创建结果链表，小的尾插到结果链表上
    public static ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode res = null;
        ListNode last = null;
        while(l1 != null && l2 != null){
            if(l1.val >= l2.val){
                ListNode next = l2.next;
                if(res == null){
                    res = l2;
                }else{
                    last.next = l2;
                }
                last = l2;
                l2 = next;
            }else{
                ListNode next = l1.next;
                if(res == null){
                    res = l1;
                }else{
                    last.next = l1;
                }
                last = l1;
                l1 = next;
            }
        }
        if(l1 == null && l2 != null){
            last.next = l2;
        }
        if(l2 == null && l1 != null){
            last.next = l1;
        }
        return res;
    }

    /**
     * 链表中倒数第K个节点
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        for( ; i < k && fast != null; i++){
            fast = fast.next;
        }
        if(fast == null){
            if(i == k){
                return head;
            }else if(k > i){
                return null;
            }
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 876 链表的中间节点
     */
    public ListNode middleNode(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            if(fast != null){
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }

    /**
     * 206 反转链表
     */
    public ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode cur = head;
        ListNode pre = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    // 对结果链表进行头插
    public ListNode reverseList2(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode res = null;
        ListNode cur = head;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = res;
            res = cur;
            cur = next;
        }
        return res;
    }

    /**
     * 203 移除链表元素
     * 直接在原链表上修改
     */
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode cur = head;
        while(cur.next != null){
            if(cur.next.val == val){
                cur.next = cur.next.next;
            }else{
                cur = cur.next;
            }
        }
        if(head.val == val){
            return head.next;
        }
        return head;
    }

    // 新建一个结果链表，不等于val的都尾插到结果链表上
    public ListNode removeElements2(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode cur = head;
        ListNode res = null;
        ListNode last = null;
        while(cur != null){
            ListNode next = cur.next;
            if(cur.val != val){
                cur.next = null;
                // 尾插到结果链表上
                if(res == null){
                    res = cur;
                }else{
                    last.next = cur;
                }
                last = cur;
            }
            cur = next;
        }
        return res;
    }
}
