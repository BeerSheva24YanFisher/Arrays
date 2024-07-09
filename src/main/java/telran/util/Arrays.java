package telran.util;

import java.lang.reflect.Array;

public class Arrays {

    public static int search(int [] ar, int key){
        int index = 0;
        while(index<ar.length && key!=ar[index]){
            index ++;
        }
        return index == ar.length ? -1 : index;
    }

    public static int[] add(int [] arr, int number){
        int [] res = java.util.Arrays.copyOf(arr, arr.length+1);
        res[arr.length]=number;
        
        return res;
    } 


    /**
     * 
    * @param ar
    * @param index
    * @param number
    * @return reference to a new array containing @param number at @param index
    */
    public static int[] insert(int[] ar, int index, int number) {
            int[] res = new int[ar.length+1];
            for (int i = 0; i < index; i++) {
                res[i]=ar[i];
            }
            res[index]=number;
            for (int i = index+1; i <= ar.length; i++) {
                res[i]=ar[i-1];
            }
        return res; 
    }

    /**
     * 
     * @param numbers
     * @param index
     * @return new array with no removed from @param numbers number at @param index
     */
    public static int[] remove(int[] numbers, int index) {
        int[] res = new int[numbers.length-1];
        for (int i = 0; i < index; i++) {
            res[i]=numbers[i];
        }
        for (int i = index+1; i < numbers.length; i++) {
            res[i-1]=numbers[i];
        }
    return res;

    }
}
