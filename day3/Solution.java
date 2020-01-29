package day3;

import java.util.Arrays;

class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
}

public class Solution {

    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};

    }

    /**
     * 搜索插入位置
     */
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length <= 0){
            return -1;
        }
        if(target < nums[0]){
            return 0;
        }
        if(target > nums[nums.length-1]){
            return nums.length;
        }
        int left = 0;
        int right = nums.length-1;
        while(left <= right){
            int mid = (left+right)/2;
            if(target == nums[mid]){
                return mid;
            }else if(target > nums[mid]){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        if(target > nums[left]){
            return left+1;
        }
        return left;
    }

    /**
     * 从数组中移除指定元素
     */
    // 双指针法，一前一后，从前面遇到等于val的直接用最后一个元素覆盖，数组长度--
    public int removeElement(int[] nums, int val) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left = 0;
        int right = nums.length;
        while(left < right){
            if(nums[left] == val){
                nums[left] = nums[right-1];
                right--;
            }else{
                left++;
            }
        }
        return right;
    }

    // 遍历法，从前往后遍历，遇到不是val的就覆盖是val的
    public int removeElement2(int[] nums, int val) {
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
//    public static int removeElement(int[] nums, int val) {
//        if(nums == null){
//            return -1;
//        }
//        int left = 0;
//        int right = nums.length-1;
//        int count = 0;
//        while(left < right){
//            if(nums[left] == val){
//                count++;
//                while(nums[right] == val && right > left){
//                    right--;
//                }
//                swap(nums,left,right);
//            }
//            left++;
//        }
//        int res = nums.length - right;
//        return res;
//    }
//    public static void swap(int[] arr, int left, int right){
//        int tmp = arr[left];
//        arr[left] = arr[right];
//        arr[right] = tmp;
//    }


    /**
     * 旋转数组
     */
    public static void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        for(int i = 0; i < k; i++){
            res[i]  = nums[nums.length-k+i];
        }
        for(int i = k; i < nums.length; i++){
            res[i] = nums[i-k];
        }
        nums = res;
    }

    /**
     * 字符串中大写字母转为小写字母
     */
    public String toLowerCase(String str) {
        if(str == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            char tmp = str.charAt(i);
            sb.append((char)(tmp|32));
        }
        return sb.toString();
    }

    /**
     * 倒数第K个节点
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
        // 跳出循环，此时有两种可能，一种是走完K步正常退出，此时可快慢指针一起走
        // 也可能是fast为空了，跳出了循环
        //如果fast为空，有两种情况
        // 1：K等于链表长度，返回头结点
        // 2：K大于链表长度，返回空

        // fast为空时退出了
        if(fast == null){
            if(i < k){
                return null;
            }else if(i == k){
                return head;
            }
        }
        // 正常退出的时候
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 链表的中间节点
     */
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
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
     * 删除链表中指定节点
     */
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode cur = head;
        while(cur.next != null){
            ListNode next = cur.next;
            if(cur.next.val == val){
                cur.next = cur.next.next;
            }else{
                cur = next;
            }
        }
        if(head.val == val){
            return head.next;
        }
        return head;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        if(head == null){
            return null;
        }
        ListNode cur = head;
        ListNode res = null; // 结果链表
        ListNode last = null; // 结果链表的最后一个节点
        while(cur != null){
            ListNode next = cur.next;
            if(cur.val != val){
                cur.next = null;
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
