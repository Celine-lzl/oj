package day3_and_4;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] data = {1,2,3,4,5};
        int[] test = Arrays.copyOfRange(data, 1, 4);
        int[] test1 = new int[10];
        for(int c : test){
            System.out.print(c+"、");
        }
        System.out.println();
        System.arraycopy(data, 0, test1, 0, 5);
        for(int c : test1){
            System.out.print(c+"、");
        }
    }

    /**
     * 88，合并两个有序数组
     * 此题中只说明数组是有序的，并未说明是升序还是降序，但是由题解，题目所说的有序应该指的是升序
     * 以下两种方法都用到了额外空间或者时间复杂度过大，其实同样的双指针法，但是可以倒序比较
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1;
        int p2 = n-1;
        int index = m+n-1;
        while(p1 >= 0 && p2 >= 0){
            if(nums1[p1] >= nums2[p2]){
                nums1[index--] = nums1[p1--];
            }else{
                nums1[index--] = nums2[p2--];
            }
        }
        // 退出循环时有可能是nums1先走完，也可能是nums2先走完
        // 如果是nums2先走完，则不影响结果，因为剩下的nums1本来就是有序的
        // 如果是nums1先走完，则需要把剩下的nums2拷贝到nums1中
        System.arraycopy(nums2,0,nums1,0,p2+1);
    }


    // 利用一个额外数组，先把nums1中的元素保存起来
    // 然后用两个指针分别指向nums1和nums2中的第一个元素，每次将较小的那个放到nums1中
    // 最后跳出循环时有可能nums1或者nums2中还有剩余，将剩余的拷贝到nums1中
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] data = new int[m];
        data = Arrays.copyOfRange(nums1,0,m);
        int index = 0;
        int n1 = 0;
        int n2 = 0;
        while(n1 < m && n2 < n){
            if(data[n1] <= nums2[n2]){
                nums1[index++] = data[n1++];
            }else{
                nums1[index++] = nums2[n2++];
            }
        }
        if(n1 < m){
            System.arraycopy(data,n1,nums1,n1+n2,m-n1);
        }
        if(n2 < n){
            System.arraycopy(nums2,n2,nums1,n1+n2,n-n2);
        }
    }

    // 将nums2里的元素添加到nums1之后再排序，时间复杂度较高
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        for(int j = 0; j < n; j++){
            nums1[m++] = nums2[j];
        }
        Arrays.sort(nums1);
    }

    /**
     *  58，最后一个单词的长度
     *  首先从后往前遍历，找到最后一个单词的最后一个字母处，下标为end
     *  从该字母处往前遍历，直到遇到第一个空格，下标为start
     *  即end-start即为最后一个单词的长度
     */
    public  int lengthOfLastWord(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        if(s.endsWith(" ")){
            return 0;
        }
        String[] tmp = s.split(" ");
        if(tmp.length == 1){
            return s.length();
        }
        return tmp[tmp.length-1].length();
    }

    // 利用系统接口，去掉首尾空格，再按空格划分为字符串数组，数组中最后一个元素的长度即为答案
    // 但可以不使用系统接口，见上面的答案
    public static int lengthOfLastWord2(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        s = s.trim();
        if(s.endsWith(" ")){
            return 0;
        }
        String[] tmp = s.split(" ");
        if(tmp.length == 1){
            return s.length();
        }
        return tmp[tmp.length-1].length();
    }

    /**
     * 回文数
     **/
    // 翻转数组的后半段，判断和前半段是否相等
    // 例如1221，先取到最后一位1，res = res * 10 + 末尾数字 = 1
    // 1221舍弃最后一位即变成122，取出新的最后一位2，res = 1 * 10 + 2 = 12
    // 当前半段数字不再大于反转后的res时，即代表我们已经反转完了后一半数字
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        if(x % 10 == 0 && x != 0){
            return false;
        }
        int res = 0;
        while(x > res){
            int last = x % 10;
            res = res * 10 + last;
            x /= 10;
        }
        // 此时如果是奇数位数字，则判断x与res/10的大小
        return x == res || x == res/10;
    }


    // 直接判断每一个首位和末位是否相等
    public boolean isPalindrome2(int x) {
        if(x < 0){
            return false;
        }
        if(x % 10 == 0 && x != 0){
            return false;
        }
        int div = 1;
        while(x/div >= 10){
            div *= 10;
        }
        while(x > 0){
            int left = x / div;
            int right = x % 10;
            if(left != right){
                return false;
            }
            // 去掉首位和末位
            x = (x%div)/10;
            div /= 100;
        }
        return true;
    }


    // 将数字变为字符串，再判断
    public boolean isPalindrome3(int x) {
        String res = x+"";
        int left = 0;
        int right = res.length()-1;
        while(left <= right){
            if(res.charAt(left) != res.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 赎金信
     * 判断赎金信中的字符串在杂志中是否存在
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        if(magazine.length() < ransomNote.length()){
            return false;
        }
        // 开创一个数组，用来保存magazine中每个字符的个数
        // 开创一个大小为26的整型数组，数组下标0~25分别表示字母a~z
        // 统计完后，遍历ransomNote中的字符，遍历一个在数组中将该字符的个数--
        // 当某个字符的个数小于0时，即返回false
        int[] num = new int[26];
        for(int i = 0; i < magazine.length(); i++){
            num[magazine.charAt(i)-'a']++;
        }
        for(int i = 0; i < ransomNote.length(); i++){
            if(--num[ransomNote.charAt(i)-'a'] < 0){
                return false;
            }
        }
        return true;
    }

    // 利用hashmap存储每个字符的个数，然后比较
    public boolean canConstruct3(String ransomNote, String magazine) {
        if(ransomNote == null){
            return true;
        }
        if(magazine == null){
            return false;
        }
        if(magazine == null && ransomNote == null){
            return true;
        }
        char[] ran = ransomNote.toCharArray();
        char[] mag = magazine.toCharArray();
        HashMap<Character,Integer> rans = new HashMap();
        HashMap<Character,Integer> mags = new HashMap();
        // 统计赎金信中每个字符的个数
        for(int i = 0; i < ran.length; i++){
            if(rans.containsKey(ran[i])){
                rans.put(ran[i],rans.get(ran[i])+1);
            }else{
                rans.put(ran[i],1);
            }
        }
        // 统计杂志中每个字符的个数
        for(int i = 0; i < mag.length; i++){
            if(mags.containsKey(mag[i])){
                mags.put(mag[i],mags.get(mag[i])+1);
            }else{
                mags.put(mag[i],1);
            }
        }
        // 比较赎金信中的字符是否在杂志中存在
        for(Map.Entry<Character,Integer> set : rans.entrySet()){
            char tmp = set.getKey();
            int val = set.getValue();
            if(!mags.containsKey(tmp)){
                return false;
            }else if(mags.containsKey(tmp) && mags.get(tmp) < val){
                return false;
            }
        }
        return true;
    }

    // 在杂志中找到一个删除一个，直到遍历完赎金信
    public boolean canConstruct2(String ransomNote, String magazine) {
        StringBuffer sb = new StringBuffer(magazine);
        for(int i = 0; i < ransomNote.length(); i++){
            String tmp = ransomNote.charAt(i)+"";
            if(sb.toString().indexOf(tmp) == -1){
                return false;
            }else{
                sb.delete(sb.toString().indexOf(tmp),sb.toString().indexOf(tmp)+1);
            }
        }
        return true;
    }
}
