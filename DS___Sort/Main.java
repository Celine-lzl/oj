package DS___Sort;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[] data = new int[]{1,3,5,7,2,4,6,8};
        int[] temp = new int[data.length];
        mergeSort(data, 0, data.length, temp);
        for (int i : data) {
            System.out.print(i + "、");
        }
    }

    /**
     * 归并排序
     */
    // 把数组分成一段一段的，保证每段都是有序的
    public static void mergeSort(int[] data, int l,  int r, int[] temp){ // L为起始位置，R为终点
        // data[l,r)
        // [3,4)
        if(l == r-1){
            return;
        }
        if(l >= r){
            return;
        }
        // 平均切分
        int mid = l + (r - l) / 2;
        mergeSort(data, l, mid, temp);
        mergeSort(data, mid, r, temp);
        // 把有序的两段数组合并
        merge(data, l, mid, r, temp);

    }

    private static void merge(int[] data, int l, int m, int r, int[] temp) { // m为右边数组的起始位置，l、r分别是头尾
        int leftindex = l; // 遍历 [0,m)
        int rightindex = m; // 遍历 [m,r)
        int index = 0;
        while(leftindex < m && rightindex < r){
            if(data[leftindex] <= data[rightindex]){
                temp[index++] = data[leftindex++];
            }else{
                temp[index++] = data[rightindex++];
            }
        }
        // 左边不为空
        while (leftindex < m){
            temp[index++] = data[leftindex++];
        }
        // 右边不为空
        while(rightindex < r){
            temp[index++] = data[rightindex++];
        }
        // 把temp复制回data，由于每次temp都会被下一次新赋的值覆盖掉，因此data[k] = temp[k-l]
        for(int k = l; k < r; k++){
            data[k] = temp[k - l];
        }
    }


    /**
     * 堆排序
     */
    public static void heapSort(int[] data, int n){
        creatHeap(data, n);
        // 每次把堆顶元素和最后一个元素交换，即最大值在最后，交换完后需要对新堆顶元素进行heapify
        for(int i = n-1; i >= 0; i--){
            swap(data, i, 0);
            heapify(data, i, 0); // 因为每次都把最大值放在树的最后一个节点，我们可以认为树的节点是越来越少的
        }
    }

    // 从最后一个非叶子节点开始建堆
    private static void creatHeap(int[] data, int n) {
        int lastnode = n-1;
        int parent = (lastnode - 1) / 2;
        for(int i = parent; i >= 0; i--){
            heapify(data, n, i);
        }
    }

    private static void heapify(int[] data, int n, int index) {
        int child1 = 2 * index + 1;
        int child2 = 2 * index + 2;
        if(child2 >= n){
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
            swap(data,max ,index );
            heapify(data, n, max);
        }
    }

    /**
     * 简单选择排序
     * 有序部分[0, i]
     * 无序部分(i+1, data.length)
     */
    public static void selectedSort(int[] num) {
        for (int i = 0; i < num.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[minIndex] > num[j]) {
                    minIndex = j;
                }
            }
            swap(num, minIndex, i);
        }
    }

    public static void swap(int[] data, int x, int y) {
        int tmp = data[x];
        data[x] = data[y];
        data[y] = tmp;
    }

    /**
     * 冒泡排序：大数冒到最后，若要把小的冒到前面，则从后往前比较
     * 无序部分 [0, i]
     * 有序部分 [i+1, data.length)
     */
    // 大的冒到后面
    public static void bubbleSort(int[] data) {
        boolean flag = true; // 当某一趟比较下来发现没有数据交换，即数组已经有序，可不必再比较
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    swap(data, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    // 小的冒前面
    // 有序部分[0,i]
    // 无序部分(i,length)
    public static void bubbleSort2(int[] data){
        boolean flag = true;
        for(int i = 0; i < data.length; i++){
            for(int j = data.length-1; j > i; j--){
                if(data[j] < data[j-1]){
                    swap(data, j, j-1);
                    flag = false;
                }
            }
            if(flag){
                break;
            }
        }
    }

    /**
     * 直接插入排序
     * 有序部分[0,i)
     * 无序部分[i,length)
     * arr[i]为待插入的元素
     */
    public static void insertSort(int[] data){
        for(int i = 0; i < data.length; i++){
            // 1:找到要插入的位置
            int j = i-1;
            for( ; j >= 0  && data[i] < data[j]; j--){

            }
            // 2:待插入位置为j+1，则需要把[j+1,i]的元素都后移一格
            int key = data[i];
            for(int k = i; k > j+1; k--){
                data[k] = data[k-1];
            }
            data[j+1] = key;
        }
    }

    // 将找位置和插入同时进行
    public static void insertSort2(int[] data){
        for(int i = 0; i < data.length; i++){
            int key = data[i]; // 因为之后data[i]可能被覆盖了，提前保存下来
            int j;
            for( j = i-1; j >= 0 && key < data[j]; j--){
                data[j+1] = data[j];
            }
            data[j+1] = key;
        }
    }

    /**
     * 希尔排序
     * 如果原数组数据无序程度较高，那么直接插入排序每次后移的数据就会很多
     * 如果我们能先把数组排的稍微有序点。就能使后移的次数减小
     */
    public static void shellSort(int[] data){
        int gap = data.length;
        while(true){
            gap /= 2;
            insertSortWithGap(data, gap);
            if(gap == 1){
                break;
            }
        }
    }

    private static void insertSortWithGap(int[] data, int gap) {
        for(int i = 0; i < data.length; i++){
            int j = i - gap;
            int key = data[i];
            for( ; j >= 0 && key < data[j]; j-= gap){
                data[j+gap] = data[j];
            }
            data[j+gap] = key;
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] data){
        quick(data, 0, data.length-1);
    }

    private static void quick(int[] data, int left, int right) {
        if(left == right){ // 只有一个元素
            return;
        }
        if(left > right){ // 么有元素
            return;
        }
        int div = partion(data, left, right); // 基准值在的位置
        quick(data,0 ,div-1 );
        quick(data,div+1 ,right );
    }

    // Hover法找基准值的位置
    private static int partion(int[] data, int left, int right) {
        int start = left;
        int end = right;
        while(start < end){
            while(start < end && data[start] <= data[right]){
                start++;
            }
            while(start < end && data[end] >= data[right]){
                end--;
            }
            swap(data, start,end );
        }
        swap(data, start, right);
        return start;
    }
}
