package jzof;
// Day1
public class Main {
    public String replaceSpace(StringBuffer str) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < str.toString().length(); i++){
            if(str.charAt(i) != ' '){
                sb.append(str.charAt(i));
            }else{
                sb.append("%20");
            }
        }
        return sb.toString();
    }

    public boolean Find(int target, int [][] array) {
        int row = 0;
        int col = array[0].length-1;
        while(row < array.length && col >= 0){
            if(array[row][col] == target){
                return true;
            }else if(array[row][col] > target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }

    public boolean Find1(int target, int [][] array) {
        int row = array.length;
        int col = array[0].length;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(target == array[i][j]){
                    return true;
                }
            }
        }
        return false;
    }
}
