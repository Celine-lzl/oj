import java.util.Arrays;
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
public class Main {
    /*
    * d20_2 链表分割
    * */
    public ListNode partition(ListNode pHead, int x) {
        // write code here
        ListNode small = null; // 小于x的
        ListNode big = null;   // 大于x的
        ListNode smallLast = null; // 小于x的最后一个
        ListNode bigLast = null;   // 大于等于x的最后一个
        ListNode cur = pHead;
        while(cur != null){
            ListNode next = cur.next;
            // 小于x的尾插到small上
            if(cur.val < x){
                cur.next = null;
                if(small == null){
                    small = cur;
                }else{
                    smallLast.next = cur;
                }
                smallLast = cur;
            }else{
                // 大于x的尾插到big上
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
        // 合并两个链表
        if(small == null){
            return big;
        }else{
            smallLast.next = big;
            return small;
        }
    }

    /* d20_1 微信红包
    如果存在这个数，对数组排序后，中间位置的数必定是这个数
    */
    public int getValue(int[] gifts, int n) {
        // write code here
        int len = gifts.length >> 1;
        Arrays.sort(gifts);
        int mid = gifts[len];
        for(int i = 0; i < len; i++){
            if(mid == gifts[i]){
                return mid;
            }
        }
        return 0;
    }
}