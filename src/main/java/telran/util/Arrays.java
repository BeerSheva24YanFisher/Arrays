package telran.util;

import java.util.Comparator;
import java.util.function.Predicate;

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
                swap(ar, i, i + 1);
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

    public static int binarySearch(int[] arSorted, int key) {
        int start = 0;
        int finish = arSorted.length - 1;
        int middle = start + (finish - start) / 2;
        while (start <= finish && arSorted[middle] != key) {
            if (arSorted[middle] > key) {
                finish = middle - 1;
            } else {
                start = middle + 1;
            }
            middle = start + (finish - start) / 2;
        }

        return start <= finish ? middle : -(start + 1);
    }

    public static int[] insertSorted(int[] arSorted, int number) {
        int position = Math.abs(-(binarySearch(arSorted, number) + 1));
        return insert(arSorted, position, number);
    }

    public static boolean isOneSwap(int[] array) {
        int left = checkLeft(array);
        int right = checkRight(array, left);
        return left < right && isOneSwapCheck(array, left, right);
    }

    private static boolean isOneSwapCheck(int[] arr, int first, int second) {
        boolean flag;
        swap(arr, first, second);
        flag = isSorted(arr);
        swap(arr, first, second);
        return flag;
    }

    private static boolean isSorted(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] < arr[i]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private static int checkLeft(int[] array) {
        int left = 0;
        while (left < array.length - 1 && array[left] < array[left + 1]) {
            left++;
        }
        return left;
    }

    private static int checkRight(int[] array, int left) {
        int right = array.length - 1;
        while (right > 0 && array[right] > array[right - 1] && left < array.length - 1) {
            right--;
        }
        return right;
    }

    public static <T> void sort(T[] array, Comparator<T> comporator) {
        int length = array.length;
        boolean flSort;
        do {
            length--;
            flSort = true;
            for (int i = 0; i < length; i++) {
                if (comporator.compare(array[i], array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    flSort = false;
                }
            }
        } while (!flSort);

    }

    public static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static <T> int binarySearch(T[] arSorted, T key, Comparator<T> comporator) {
        int start = 0;
        int finish = arSorted.length - 1;
        int middle = start + (finish - start) / 2;
        int compRes = 0;
        while (start <= finish && (compRes = comporator.compare(arSorted[middle], key)) != 0) {
            if (compRes > 0) {
                finish = middle - 1;
            } else {
                start = middle + 1;
            }
            middle = start + (finish - start) / 2;
        }

        return start <= finish ? middle : -(start + 1);
    }

    public static <T> int binarySearch(T[] array, T key) {
        return binarySearch(array, key, (a, b) -> ((Comparable<T>) a).compareTo(b));
        //return binarySearch(array, key, (a,b)->a.toString().compareTo(b.toString()));

    }

    public static <T> T[] insert(T[] array, int index, T item) {
        T[] res = java.util.Arrays.copyOf(array, array.length + 1);
        System.arraycopy(array, index, res, index + 1, array.length - index);
        res[index] = item;
        return res;
    }

    public static <T> T[] find(T[] array, Predicate<T> predicate) {
        T[] result = java.util.Arrays.copyOf(array, 0);
        for (T array1 : array) {
            if (predicate.test(array1)) {
                result = insert(result, result.length, array1);
            }
        }
        return result;
    }

    public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
        return find(array, predicate.negate());
    }

}
