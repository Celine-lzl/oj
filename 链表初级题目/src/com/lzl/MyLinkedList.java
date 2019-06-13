package com.lzl;
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
 }
class Solution {
   /*
   *  删除链表中重复的节点
   *  用两个索引分别指向链表的第一个和第二个元素
   *  如果第二个元素与第一个元素不相等，则第二个元素后面的和第一个元素都不会相等（链表是有序的）
   *  两个索引同时向后走，若相等，则第一个索引不动。第二个往后走就可以了，直到走到链表为空或
   *  遇到了与第一个元素不相等的节点
   *  */
   public ListNode deleteDuplication(ListNode pHead){
       if(pHead == null){
           return null;
       }
       ListNode dummy = new ListNode(0);
       dummy.next = pHead;
       ListNode pre = dummy;
       ListNode p1 = pHead;
       ListNode p2 = pHead.next;
       while(p2 != null){
           if(p1.val != p2.val){
               pre = pre.next;
               p1 = p1.next;
               p2 = p2.next;
           }else{
               while(p2 != null && p2.val == p1.val){
                   p2 = p2.next;
               }
               pre.next = p2;
               p1 = p2;
               if(p2 != null){
                   p2 = p2.next;
               }
           }
       }

       return dummy.next;
   }

    /*
    *  链表的回文结构
    *  找到链表的中间节点，对中间节点之后的链表做逆序
    *  对原链表与逆序后的链表比较，不等则直接返回false
    *  全部比较完都相等则返回true
    *  */
    public static int getLength(ListNode head){
        ListNode cur = head;
        int len = 0;
        while(cur != null){
            len++;
            cur = cur.next;
        }
        return len;
    }
    public static ListNode reverse(ListNode head){
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
    public boolean chkPalindrome(ListNode A) {
        // write code here
        int len = getLength(A);
        int half = len / 2;
        ListNode middle = A;
        for(int i = 0; i < half; i++){
            middle = middle.next;
        }
        ListNode r = reverse(middle);
        ListNode c1 = A;
        ListNode c2 = r;
        while(c1 != null && c2 != null){
            if(c1.val != c2.val){
                return false;
            }
            c1 = c1.next;
            c2 = c2.next;
        }
        return true;
    }

    /*
    *  链表中倒数第K个节点，同样快慢引用法
    *  但是要注意特殊情况，比如K大于链表长度
    *  K刚好等于链表长度时
    *  */
    public ListNode FindKthToTail(ListNode head,int k) {
        ListNode front = head;
        ListNode back = head;
        int i;
        for(i = 0; front != null && i < k; i++){
            front = front.next;
        }
        if(front == null && i < k){
            // k 大于 节点个数
            return null;
            // front为空但i不大于k，即找的就是第一个节点
        }else if(front == null){
            return head;
        }
        while(front != null){
            front = front.next;
            back = back.next;
        }
        return back;
    }

    /*
    *  链表的中间节点： 快慢引用
    *  快的每次走两步，慢的走一步，快的走到空了，慢的就到中奖了
    *  */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null){
            fast = fast.next;
            if(fast == null){
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /*
    *  链表分割
    *  每次将cur.next置空是为了避免形成环
    *  假如我们SL和BL都到了各自的最后一个元素
    *  原来的BL假如指向SL，而我们又把BIG接到了
    *  SL的后面，此时就形成了环
    *  */
    public ListNode partition(ListNode pHead, int x) {
        ListNode small = null;
        ListNode big = null;
        ListNode smallLast = null;
        ListNode bigLast = null;
        ListNode cur = pHead;
        while(cur != null){
            ListNode next = cur.next;
            // 小于x的放在small中
            if(cur.val < x){
                cur.next = null;
                // 小于x，尾插到small
                // 大于x，尾插到big
                if(small == null){
                    small = cur;
                }else{
                    smallLast.next = cur;
                }
                smallLast = cur;
            }
            // 大于x的放在big中
            else{
                cur.next = null;
                if(big == null){
                    big = cur;
                }else{
                    bigLast.next = cur;
                }
                bigLast = cur;
            }
            cur = next;
        }
        if(small == null){
            return big;
        }else{
            smallLast.next = big;
            return small;
        }
    }

    /*
    *  合并两个有序链表
    *  创一个结果链表，l1,l2依此比较，每次把小的放进结果链表
    *  被放进去的那个继续往后，直到大于，当有一个链表为空时，
    *  直接把另一个的剩余部分尾插到结果链表中
    *  */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode res = null;
        ListNode last = null; //记录结果链表的最后一个
        while(cur1 != null && cur2 != null){
            if(cur1.val <= cur2.val){
                // 把cur1尾插到result上
                ListNode next = cur1.next;
                cur1.next = null;
                if(res == null){
                    res = cur1;
                }else{
                    last.next = cur1;
                }
                last = cur1;
                cur1 = next;
            }else{
                // 把cur2尾插到result上
                ListNode next = cur2.next;
                cur2.next = null;
                if(res == null){
                    res = cur2;
                }else{
                    last.next = cur2;
                }
                last = cur2;
                cur2 = next;
            }
        }
        if(cur1 != null){
            last.next = cur1;
        }
        if(cur2 != null){
            last.next = cur2;
        }
        return res;
    }

    /**
     *  逆序链表
     *  头插或三指针遍历
     */
    public ListNode reverseList1(ListNode head) {
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
    // 把第一个节点的下一个元素依此头插到head前
     public ListNode reverseList(ListNode head) {
         ListNode cur = head;
         ListNode res = null;
         while(cur != null){
             ListNode next = cur.next; //保存下一个节点

             cur.next = res;  //对结果链表做头插
             res = cur;

             cur = next; //继续遍历
         }
         return res;
     }


    /*
     * 移除链表元素
     * 用当前节点的下一个判断，这样不用去找要删除
     * 的节点的上一个节点
     * */
     public ListNode removeElements(ListNode head, int val) {
         if(head == null){
             return null;
         }
         ListNode cur = head;
         while(cur.next != null){
             // 在原链表中操作，等于值则删掉，不等于就继续去下一个节点
             if(cur.next.val != val){
                 cur = cur.next;
             }else{
                 cur.next = cur.next.next;
             }
         }
         if(head.val == val){
             return head.next;
         }else{
             return head;
         }
     }
     // 创建新的结果链表，把等于val尾插到结果链表中
    public ListNode removeElements1(ListNode head, int val) {
        ListNode cur = head;
        ListNode res = null;
        ListNode last = null;
        while(cur != null){
            ListNode next = cur.next;
            if(cur.val != val){
                cur.next = null;
                if(res == null){
                    res = cur;
                }else{
                    last.next = cur;
                }
                last= cur;
            }
            cur = next;
        }
        return res;
    }
}
// 链表基本操作
public class MyLinkedList {
    public static class Node {
        public int value;
        public Node next;

        public Node(Node next) {
            this.next = next;
        }

        public Node(int value) {
            this.value = value;
        }
    }

    public Node head;

    // 链表头插
    void pushFront(Node node) {
        node.next = head;
        head = node;
    }

    // 链表尾插
    Node last = head;

    void pushBack(Node node) {
        node.next = null;
        if (head == null) {
            head = node;
        } else {
            while (last != null) {
                last = last.next;
            }
            last.next = node;
        }
    }

    // 依此打印链表中的每一个值
    public void display() {
        Node cur = head;
        while (cur != null) {
            System.out.format("%d --> ", cur.value);
            cur = cur.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.pushFront(new Node(1));
        list.pushFront(new Node(2));
        list.pushFront(new Node(3));
        list.display();
    }
}
