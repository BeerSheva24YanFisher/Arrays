package telran.util.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static telran.util.Arrays.add;
import static telran.util.Arrays.binarySearch;
import static telran.util.Arrays.find;
import static telran.util.Arrays.insert;
import static telran.util.Arrays.insertSorted;
import static telran.util.Arrays.isOneSwap;
import static telran.util.Arrays.matchesRules;
import static telran.util.Arrays.remove;
import static telran.util.Arrays.removeIf;
import static telran.util.Arrays.search;
import static telran.util.Arrays.sort;
import telran.util.CharacterRule;



public class ArraysTest {
    private static final int N_ELEMENTS = 1_000;
    int[] numbers = {10, 7, 12, -4, 13, 3, 14};
    @Test
    void searchTest(){
        assertEquals(0, search(numbers,10));
        assertEquals(3, search(numbers,-4));
        assertEquals(6, search(numbers,14));
        assertEquals(-1, search(numbers,222));
    }

    @Test
    void addTest(){
        int newNumber = 10;
        int[] expected = {10, 7, 12, -4, 13, 3, 14, 10};
        assertArrayEquals(expected, add(numbers, newNumber));
    }

    @Test
    void insertTest(){
    //{10, 7, 12, -4, 13, 3, 14} - all numbers
    int newNumber = 30;
    int[] expected_0 ={newNumber, 10, 7, 12, -4, 13, 3, 14};
    int[] expected_3 = {10, 7, 12, newNumber, -4, 13, 3, 14};
    int[] expected_last = {10, 7, 12,  -4, 13, 3, 14, newNumber};
    assertArrayEquals(expected_0, insert(numbers, 0, newNumber));
    assertArrayEquals(expected_3, insert(numbers, 3, newNumber));
    assertArrayEquals(expected_last, insert(numbers, numbers.length, newNumber));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->insert(numbers, numbers.length + 1, newNumber));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->insert(numbers, -1, newNumber));
    }

    @Test
    void removeTest(){
    //{10, 7, 12, -4, 13, 3, 14} - all numbers
    int[] expected_0 ={ 7, 12, -4, 13, 3, 14};
    int[] expected_3 = {10, 7, 12, 13, 3, 14};
    int[] expected_last = {10, 7, 12, -4, 13, 3};
    assertArrayEquals(expected_0, remove(numbers,0));
    assertArrayEquals(expected_3, remove(numbers, 3));
    assertArrayEquals(expected_last, remove(numbers, numbers.length-1));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->remove(numbers, numbers.length));
    assertThrowsExactly(ArrayIndexOutOfBoundsException.class, ()->remove(numbers, -1));
    }


    @Test
    void sortTest() {
        int [] testNumber = Arrays.copyOf(numbers, numbers.length);
        int[] expected = {-4, 3, 7, 10,  12,  13, 14};
        sort(testNumber);
        assertArrayEquals(expected, testNumber);
    }

    @Test
    void sortTestRandomArray(){
        int[] array = getRandomArray(N_ELEMENTS);
        int limit = array.length -1;
        sort(array);
        for (int i = 0; i < limit; i++) {
            assertTrue(array[i]<=array[i+1]);
        }
    }

    private int[] getRandomArray(int nElements) {
        int[] res = new int[nElements];
        Random random = new Random();
        for (int i = 0; i < nElements; i++) {
            res[i] = random.nextInt();
        }
        return res;
    }

    @Test
    public void binarySearchTest() {
        int[] sortedArray = {1, 2, 3, 4, 5, 7, 8, 9, 10, 11};
        assertEquals(0, binarySearch(sortedArray, 1));
        assertEquals(4, binarySearch(sortedArray, 5));
        assertEquals(8, binarySearch(sortedArray, 10));

        assertEquals(-1, binarySearch(sortedArray, 0));
        assertEquals(-11, binarySearch(sortedArray, 12)); 
        assertEquals(-6, binarySearch(sortedArray, 6)); 

        assertEquals(-1, binarySearch(new int[]{}, 5));

        assertEquals(0, binarySearch(new int[]{5}, 5));
        assertEquals(-1, binarySearch(new int[]{5}, 1));

        int[] arrayWithDuplicates = {1, 2, 2, 2, 3};
        int index = binarySearch(arrayWithDuplicates, 2);
        assertEquals(true, index >= 1 && index <= 3);

        int[] allSameElements = {1, 1, 1, 1, 1};
        index = binarySearch(allSameElements, 1);
        assertEquals(true, index >= 0 && index <= 4);
    }

    @Test
    public void insertSortedTest() {
        int[] sortedArray1 = {1, 3, 4, 5, 7, 9, 10};
        int numberToInsert1 = 6;
        int[] expectedArray1 = {1, 3, 4, 5, 6, 7, 9, 10};
        int[] resultArray1 = insertSorted(sortedArray1, numberToInsert1);
        assertArrayEquals(expectedArray1, resultArray1);

        int[] sortedArray2 = {2, 3, 4, 5};
        int numberToInsert2 = 1;
        int[] expectedArray2 = {1, 2, 3, 4, 5};
        int[] resultArray2 = insertSorted(sortedArray2, numberToInsert2);
        assertArrayEquals(expectedArray2, resultArray2);

        int[] sortedArray3 = {1, 2, 3, 4};
        int numberToInsert3 = 5;
        int[] expectedArray3 = {1, 2, 3, 4, 5};
        int[] resultArray3 = insertSorted(sortedArray3, numberToInsert3);
        assertArrayEquals(expectedArray3, resultArray3);

        int[] sortedArray4 = {};
        int numberToInsert4 = 1;
        int[] expectedArray4 = {1};
        int[] resultArray4 = insertSorted(sortedArray4, numberToInsert4);
        assertArrayEquals(expectedArray4, resultArray4);

        int[] sortedArray5 = {1, 2, 3};
        int numberToInsert5 = 2;
        int[] expectedArray5 = {1, 2, 2, 3};
        int[] resultArray5 = insertSorted(sortedArray5, numberToInsert5);
        assertArrayEquals(expectedArray5, resultArray5);
    }

    @Test
    public void testIsOneSwap() {
        int[] sortedArray1 = {1, 2, 3, 4, 5};
        assertFalse(isOneSwap(sortedArray1));

        int[] swapNeededArray = {1, 5, 3, 4, 2};
        assertTrue(isOneSwap(swapNeededArray));

        int[] swapNeededArray1 = {1,5,2,3,4};
        assertFalse(isOneSwap(swapNeededArray1));

        int[] swapNeededArray2 = {2,1,3,4,5};
        assertTrue(isOneSwap(swapNeededArray2));

        int[] swapNeededArray3 = {1, 2, 3, 5, 4};
        assertTrue(isOneSwap(swapNeededArray3));

        int[] swapNeededArray4 = {3, 2, 1, 4, 5};
        assertTrue(isOneSwap(swapNeededArray4));

        int[] swapNeededArray5 = {5, 2, 3, 4, 1};
        assertTrue(isOneSwap(swapNeededArray5));

        int[] multipleSwapArray = {1, 5, 3, 2, 4};
        assertFalse(isOneSwap(multipleSwapArray));

        int[] emptyArray = {};
        assertFalse(isOneSwap(emptyArray));

        int[] singleElementArray = {1};
        assertFalse(isOneSwap(singleElementArray));

        int[] twoElementSortedArray = {1, 2};
        assertFalse(isOneSwap(twoElementSortedArray));

        int[] twoElementSwapArray = {2, 1};
        assertTrue(isOneSwap(twoElementSwapArray));
    }


    @Test
    void binarySortAnyTypeTest(){
        String [] strings = {"lmn","cfta", "w", "aa"};
        String [] expectedASCII = {"aa", "cfta", "lmn", "w"};
        String [] expectedLength = {"w", "aa","lmn", "cfta"};
        sort(strings, String::compareTo);
        assertArrayEquals(expectedASCII, strings);
        sort(strings, (a,b)->Integer.compare(a.length(), b.length()));
        assertArrayEquals(expectedLength, strings);

    }

    @Test
    public void testBinarySearchFound() {
        Integer[] arrayInteger = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(4, binarySearch(arrayInteger, 5, Integer::compare));
        assertEquals(-11, binarySearch(arrayInteger, 11, Integer::compare));

        String[] arrayString = {"apple", "banana", "cherry", "date", "fig", "grape"};
        Comparator<String> comparatorASCII = String::compareTo;
        assertEquals(2, binarySearch(arrayString, "cherry", comparatorASCII));
        assertEquals(-7, binarySearch(arrayString, "kiwi", comparatorASCII));

        Double[] arrayDoubles = {1.5, 2.5, 3.5, 4.5, 10.4};
        Comparator<Double> comparatorDouble = Double::compare;
        assertEquals(0, binarySearch(arrayDoubles, 1.5, comparatorDouble));
        assertEquals(-5, binarySearch(arrayDoubles, 8.8, comparatorDouble));
        
        Comparator<Character> comparatorCharacter = Character::compare;
        Character[] arrayCharapter = { 'a', 'b', 'c', 'd' };
        assertEquals(1, binarySearch(arrayCharapter, 'b', comparatorCharacter));

        Integer[][] arrayArrayInteger = {{1,2},{1,3},{2,3}};
        Integer[] key = {1, 5};
        Comparator<Integer[]> comparatorArraysInteger = new ComparatorArraysInteger();
        assertEquals(-3, binarySearch(arrayArrayInteger, key, comparatorArraysInteger));
    }

    @Test
    void binarySearchNoComporatorTest(){
        String [] strings = {"aa", "cfta", "lmn", "w"};
        Person prs1 = new Person(10, "Vasya");
        Person prs2 = new Person(20, "Itay");
        Person prs3 = new Person(30, "Sara");
        Person [] persons = {prs1, prs2, prs3};
        assertEquals(1, binarySearch(strings, "cfta"));
        assertEquals(0, binarySearch(persons, prs1));
        assertEquals(-1, binarySearch(persons,new Person(5, "Serg")));     
    }

    @Test
    void evenOddSorting(){
        Integer [] array = {7, -8, 10, -100, 13, -10, 99};
        Integer [] expected = {-100, -10, -8, 10, 99, 13, 7};
        sort(array, (a,b)->{boolean isArg0Even = a % 2 == 0;
            boolean isArg1Even = b % 2 == 0;
            boolean noSwapFlag = (isArg0Even && !isArg1Even) ||
            (isArg0Even && isArg1Even && a <= b) ||
             (!isArg0Even && !isArg1Even && a >= b);
            return noSwapFlag ? -1 : 1;});
        assertArrayEquals(expected, array);
    }

    @Test
    void findTest(){
        Integer [] array = {7, -8, 10, -100, 13, -10, 99};
        Integer [] expected = {7, 13, 99};
        assertArrayEquals(expected, find(array, n->n%2!=0));
    }

    @Test
    void removeIfTest(){
        Integer [] array = {7, -8, 10, -100, 13, -10, 99};
        Integer [] expected = {-8, 10, -100, -10};
        assertArrayEquals(expected, removeIf(array, n->n%2!=0));
    }

    @Test
    void matchesRulesTest() {
        CharacterRule[] mustBeRules = {
            new CharacterRule(true, Character::isUpperCase, "No capital"),
            new CharacterRule(true, Character::isLowerCase, "No lowercase"),
            new CharacterRule(true, Character::isDigit, "No digit"),
            new CharacterRule(true, c -> c == '.', "No dot")
        };

        CharacterRule[] mustNotBeRules = {
            new CharacterRule(false, Character::isWhitespace, "Space disallowed")
        };


        // Test cases
        assertEquals("", matchesRules(new char[] {'a', 'n', '*', 'G', '.', '.', '1'}, mustBeRules, mustNotBeRules));
        assertEquals("No capital!Space disallowed!", matchesRules(new char[] {'a', 'n', '*', '.', '.', '1', ' '}, mustBeRules, mustNotBeRules));
        assertEquals("No capital!", matchesRules(new char[] {'a', 'n', '*', '.', '.', '1'}, mustBeRules, mustNotBeRules));
        assertEquals("No digit!", matchesRules(new char[] {'a', 'n', '*', 'G', '.', '.'}, mustBeRules, mustNotBeRules));
        assertEquals("No capital!No lowercase!No digit!No dot!Space disallowed!", matchesRules(new char[] {' '}, mustBeRules, mustNotBeRules));
        assertEquals("No capital!No lowercase!No digit!No dot!", matchesRules(new char[] {}, mustBeRules, mustNotBeRules));
    }

}
