package com.lzl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
public class Test {
    public static void main(String[] args) {


    }
    /**
     * leetcode 605 种花问题
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(flowerbed.length==1) return n + flowerbed[0]<=1;
        if(flowerbed[0]==0&&flowerbed[1]==0)  {flowerbed[0]=1;n--;}
        for(int i = 1; i < flowerbed.length-1; i++){
            if(flowerbed[i-1]==0&&flowerbed[i+1]==0&&flowerbed[i]==0){
                flowerbed[i]=1;n--;
            }
            if(flowerbed[flowerbed.length-2]==0&&flowerbed[flowerbed.length-1]==0)  {flowerbed[flowerbed.length-1]=1;n--;}
        }
        return n<=0;
    }

    /**
     *  leetcode 234 回文链表
     */
    public boolean isPalindrome(ListNode head) {
        // 要实现 O(n) 的时间复杂度和 O(1) 的空间复杂度，需要翻转后半部分
        if (head == null || head.next == null) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 根据快慢指针，找到链表的中点
        while(fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow.next);
        while(slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head){
        // 递归到最后一个节点，返回新的新的头结点
        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     *  leetcode 2 两数相加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0); // 结果链表
        ListNode p = l1, q = l2, curr = dummyHead; // curr是链表当前遍历的位置
        int carry = 0; // 进位标志位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0; // 若两个链表不一样长
            int y = (q != null) ? q.val : 0; // 在较短的链表前面补0
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        // 当99+1这种特殊情况时，最高位还会向前进位时
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    /*
    * jz 栈的压入、弹出序列
    * */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack();
        int index = 0;
        for(int i = 0; i < pushA.length; i++){
            stack.push(pushA[i]);
            // 若弹出序列的值与栈顶元素相同，则将栈顶元素出栈
            // 继续向后遍历下一个弹出元素，若该元素不是栈顶元素
            // 将压入数组中的还未入栈的元素压入栈中，若所有数都压完了
            // 还没有弹出元素，则返回false
            while(!stack.empty() && stack.peek() == popA[index]){
                stack.pop();
                index++;
            }
        }
        return stack.empty();
    }
}
