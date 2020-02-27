package JZOF;
import java.util.*;

class ListNode {
    int val;
    ListNode next = null;
    ListNode(int val) {
        this.val = val;
    }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

public class Solution {
    public static void main(String[] args) {
     //   FindNumsAppearOnce(new int[]{1,1,3,6}, new int[1], new int[1]);
        //System.out.println(match(new char[]{'a','a','a'}, new char[]{'a','*'}));
        System.out.println(isNumeric("123.45e+6".toCharArray()));

    }

    /**
     * 数据流中的中位数
     */
    // 利用优先级队列模拟一个堆，我们将数字分别放到两个堆里
    // 第奇数个数放在小堆中，第偶数个数放在大堆中，这样数据就平均分在两个堆里了
    // 我们使小堆中最小的值，都大于大堆中的最大值，这样中位数就是小堆顶值(总共有奇数个数)
    // 或者小堆顶和大堆顶的平均值（偶数个数时）
    // 为了保证小堆里最小值大于大堆最大值，我们每次在大堆添加数后，将最大值取出放入小堆中
    // 每次在小堆添加数后，将小堆的最小值拿出放入大堆中
    PriorityQueue<Integer> minheap = new PriorityQueue();
    PriorityQueue<Integer> maxheap = new PriorityQueue(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
    int count = 0;
    public void Insert(Integer num) {
        if(count % 2 == 0){
            maxheap.offer(num);
            int max = maxheap.poll();
            minheap.offer(max);
        }else{
            minheap.offer(num);
            int min = minheap.poll();
            maxheap.offer(min);
        }
        count++;
    }
    public Double GetMedian() {
        if(count % 2 == 0){
            return new Double((maxheap.poll() + minheap.poll()) / 2);
        }else{
            return new Double(minheap.poll());
        }
    }





    /**
     * 字符流中第一个不重复的字符
     */
    int[] re = new int[256]; //char是8位，共256种可能性
    String s = "";
    public void Insert(char ch){
        if(re[ch] == 0){
            re[ch] = 1;
        }else{
            re[ch] += 1;
        }
        s += ch;
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce(){
        for(int i = 0; i < s.length(); i++){
            if(re[s.charAt(i)] == 1){
                return s.charAt(i);
            }
        }
        return '#';
    }

    /**
     * 表示数值的字符串
     */
    public static boolean isNumeric(char[] str) {
        if(str == null || str.length == 0){
            return false;
        }
        // 合理数值为 A.Be/Ec
        // 其中A和c为整数，且有正负号，B为小数部分，不能有正负号
        // A部分可省略，如.123表示0.123。e前e后必有数，e后必为整数，且e只会出现一次
        // 小数点只能出现一次，+-只能出现在首位或者紧跟在e/E后面
        boolean signal = false, decimal = false, hasE = false; // 符号，小数点，e/E
        for(int i = 0; i < str.length; i++){
            if(str[i] == 'e' || str[i] == 'E'){
                // 出现两个e，或者e后没有数了，或者e前不是数
                if(hasE || i == str.length-1 || (i > 0 && str[i-1] < '0' && str[i-1] > '9')){
                    return false;
                }
                hasE = true;
            }else if(str[i] == '+' || str[i] == '-'){
                //符号已经出现过了，那当前符号前必定有e
                if(signal && i > 0 && (str[i-1] != 'e' && str[i-1] != 'E')){
                    return false;
                }
                // 符号还没出现过，但却不是在首位出现，也不是e后出现
                if(!signal &&  i > 0 && (str[i-1] != 'e' && str[i-1] != 'E')){
                    return false;
                }
                signal = true;
            }else if(str[i] == '.'){
                // 小数点已经出现过了，或者出现在末位
                if(decimal || i == str.length-1){
                    return false;
                }
                // 小数点还未出现过，但是小数点前面有e
                for(int j = i; !decimal && j >= 0; j--){
                    if(str[j] == 'e' || str[j] == 'E'){
                        return false;
                    }
                }
                decimal = true;
            }else if(str[i] < '0' || str[i] > '9'){
                return false;
            }
        }
        return true;
    }


    /**
     * 字符串匹配
     */
    public static boolean match(char[] str, char[] pattern){
        if(pattern == null || str == null){
            return false;
        }
        int indexOfS = 0;
        int indexOfP = 0;
        return matchStr(str, pattern, indexOfS, indexOfP);
    }
    public static boolean matchStr(char[] str, char[] pattern, int i, int j){
        // 同时到达结尾，匹配成功
        if(j == pattern.length && i == str.length){
            return true;
        }
        // 如果j到达结尾，而i还没到达结尾，则匹配失败
        if(j == pattern.length && i != str.length){
            return false;
        }
        // 第二个字符是*，就有很多种情况：
        // 如果二者第一个字符相等，则str向后移动一位，pattern可以后移两位（忽略掉前面）
        //                     pattern也可以选择在原地(*可以代表前一个字符出现多次)
        // str也可以原地不动，pattern后移两位（忽略掉前面）
        // 如果二者第一个字符不相等，则pattern必须后移两位
        if(j+1 < pattern.length && pattern[j+1] == '*'){
            if(i != str.length && str[i] == pattern[j] || i != str.length && pattern[j] == '.'){
                return matchStr(str, pattern, i+1, j+2) ||
                        matchStr(str, pattern, i+1, j) ||
                        matchStr(str, pattern, i, j+2);
            }else{
                return matchStr(str, pattern, i, j+2);
            }
        }
        // 第二个字符不是*，则只需要比较第一个字符
        // 第一个字符相等，继续比较后一位的字符
        if(i != str.length && str[i] == pattern[j] || pattern[j] == '.' && i != str.length){
            return matchStr(str, pattern, i+1, j+1);
        }
        return false;
    }


    /**
     * 表示数值的字符串
     */
    public int StrToInt1(String str) {
        if(str == null || str.length() == 0){
            return 0;
        }
        int fuhao = 0; // 正负
        int num = 0;
        if(str.charAt(0) == '-'){
            fuhao = 1;
        }
        for(int i = fuhao; i < str.length(); i++){
            char c = str.charAt(i);
            if( c == '+'){
                continue;
            }
            if(c < '0' || c > '9'){
                return 0;
            }
            num = num * 10 + c - '0';
        }
        if(fuhao == 1){
            num *= -1;
        }
        if(num > Integer.MAX_VALUE || num < Integer.MIN_VALUE){
            return 0;
        }
        return num;
    }


    /**
     * 圆圈中最后剩下数字
     */
    public static int StrToInt(String str) {
        if(str == null || str.length() == 0){
            return 0;
        }
        boolean flag = true; // 正负
        int num = 0;
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(i == 0 && c == '-'){
                flag = false;
            }else if(i == 0 && c == '+'){
                continue;
            }else if(  c >= '0' && c <= '9'){
                num = (num * 10) + (c - '0' );
            }else{
                return 0;
            }
        }
        if(flag == false){
            return -1 * (num / 10);
        }
        return num / 10;
    }

    /**
     * 翻转单词序列
     */
    public String ReverseSentence(String str) {
        if(str == null || str.length() == 0){
            return str;
        }
        String[] s = str.split(" ");
        if(s.length == 0){
            return str;
        }
        Stack<String> stack = new Stack();
        for(int i = 0; i < s.length; i++){
            stack.push(s[i]);
        }
        // 添加
        StringBuffer sb = new StringBuffer();
        while(!stack.empty()){
            sb.append(stack.pop());
            sb.append(" ");
        }
        return sb.delete(sb.length()-1,sb.length()).toString();
    }

    /**
     * 左旋转字符串
     */
    public String LeftRotateString(String str,int n) {
        if(str == null || str.length() == 0 || n < 0){
            return str;
        }
        n %= str.length();
        char[] c = str.toCharArray();
        reverse(c, 0, n-1);
        reverse(c, n, str.length()-1);
        reverse(c, 0, str.length()-1);
        StringBuffer sb = new StringBuffer();
        for(char cc: c){
            sb.append(cc);
        }
        return sb.toString();
    }
    // 逆序
    public void reverse(char[] data, int begin, int end){
        while(begin < end){
            char temp = data[begin];
            data[begin] = data[end];
            data[end] = temp;
            begin++;
            end--;
        }
    }

    /**
     * 和为s的连续正数序列
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        if(sum < 3){
            return list;
        }
        int small = 1;
        int big = 2;
        int mid = (sum + 1) / 2;
        int cursum = big + small;
        while(small < mid){
            if(cursum == sum){
                list.add(printSequeue(small, big));
            }
            while(small < mid && cursum > sum){
                cursum -= small;
                small++;
                if(sum == cursum){
                    list.add(printSequeue(small, big));
                }
            }
            big++;
            cursum += big;
        }
        return list;
    }
    // 打印连续正数序列
    public static ArrayList<Integer> printSequeue(int small, int big){
        ArrayList<Integer> temp = new ArrayList();
        for(int i = small; i <= big; i++){
            temp.add(i);
        }
        return temp;
    }

    /**
     * 数组中只出现1次的数字
     */
    public static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        // 两个相同数字异或结果肯定是0
        // 因此我们把原数组分成两部分，每部分含一个只出现一次的数字，最后两部分分别异或就是结果
        // 我们先把数组全部异或得到一个结果，根据这个结果二进制中第一位出现1的位置index，分割数组
        // index是1的在一组，index是0的在另一组
        if(array == null || array.length == 0){
            return;
        }
        int res = 0;
        for(int i = 0; i < array.length; i++){
            res ^= array[i];
        }
        int index = findBite1(res);
        for(int i = 0; i < array.length; i++){
            if(is1(array[i], index)){
                num1[0] ^= array[i];
            }else{
                num2[0] ^= array[i];
            }
        }
    }
    // 找第一个1出现的位置
    public static int findBite1(int res){
        int count = 0;
        while((res & 1) == 0){
            count++;
            res = res >> 1;
        }
        return count;
    }
    // 确定index位是0还是1
    public static boolean is1(int n, int index){
        n = n >> index;
        return (n & 1) == 1;
    }

    /**
     * 数字在排序数组中出现的次数
     */
    public int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0){
            return 0;
        }
        int num = 0;
        int first = firstK(array, k);
        int last = lastK(array, k);
        if(first > -1 && last > -1){
            num = last - first + 1;
        }
        return num;
    }
    // 找K第一次或最后一次出现的位置
    public int firstK(int[] arr, int target){
        int start = 0;
        int end = arr.length-1;
        if(start > end){
            return -1;
        }
        int mid = (end + start) / 2;
        if(start > end){
            return -1;
        }
        while(start <= end){
            if(arr[mid] == target){
                if(mid > 0 && arr[mid-1] != target || mid == 0){
                    return mid;
                }else{
                    end = mid-1;
                }
            }else if(arr[mid] > target){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return start;
    }

    public int lastK(int[] arr, int target){
        int start = 0;
        int end = arr.length-1;
        int mid = (end + start) / 2;
        if(start > end){
            return -1;
        }
        while(start <= end){
            if(arr[mid] == target){
                if(mid < arr.length-1 && arr[mid+1] != target || mid == arr.length-1){
                    return mid;
                }else{
                    start = mid + 1;
                }
            }else if(arr[mid] > target){
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        return start;
    }

    /**
     * 第一个只出现一次的字符
     */
    public static int FirstNotRepeatingChar(String str) {
        if(str == null || str.length() == 0){
            return -1;
        }
        ArrayList<Character> list = new ArrayList();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(!list.contains(c)){
                list.add(Character.valueOf(c));
            }else{
                list.remove(Character.valueOf(c));
            }
        }
        if(list.size() <= 0){
            return -1;
        }
        return str.indexOf(list.get(0));
    }

    // 利用hashmap
    public static int FirstNotRepeatingChar2(String str) {
        if(str == null){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap();
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c)+1);
            }else{
                map.put(c, 1);
            }
        }
        for(int i = 0; i < str.length(); i++){
            if(map.get(str.charAt(i)) == 1){
                return i;
            }
        }
        return -1;
    }


    /**
     * 丑数
     */
    public static int GetUglyNumber_Solution(int index) {
        if(index <= 0){
            return 0;
        }
        // 我们知道，丑数总是由其他丑数乘2或3或5得到的
        ArrayList<Integer> list = new ArrayList();
        list.add(1); // 第一个丑数
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        while(list.size() < index){
            int m2 = list.get(i2) * 2;
            int m3 = list.get(i3) * 3;
            int m5 = list.get(i5) * 5;
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);
            if(min == m2) i2++;
            if(min == m3) i3++;
            if(min == m5) i5++;
        }
        return list.get(list.size()-1);
    }


    // 这样计算很容易超时，此处列出只是提供一个思路
    public int GetUglyNumber_Solution2(int index) {
        if(index <= 0){
            return 0;
        }
        int uglyCount = 0; // 第几个丑数
        int num = 0; // 从1开始，找第index个丑数
        while(uglyCount < index){
            ++num;
            if(isUgly(num)){
                uglyCount++;
            }
        }
        return num;
    }
    // 判断丑数
    // m是n的因子，即 n%m == 0
    // 也就是说如果一个数能被2/3/5整除，就连续除以2/3/5，如果最后得到的是1，即是丑数
    public boolean isUgly(int n){
        while(n % 2 == 0){
            n /= 2;
        }
        while(n % 3 == 0){
            n /= 3;
        }
        while(n % 5 == 0){
            n /= 5;
        }
        if(n == 1){
            return true;
        }
        return false;
    }

    /**
     * 把数组排成最小的数
     */
    public static String PrintMinNumber(int [] numbers) {
        if(numbers == null || numbers.length == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] num = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            num[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(num, new Comparator<String>(){
            public int compare(String a, String b){
                String s1 = a + b;
                String s2 = b + a;
                return s1.compareTo(s2);
            }
        });
        for(String s : num){
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 最小的K个数
     */
    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList();
        if(input == null || k > input.length || k <= 0){
            return list;
        }
        int[] temp = new int[k];
        for(int i = 0; i < k; i++){
            temp[i] = input[i];
        }
        // 建堆
        creatHeap(temp, k);
        // 比较
        for(int i = k; i < input.length; i++){
            if(input[i] < temp[0]){
                temp[0] = input[i];
                heapify(temp, k-1, 0);
            }
        }
        for(int i = 0; i < k; i++){
            list.add(temp[i]);
        }
        return list;
    }
    // 建堆
    private static void creatHeap(int[] data, int n) {
        int lastnode = n-1;
        int parent = (lastnode - 1) / 2;
        for(int i = parent; i >= 0; i--){
            heapify(data, n, i);
        }
    }

    // 向下调整
    private static void heapify(int[] data, int n, int index) {
        int child1 = 2 * index + 1;
        int child2 = 2 * index + 2;
        if(child1 >= n){
            return;
        }
        int max = index;
        // 找到孩子节点中较大的那个
        if(data[child1] > data[max]){
            max = child1;
        }
        if(data[child2] > data[max]){
            max = child2;
        }
        // 将最大的节点放到当前小堆的堆顶，max ！= index 即孩子比根节点大，此时需要交换
        if(max != index){
            swap111(data,max ,index );
            heapify(data, n, max);
        }
    }

    public static void swap111(int[] data, int x, int y) {
        int tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }



    /**
     * 出现超过一半的数字
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length <= 0){
            return 0;
        }
        int len = array.length;
        int mid = len >> 1;
        int end = len-1;
        int start = 0;
        int index = partion(array, start, end);
        while(mid != index){
            if(index > mid){
                index = partion(array, start, index-1);
            }else{
                index = partion(array, index+1, end);
            }
        }
        int res = array[index];
        if(!resMoreThanHalf(array,res)){
            return 0;
        }
        return res;
    }
    public boolean resMoreThanHalf(int[] arr, int res){
        int count = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == res){
                count++;
            }
        }
        return count*2 > arr.length;
    }
    public int partion(int[] arr, int start, int end){
        int left = start;
        int right = end;
        while(left < right){
            while(left < right && arr[left] <= arr[end]){
                left++;
            }
            while(left < right && arr[right] >= arr[end]){
                right--;
            }
            swap(arr, left, right);
        }
        swap(arr, left, end);
        return left;
    }



    /**
     * 二叉搜索树与双向链表
     */
    TreeNode pre = null;
    TreeNode res = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null){
            return null;
        }
        InOrder(pRootOfTree);
        return res;
    }
    private void InOrder(TreeNode root){
        if(root != null){
            InOrder(root.left);
            buildList(root);
            InOrder(root.right);
        }
    }
    private void buildList(TreeNode cur){
        cur.left = pre;
        if(pre == null){
            res = cur; // 结果链表中的第一个元素
        }else{
            pre.right = cur;
        }
        pre = cur;
    }

    /**
     * 二叉树中和为某一值的路径
     */
    ArrayList<ArrayList<Integer>> res11 = new ArrayList();
    ArrayList<Integer> list = new ArrayList();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null){
            return res11;
        }
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null){
            res11.add(new ArrayList(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size()-1);
        return res11;
    }

    /**
     * 栈的压入弹出序列
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA == null || popA == null || pushA.length != popA.length || pushA.length == 0){
            return false;
        }
        Stack<Integer> stack = new Stack();
        int index = 0;
        for(int i = 0; i < pushA.length; i++){
            stack.push(pushA[i]);
            while(!stack.empty() && stack.peek() == popA[index]){
                stack.pop();
                index++;
            }
        }
        return stack.empty();
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
            if(i < k){
                return null;
            }
            if(i == k){
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
     * 调整数组顺序使奇数在前偶数在后
     */
    public void reOrderArray(int [] array) {
        if(array == null || array.length == 0){
            return;
        }
        for(int i = 0; i < array.length; i++){
            // 这里j也必须从0开始取，否则如果1,2,4,6,5,7这种就不对了
            for(int j = 0; j < array.length-1; j++){
                // 只有前一个数是偶数，后一个数是奇数时才交换，保证相对位置不变
                if(array[j] % 2 == 0 && array[j+1] % 2 == 1){
                    swap(array, j, j+1);
                }
            }
        }
    }
    private void swap(int[] array, int x, int y){
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * 数值的整数次方
     */
    public double Power(double base, int exponent) {
        boolean flag = false;
        double res = 1;
        if(base == 0.0){
            return 0.0;
        }
        if(exponent < 0){
            exponent = Math.abs(exponent);
            flag = true;
        }
        if(exponent == 0){
            return 1;
        }
        // 2^8 = 2^4 * 2^4
        // 若是奇数次方 2^3 --> 2^1 --> 2^0
        //<-奇数次方再乘base-2^3 <--因为奇数次方res*base-- 2^1(此时res为1) <--返回1--- 2^0
        // a^n =a^(2/n)*a^(2/n)---n为偶数
        //    =a^((n-1)/2)*a^((n-1)/2)*a----n为奇数。对奇数还说(n-1)/2与n/2结果无异
        res = Power(base, exponent >> 1);
        res *= res;
        // 判断奇数次方
        if(exponent % 2 == 1){
            res *= base;
        }
        if(flag){
            return 1/res;
        }
        return res;
    }

    /**
     * 6，二进制中1的个数
     * 利用n与n-1相与会把n二进制中最右边的1变为0，来计算
     */
    public int NumberOf1(int n) {
        int count = 0;
        // n和(n-1)做与运算，会把 n 二进制中最右边的1变为0
        while(n != 0){
            count++;
            n = n & (n-1);
        }
        return count;
    }

    public int NumberOf1_(int n) {
        String s = Integer.toBinaryString(n); // 将整数转为二进制字符串
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '1'){
                count++;
            }
        }
        return count;
    }

    /**
     * 5，两个栈实现队列
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.empty()){
            while(!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    /**
     *,4，重建二叉树
     * 利用递归分出左右子树的前中序列
     */
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        TreeNode root = buildTree(pre, 0, pre.length-1, in, 0, in.length-1);
        return root;
    }
    // 构建二叉树
    public TreeNode buildTree(int[] pre, int pl, int pr, int[] in, int il, int ir){
        if(pl > pr || il > ir){
            return null;
        }
        TreeNode root = new TreeNode(pre[pl]);
        for(int i = il; i <= ir; i++){
            if(in[i] == pre[pl]){
                // 递归时注意这些边界的取值
                root.left = buildTree(pre, pl+1, pl+i-il, in, il, i-1);
                root.right = buildTree(pre,i-il+1+pl, pr, in, i+1, ir);
                break;
            }
        }
        return root;
    }

    /**
     *,3，从尾到头打印链表
     * 也可以用栈 或者 先逆序链表再添加节点
     */
    ArrayList<Integer> list1 = new ArrayList();
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode != null){
            printListFromTailToHead(listNode.next);
            list1.add(listNode.val);
        }
        return list1;
    }

    /**
     *,2，替换空格
     * 也可以创建新的StringBuffer
     */
    public String replaceSpace(StringBuffer str) {
        int spacenum = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ' '){
                spacenum++;
            }
        }
        int newlen = str.length() + 2 * spacenum;
        int oldindex = str.length()-1;
        int newindex = newlen - 1;
        str.setLength(newlen);
        for( ; oldindex >= 0 && oldindex < newindex; oldindex--){
            if(spacenum == 0){
                break;
            }
            if(str.charAt(oldindex) == ' '){
                spacenum--;
                str.setCharAt(newindex--, '0');
                str.setCharAt(newindex--, '2');
                str.setCharAt(newindex--, '%');
            }else{
                str.setCharAt(newindex--, str.charAt(oldindex));
            }
        }
        return str.toString();
    }

    /**
     * 1，二维数组的查找
     */
    public boolean Find(int target, int [][] array) {
        if(array == null || array.length == 0){
            return false;
        }
        int row = array.length;
        int col = array[0].length;
        int x = 0;
        int y = col-1;
        while(x < row && y >= 0){
            if(target == array[x][y]){
                return true;
            }else if(y >= 0 && target < array[x][y]){
                y--;
            }else if(x < row && target > array[x][y]){
                x++;
            }
        }
        return false;
    }
}
