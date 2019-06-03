package com.lzl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
class ListNode {
         int val;
         ListNode next = null;
         ListNode(int val) {
             this.val = val;
         }
}
public class Solution {
    // jz3 从尾到头打印链表
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack();
        ListNode cur = listNode;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }
}
