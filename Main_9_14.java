import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main_9_14{
    public static void main(String[] args) {
        int[] data = new int[]{1,0,0,1,0};
        System.out.println(isContinuous1(data));
    }
    /**
     * 孩子们的游戏
     */
    public int LastRemaining_Solution(int n, int m) {
        if(n == 0 ){
            return -1;
        }
        List<Integer> list = new ArrayList();
        for(int i = 0; i < n; i++){
            list.add(i);
        }
        int index = 0;
        while(list.size() > 1){
            index = (index + m-1) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }
    /**
     * 扑克牌的顺子：不用排序的解法
     */
    public static boolean isContinuous1(int [] numbers) {
        if(numbers.length < 0 || numbers.length > 5){
            return false;
        }
        int max = -1;
        int min = 14;
        int[] data = new int[14]; // A~K
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 0){
                continue;
            }
            data[numbers[i]] += 1;
            // 保证没有非0的重复的牌
            if(data[numbers[i]] > 1){
                return false;
            }
            if(numbers[i] > max){
                max = numbers[i];
            }
            if(numbers[i] < min){
                min = numbers[i];
            }
        }
        if(max - min >= 5){
            return false;
        }
        return true;
    }
    /**
     * 扑克牌的顺子
     */
    public boolean isContinuous(int [] numbers) {
        if(numbers.length <= 0){
            return false;
        }
        Arrays.sort(numbers);
        int zeroCount = 0;
        int no = 0;
        for(int i = 0; i < numbers.length-1; i++){
            // 最后一个是否为0不影响整体结果
            if(numbers[i] == 0){
                zeroCount++;
                continue;
            }
            // 有除0以外的重复数字
            if(numbers[i] == numbers[i+1]){
                return false;
            }
            // 不连续的数的个数
            no += numbers[i+1] - numbers[i] - 1;
        }
        if(zeroCount >= no){
            return true;
        }
        return false;
    }
    /**
     * 左旋转字符串
     */
    public String LeftRotateString(String str,int n) {
        if(str == null){
            return "";
        }
        if(n > str.length() || n < 0){
            return "";
        }
        if(n == str.length()){
            return str;
        }
        StringBuffer sb = new StringBuffer();
        String temp = str.substring(0,n);
        sb.append(str.substring(n));
        sb.append(temp);
        return sb.toString();
    }

    /**
     * 和为S的两个数
     */
    public static ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        int left = 0;
        int right = array.length-1;
        ArrayList<Integer> list = new ArrayList(2);
        while(left < right){
            if(array[left] + array[right] < sum){
                left++;
            }else if(array[left] + array[right] > sum){
                right--;
            }else{
                if(list.size() == 0){
                    list.add(array[left]);
                    list.add(array[right]);
                }else{
                    if(array[left] * array[right] < list.get(0) * list.get(1)){
                        list.clear();
                        list.add(array[left]);
                        list.add(array[right]);
                    }
                }
                left++;
                right--;
            }
        }
        return list;
    }
}
