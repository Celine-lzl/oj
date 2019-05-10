package www.javaTest;

public class ListNode {
    int val;
      ListNode next;
      ListNode(int x) { val = x; }
// 牛客 -- 链表的回文结构
public int getLength(ListNode A){
    int count = 0;
    ListNode cur = A;
    while(cur != null){
        count++;
        cur = cur.next;
    }
    return count;
}
    public ListNode reverse(ListNode A){
        ListNode pre = null;
        ListNode cur = A;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    public boolean chkPalindrome(ListNode A) {
        int length = getLength(A);
        int half = length/2;
        int i = 0;
        ListNode mid = A;
        for(i = 0 ; i < half ; i++){
            mid = mid.next;
        }
        ListNode r = reverse(mid);
        while( r != null && A != null){
            if( r.val == A.val){
                r = r.next;
                A = A.next;
            }else{
                return false;
            }
        }
        return true;
    }

// leetcode21 -- 合并两个有序链表
//class Solution {
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        // 创建一个头结点
//        ListNode dummyHead = new ListNode(0);
//        ListNode cur = dummyHead;
//        while (l1 != null && l2 != null) {
//            if (l1.val < l2.val) {
//                cur.next = l1;
//                cur = cur.next;
//                l1 = l1.next;
//            } else {
//                cur.next = l2;
//                cur = cur.next;
//                l2 = l2.next;
//            }
//        }
//        // 任一为空，直接连接另一条链表
//        if (l1 == null) {
//            cur.next = l2;
//        } else {
//            cur.next = l1;
//        }
//        return dummyHead.next;
//    }
//}

// 牛客 -- 删除重复节点
//public ListNode deleteDuplication(ListNode pHead)
//{
//    if (pHead == null) return null;
//    ListNode p = pHead;
//    ListNode n = new ListNode(0);
//    ListNode pre = n;
//    n.next = pHead;
//    boolean flag = false;
//    while (p != null) {
//        ListNode q = p.next;
//        if (q == null) break;
//        if (q.val == p.val) {
//            while (q != null && q.val == p.val) {
//                q = q.next;
//            }
//            pre.next = q;
//            p = q;
//        } else {
//            if (!flag) {
//                n.next = p;
//                flag = true;
//            }
//            pre = p;
//            p = q;
//        }
//    }
//    return n.next;
//}

//   牛客 -- 分割链表
//public ListNode partition(ListNode pHead, int x) {
//    // write code here
//    if(pHead == null || pHead.next == null)
//    {
//        return pHead;
//    }
//    ListNode cur = pHead;
//    ListNode Shead = new ListNode(-1);
//    ListNode Bhead = new ListNode(-1);
//    ListNode Stmp = Shead;
//    ListNode Btmp = Bhead;
//    while(cur != null){
//        if(cur.val < x){
//            Stmp.next = new ListNode(cur.val);
//            Stmp = Stmp.next;
//        }else{
//            Btmp.next = new ListNode(cur.val);
//            Btmp = Btmp.next;
//        }
//        cur = cur.next;
//    }
//    cur = Shead;
//    while(cur.next != null && cur.next.val != -1){
//        cur = cur.next;
//    }
//    cur.next = Bhead.next;
//    return Shead.next;
//}
//    leetcode21 合并有序链表
//    class Solution {
//        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//            // 类似归并排序中的合并过程
//            ListNode dummyHead = new ListNode(0);
//            ListNode cur = dummyHead;
//            while (l1 != null && l2 != null) {
//                if (l1.val < l2.val) {
//                    cur.next = l1;
//                    cur = cur.next;
//                    l1 = l1.next;
//                } else {
//                    cur.next = l2;
//                    cur = cur.next;
//                    l2 = l2.next;
//                }
//            }
//            // 任一为空，直接连接另一条链表
//            if (l1 == null) {
//                cur.next = l2;
//            } else {
//                cur.next = l1;
//            }
//            return dummyHead.next;
//        }
//      }
}