package telran.util;

public class Arrays {

    public static int search(int[] ar, int key) {
        int index = 0;
        while (index < ar.length && key != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    public static int[] add(int[] arr, int number) {
        int[] res = java.util.Arrays.copyOf(arr, arr.length + 1);
        res[arr.length] = number;

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
        int[] res = new int[ar.length + 1];
        System.arraycopy(ar, 0, res, 0, index);
        res[index] = number;
        System.arraycopy(ar, index, res, index + 1, ar.length - index);
        return res;
    }

    /**
     *
     * @param numbers
     * @param index
     * @return new array with no removed from @param numbers number at @param
     * index
     */
    public static int[] remove(int[] numbers, int index) {
        int[] res = new int[numbers.length - 1];
        System.arraycopy(numbers, 0, res, 0, index);
        System.arraycopy(numbers, index + 1, res, index, numbers.length - index - 1);
        return res;

    }

    public static boolean pushMaxAtEnd(int[] ar, int length) {
        boolean res = false;
        for (int i = 0; i < length; i++) {
            if (ar[i] > ar[i + 1]) {
                res = true;
                swap(ar, i, i+1);
            }
        }
        return res;
    }

    public static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void sort(int[] ar) {
        int length = ar.length;
        boolean flSorted = false;
        while (!flSorted) {
            length--;
            flSorted = !pushMaxAtEnd(ar, length);
        }
    }

    public static int binarySearch(int[] arSorted, int key){
        int start = 0;
        int finish = arSorted.length-1;
        int middle = -1;
        boolean foundFlag = false;
        while(start<=finish){ 
            middle = (start+finish)/2;
            if (arSorted[middle]==key) {
                foundFlag = true;
                break;
            } else if  (arSorted[middle]>key){
                finish = middle-1;
            } else {
                start = middle+1;
            }
        }
        
        return foundFlag ? middle : -(start+1);
    }


    public static int [] insertSorted(int[] arSorted, int number){
        int position = Math.abs(-(binarySearch(arSorted, number)+1));
        return insert(arSorted, position, number);
    }

    public static boolean isOneSwap(int[] array){
        int a = 0;
        int b = array.length-1;
        boolean flag = true;

        while (a < array.length - 1 && array[a] < array[a + 1]) {
            a++;
        }
    
        while (b > 0 && array[b] > array[b - 1]) {
            b--;
        }

        if (a<b) {
            swap(array, a, b);

            for (int i = 0; i < array.length-1; i++) {
                if (array[i+1]<array[i]) {
                    flag = false;
                    break;
                }            
            }
    
            swap(array, a, b);
        }

        return  flag && a < b;
    }
}
