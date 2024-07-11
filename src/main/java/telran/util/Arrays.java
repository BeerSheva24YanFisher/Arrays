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
        if (!foundFlag) {
            middle = -1;
        }

        return middle;
    }


    public static int [] insertSorted(int[] arSorted, int number){
        int start = 0;
        int finish = arSorted.length-1;
        int middle;
        while(start<=finish){ 
            middle = (start+finish)/2;
            if  (arSorted[middle]>number){
                finish = middle-1;
            } else start = middle+1;
        }
        return insert(arSorted, start, number);
    }

    public static boolean isOneSwap(int[] array){
        boolean flag = true;
        int firstNumber=-1;
        int secondNumber=-1;
        if (array.length<2) {
            flag=false;
        } 
        if (flag) {
            for (int i = 0; i < array.length-1; i++) {
                if (array[i]>array[i+1]||i==array.length-2) {
                    if (firstNumber==-1) {
                        firstNumber=i;
                        if (i==array.length-2){
                        secondNumber = i+1;
                        break;                 
                        }       
                    } else if (secondNumber==-1) {
                        if (i==array.length-2 && array[i]>array[i+1]){
                            secondNumber = i+1;
                            break;                 
                        } else if (i==array.length-2){
                            secondNumber = firstNumber+1;
                            break;
                        } else if (array[i]>array[i+1]){
                            secondNumber = i+1;
                            break;
                        }
                    } else {
                        flag = false;
                        break;
                    }       
                }
            }

            if (firstNumber != -1 && secondNumber != -1) {
                swap(array, firstNumber, secondNumber);
                for (int i = 0; i < array.length-1; i++) {
                    if (array[i]>array[i+1]) {
                        flag = false;       
                    }
                }
                swap(array, secondNumber, firstNumber);
            } else {
                flag =false;
            }
        }
        

        return flag;
    }
}
