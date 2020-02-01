package day7_and_8;

import java.util.*;
public class Solution {
    public static void main(String[] args) {
        int[] num = {2,2,3,1};
        System.out.println(thirdMax(num));

    }

    /**
     * 414 第三大的数
     * 用三个变量来保存数组中的第一大，第二大，第三大元素
     * 要考虑到有重复数字时，以及存在Integer.MIN_VAL时，还有有多个Integer.MIN_VAL时的情况
     */
    public static int thirdMax(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        if(nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        int max = Integer.MIN_VALUE;
        int mid = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        int flag = 0; // 去掉重复后元素的个数。在1 1 2这种就不会出错了
        boolean bool = true; // 保证 MIN MIN MIN 1 1 1这种情况下不会出错
        for(int i = 0; i < nums.length; i++){
            // bool的作用是保证当数组内有多个Integer.MINVAL时，flag只会计数一次
            if(nums[i] == Integer.MIN_VALUE && bool){
                flag++;
                bool = false;
            }else if(nums[i] > max){
                flag++;
                min = mid;
                mid = max;
                max = nums[i];
            }else if(nums[i] > mid && nums[i] < max){
                flag++;
                min = mid;
                mid = nums[i];
            }else if(nums[i] > min && nums[i] < mid){
                flag++;
                min = nums[i];
            }
        }
        return flag >= 3 ? min : max;
    }

    // 利用TreeSet有序且不重复的性质，将元素添加到set内
    // 最后返回即可，但是此方法比较慢
    public int thirdMax3(int[] nums) {
        if(nums == null || nums.length == 0){
            throw new Error("no arr!");
        }
        TreeSet<Integer> set = new TreeSet();
        for(int i : nums){
            set.add(i);
            if(set.size() > 3){
                set.remove(set.first());
            }
        }
        if(set.size() < 3){
            return set.last();
        }
        return set.first();
    }

    // 先对原数组排序，再剔除重复元素，找到第三大的
    public int thirdMax2(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList();
        for(int i = 0; i < nums.length; i++){
            if(i == 0 || nums[i] != nums[i-1]){
                list.add(nums[i]);
            }
        }
        if(list.size() < 3){
            return list.get(list.size()-1);
        }
        int index = list.size()-3;
        return list.get(index);
    }

    /**
     * 66 加一：{1,2,3} 加一之后是{1,2,4} 返回{1,2,4}这个数组
     * 不能先把数组中的元素代表的数字表示起来，再把结果加一，因为如果数组中元素个数
     * 非常大时，比如几百个元素，则该数字将有几百位，不能找到一个合适的数据类型代替
     * 因此采用下面的方法：考虑到数字无非有以下3种情况：
     * 1：+1之后不会产生任何进位 2：中间可能会有几次进位 3：像99,999这种一直需要进位的
     * 我们可以对原数组进行操作，首先对最后一个元素+1，+1后%10如果不等于0，则就是无需进位，直接返回数组
     * 如果+1后%10等于0，则需要进一位，也就是前面一个元素+1，对前一个元素+1后再进行相同的判断，直到不再进位，返回数组
     * 如果在for循环内都没返回，即证明是第三种情况，直接开辟一个长度是原数组长度+1的新数组，数组第一位为0即可返回
     */
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length == 0){
            return digits;
        }
        for(int i = digits.length-1; i >= 0; i--){
            digits[i]++;
            digits[i] = digits[i] % 10;
            if(digits[i] != 0){
                return digits;
            }
        }
        digits = new int[digits.length+1];
        digits[0] = 1;
        return digits;
    }

    /**
     * 724 寻找数组的中心索引：中心索引左边所有数的和等于右边之和
     * 利用两次遍历，第一次遍历求得数组中所有元素之和sum
     * 第二次遍历，判断leftsum*2+data[i]是否等于sum
     * 等于直接返回i，不等于则leftsum再加上当前元素的值
     */
    public static  int pivotIndex(int[] nums) {
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        int leftsum = 0;
        for(int i = 0; i < nums.length; i++){
            if(leftsum*2 + nums[i] == sum){
                return i;
            }else{
                leftsum += nums[i];
            }
        }
        return -1;
    }

    // 最笨的方法， 挨个计算每个数的左右数字之和
    public int pivotIndex3(int[] nums) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        if(nums.length == 1){
            return nums[0];
        }
        for(int i = 0; i < nums.length; i++){
            int left = 0;
            int right = 0;
            if(i > 0){
                left = add(nums,0,i-1);
            }
            if(i < nums.length-1){
                right = add(nums,i+1,nums.length-1);
            }
            if(left == right){
                return i;
            }
        }
        return -1;
    }
    public int add(int[] arr, int i,int n){
        int res = 0;
        for(int j = i; j <= n; j++){
            res += arr[j];
        }
        return res;
    }

    /**
     * 905 按奇偶排序数组
     * 利用双指针，一个从前往后，一个从后往前
     * 遇到不符合条件的，交换即可
     */
    public int[] sortArrayByParity(int[] A) {
        int left = 0; // 定位偶数
        int right = A.length-1; // 定位奇数
        while(left <= right){
            if(A[left] % 2 == 1){ //遇到奇数了
                if(A[right] % 2 == 1){
                    right--;
                }else{
                    swap(A,left,right);
                    left++;
                    right--;
                }
            }else{
                left++;
            }
        }
        return A;
    }
    public void swap(int[] arr,int x, int y){
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }

    // 也可以创建一个结果数组，从前往后遍历原数组，
    // 是偶数放在结果数组前面，奇数放在后面
    public int[] sortArrayByParity2(int[] A) {
        int left = 0;
        int right = A.length-1;
        int[] res = new int[A.length];
        for(int i = 0; i < A.length; i++){
            if(A[i] % 2 == 0){
                res[left++] = A[i];
            }else{
                res[right--] = A[i];
            }
        }
        return res;
    }
}
