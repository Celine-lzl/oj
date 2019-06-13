package com.lzl;
import javax.xml.soap.Node;
import java.util.Arrays;
class MyArrayList {
    // 线性表头插
    private int[] array; // 存储数据的实际空间
    private int size;    // 实际存储的数据个数
    public void pushFront(int v){
        for(int i = 0; i < size; i++){
            array[size-1] = array[size-i-1];
            size++;
            if(size == array.length){
                int[] newArr = new int[array.length*2];
                System.arraycopy(array,0,newArr,0,array.length);
                this.array = newArr;
            }
        }
        array[0] = v;
        size++;
    }
}
public class Test {

}
