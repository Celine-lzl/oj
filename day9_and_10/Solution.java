package day9_and_10;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        //System.out.println(addBinary("11", "1"));
        System.out.println(myAtoi("4193 with h"));
    }

    /**
     * 8 字符串转换整数
     */
    public static int myAtoi(String str) {
        int i = 0;
        int len = str.length();
        int flag = 1; //标识正负号
        int res = 0;
        // 找到第一个非空字符
        while(i < len && str.charAt(i) == ' '){
            i++;
        }
        // 走到头了或者找到了第一个非空字符
        if(i == str.length()){
            return 0;
        }
        char c = str.charAt(i);
        if(c == '+'){
            flag = 1;
            i++;
        }else if(c == '-'){
            flag = -1;
            i++;
        }else if(c >= '0' && c <= '9'){
            flag = 1;
        }
        for( ; i < len; i++){
            char tmp = str.charAt(i);
            if(tmp < '0' || tmp > '9'){
                break;
            }
            res = res*10 + (tmp -'0');
            // 溢出判断
            if(flag > 0 && i+1 < str.length() && str.charAt(i+1) >= '0' &&
                    str.charAt(i+1) <= '9' && res > Integer.MAX_VALUE/10)
                return Integer.MAX_VALUE;
            if(flag > 0 && i+1 < str.length() && str.charAt(i+1) >= '0' &&
                    str.charAt(i+1) <= '9' && res == Integer.MAX_VALUE/10 &&
                    str.charAt(i+1)-'0' > Integer.MAX_VALUE%10)
                return Integer.MAX_VALUE;
            if(flag < 0 && i+1 < str.length() && str.charAt(i+1) >= '0' &&
                    str.charAt(i+1) <= '9' && -res < Integer.MIN_VALUE/10)
                return Integer.MIN_VALUE;
            if(flag < 0 && i+1 < str.length() && str.charAt(i+1) >= '0' &&
                    str.charAt(i+1) <= '9' && -res == Integer.MIN_VALUE/10 &&
                    -(str.charAt(i+1)-'0') < Integer.MIN_VALUE%10)
                return Integer.MIN_VALUE;
        }
        return res * flag;
    }

    /**
     * 34 在排序数组中找元素的第一个和最后一个位置
     * 利用二分查找法分别找到第一个和最后一个位置
     * 注意寻找左右边界时的边界条件，以及如何判断数组中没有找到指定元素
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[] {-1,-1};
        if(nums == null || nums.length == 0){
            return res;
        }
        res[0] = leftindex(nums,target,0,nums.length);
        res[1] = rightindex(nums,target,0,nums.length);
        return res;
    }
    // 寻找左边界
    public int leftindex(int[] nums, int target, int left, int right){
        while(left < right){
            int mid = (left+right)/2;
            if(target == nums[mid]){
                right = mid;
            }else if(target > nums[mid]){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        // target比nums中所有元素都大
        // 没有这个if，直接返回下面的会下标越界
        if(left == nums.length){
            return -1;
        }
        // target取值在nums[0]~nums[len-1]之间，但数组中并没有该元素
        return nums[left] == target? left:-1;
    }
    // 寻找右边界
    public int rightindex(int[] nums, int target, int left, int right){
        while(left < right){
            int mid = (left+right)/2;
            if(target == nums[mid]){
                left = mid+1;
            }else if(target > nums[mid]){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        // target比nums中所有元素都小
        if(left == 0){
            return -1;
        }
        // 没找到返回-1
        return nums[right-1] == target? right-1:-1;
    }

    // 上述的简写
    public int[] searchRange2(int[] nums, int target) {
        int[] res = {-1,-1};
        int leftindex = findIndex(nums,target,true);
        if(leftindex == nums.length || nums[leftindex] != target){
            return res;
        }
        int rightindex = findIndex(nums,target,false);
        res[0] = leftindex;
        res[1] = rightindex-1;
        return res;
    }
    // flag的作用是识别当前要找的是左边界还是右边界，flag为true寻找左边界，否则为右
    // 寻找左边界时：
    //    nums[mid] == target时，right = mid
    //    nums[mid] > target时，right = mid
    //    nums[mid] < target时，left = mid+1
    // 寻找右边界时：
    //    nums[mid] == target时，left = mid+1
    //    nums[mid] > target时，right = mid
    //    nums[mid] < target时，left = mid+1
    public int findIndex(int[] nums, int target, boolean flag){
        int left = 0;
        int right = nums.length;
        while(left < right){
            int mid = (left+right)/2;
            if(nums[mid] > target || flag && nums[mid] == target){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    /**
     * 67 二进制求和
     * 首先给较短的字符串前面补0，然后用两个指针分别指向字符串的末尾
     * 开始相加，并考虑进位，将每一位相加的结果存入字符串内，最后反转字符串即可
     * 不能把两个字符串转为十进制，再将十进制转为二进制，这样容易溢出
     */
    public String addBinary(String a, String b) {
        StringBuffer res = new StringBuffer();
        int ca = 0; // 进位
        for(int i = a.length()-1, j = b.length()-1; i >= 0 || j >= 0; i--, j--){
            int sum = ca; // 每一位相加的结果
            sum += i >= 0 ? a.charAt(i)-'0': 0; // 如果a比b短，则给a左边进行补0
            sum += j >= 0 ? b.charAt(j)-'0': 0;
            res.append(sum % 2); //相加结果无非就是0,1,2
            ca = sum / 2; // 进位结果不是0就是1
        }
        if(ca == 1){
            res.append(1);
        }
        return res.reverse().toString();
    }


    /**
     * 1 两数之和
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            if(map.containsKey(diff) && map.get(diff) != i){
                return new int[] {i,map.get(diff)};
            }else{
                map.put(nums[i],i);
            }
        }
        throw new Error("不存在这样的数");
    }

    // 利用HashMap键值性质，key保存nums[i]，value存nums[i]的下标
    // 计算target-nums[i]，查看当前差值是否在map中存在，存在则返回二者的value值
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i],i);
        }
        for(int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            // 题目说一个元素不能重复用，故要判断map.get(diff) != i
            if(map.containsKey(diff) && map.get(diff) != i){
                return new int[] {i,map.get(diff)};
            }
        }
        throw new Error("不存在这样的数");
    }

    // 遍历法
    public int[] twoSum3(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            int diff = target - nums[i];
            for(int j = i+1; j < nums.length; j++){
                if(diff == nums[j]){
                    return new int[]{i,j};
                }
            }
        }
        throw new Error("不存在这样的下标");
    }
}
