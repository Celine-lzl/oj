package tx1;

public class Main {
    public static void main(String[] args) {
        System.out.println(NumberOf1_(-10));
    }
    /**
     * 16, 树的子结构
     */
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if(null==root2||null==root1){
            return false;
        }
        if(root1.val==root2.val){
            if(isSubtree(root1,root2)){
                return true;
            };
        }
        return HasSubtree(root1.left,root2)||HasSubtree(root1.right,root2);
    }

    public boolean isSubtree(TreeNode root1,TreeNode root2){
        if(null==root2){
            return true;
        }
        if(null==root1){
            return false;
        }
        if(root1.val!=root2.val){
            return false;
        }
        return (isSubtree(root1.left,root2.left)&&isSubtree(root1.right,root2.right));
    }

    /**
     * 15, 合并两个有序链表
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode cur1 = list1;
        ListNode cur2 = list2;
        ListNode res = null;
        ListNode last = null;
        while(cur1 != null && cur2 != null){
            if(cur1.val < cur2.val){
                if(res == null){
                    last = res = cur1;
                }else{
                    last.next = cur1;
                    last = cur1;
                }
                cur1 = cur1.next;
            }else{
                if(res == null){
                    last = res = cur2;
                }else{
                    last.next = cur2;
                    last = cur2;
                }
                cur2 = cur2.next;
            }
        }
        if(cur1 == null){
            last.next = cur2;
        }
        if(cur2 == null){
            last.next = cur1;
        }
        return res;
    }

    /**
     * 14, 逆置链表
     */
    public ListNode ReverseList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode cur  =head;
        ListNode pre = null;
        ListNode newnode = null;
        while(cur != null){
            ListNode next = cur.next;
            if(cur.next == null){
                newnode = cur;
            }
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return newnode;
    }

    /**
     * 13，链表中倒数第K个节点
     */
    public ListNode FindKthToTail(ListNode head,int k) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        int i = 0;
        for( i = 0; i < k && fast != null; i++){
            fast = fast.next;
        }
        if(fast == null){
            if( i < k){
                return null;
            }else if(i == k){
                return head;
            }
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 12, 数值的整数次方
     */
    public double Power(double base, int exponent) {
        boolean flag = false;  // 判断指数大于0还是小于0
        double res = 1;
        if(base == 0.0){
            return 0;
        }
        if(exponent == 0){
            return 1;
        }
        if(exponent < 0){
            exponent *= -1;
            flag = true;
        }
        // 递归思路，偶数次方时，4次方就是2次方乘2次方，8次方就是四次方*四次方
        // 奇数次方就要特殊处理
        res = Power(base, exponent >> 1);
        res *= res;
        // 判断指数是奇数还是偶数
        if((exponent & 1) == 1){
            res *= base;
        }
        if(flag){
            return 1/res;
        }
        return res;
    }

    /**
     * 11, 二进制中1的个数
     */
    public static int NumberOf1_(int n) {
        int count = 0;
        // 把一个数-1再和原来的数做与运算，发现结果就是把原数二进制表示中最右边一位的1编程0了
        // 那么如果一个数如果有n个1，那么我们做n次与运算，这个数就会变成0了
        while(n != 0){
            count++;
            n = n & (n-1);
        }
        return count;
    }

    public static int NumberOf1(int n) {
        int num = 0;
        int tmp = 1;
        while(tmp != 0){
            // 这里千万不要写成if((tmp & n) == 1)
            // 我们希望按位与后某一位是1，不代表它写成10进制就是1鸭
            if((tmp & n) != 0){
                num++;
            }
            tmp = tmp << 1;
        }
        return num;
    }

}
