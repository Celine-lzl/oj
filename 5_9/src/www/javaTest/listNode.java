package www.javaTest;

//public class listNode {
//    int val;
//    listNode next;
//    listNode(int x) { val = x; }
//}

// 牛客 -- 链表中倒数第K个节点
//public class Solution {
//    public ListNode FindKthToTail(ListNode list,int k) {
//        if (list == null)   return list;
//
//        ListNode node = list;
//        int count = 0;
//        while (node != null) {
//            count++;
//            node = node.next;
//        }
//        if (count < k)  return null;
//
//        ListNode p = list;
//        for (int i = 0; i < count - k; i++) {
//            p = p.next;
//        }
//
//        return p;
//    }
//}


// 牛客 -- 链表中倒数第K个节点
//public class Solution {
//    public ListNode FindKthToTail(ListNode head,int k) {
//        ListNode pre=null,p=null;
//        //两个指针都指向头结点
//        p=head;
//        pre=head;
//        //记录k值
//        int a=k;
//        //记录节点的个数
//        int count=0;
//        //p指针先跑，并且记录节点数，当p指针跑了k-1个节点后，pre指针开始跑，
//        //当p指针跑到最后时，pre所指指针就是倒数第k个节点
//        while(p!=null){
//            p=p.next;
//            count++;
//            if(k<1){
//                pre=pre.next;
//            }
//            k--;
//        }
//        //如果节点个数小于所求的倒数第k个节点，则返回空
//        if(count<a) return null;
//        return pre;
//
//    }
//}

// leetcode876 链表的中间节点，设置快慢指针
//class Solution {
//    public ListNode middleNode(ListNode head) {
//        ListNode fast = head;
//        ListNode slow = head;
//        while(fast != null && fast.next != null){
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        return slow;
//    }

//    计数找到中间的节点
//public ListNode middleNode(ListNode head) {
//        ListNode head1 = head;
//        ListNode cur = head;
//        int count = 0;
//        while(cur != null){
//        count++;
//        cur = cur.next;
//        }
//        int half = count/2;
//        for(int i = 0;i < half;i++){
//        head1 = head1.next;
//        }
//        return head1;
//        }
//}

// leetcode206 逆序单链表
//class Solution {
//    public listNode reverseList(listNode head) {
//        // 结果链表
//        listNode result = null;
//        listNode cur = head;
//        while(cur != null){
//            // 把原链表中的节点头插到结果链表中
//            listNode next = cur.next;
//            cur.next = result;
//            result = cur;
//            cur = next;
//        }
//        return result;
//    }

//     三指针法逆序
//    public ListNode reverseList(ListNode head) {
//        ListNode pre = null;
//        ListNode cur = head;
//        while(cur != null){
//            ListNode next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
//        return pre;
//    }
//}


// LeetCode203 移除链表中指定元素
//class Solution {
//    public ListNode removeElements(ListNode head, int val) {
//        if(head == null){
//            return null;
//        }
//        ListNode cur = head;
//        // 当前结点的下一个节点若是指定值，则让当前节点的下一个指向当前结点的下一个的下一个
//        while(cur.next != null){
//            if(cur.next.val != val){
//                cur = cur.next;
//            }else{
//                cur.next = cur.next.next;
//            }
//        }
//        // 判断头结点
//        if(head.val ==val){
//            return head.next;
//        }else{
//            return head;
//        }
//    }
//}

// leetcode203递归写法
//class Solution {
//    public ListNode removeElements(ListNode head, int val) {
//        if(head == null){
//            return null;
//        }
//        head.next = removeElements(head.next,val);
//        return head.val == val? head.next:head;
//    }
//}

