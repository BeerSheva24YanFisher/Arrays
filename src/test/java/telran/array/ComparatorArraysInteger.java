/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package telran.array;

import java.util.Comparator;
import java.util.Objects;


public class ComparatorArraysInteger implements Comparator<Integer[]>{

    @Override
    public int compare(Integer[] arr1, Integer[] arr2) {
        int res=0;
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (!Objects.equals(arr1[i], arr2[i])) {
                    res = arr1[i] - arr2[i];
                    break;
                }
            }
        }        
        
        return arr1.length != arr2.length ? arr1.length - arr2.length : res;
    }


}
