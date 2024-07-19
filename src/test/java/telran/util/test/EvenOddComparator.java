package telran.util.test;

import java.util.Comparator;


public class EvenOddComparator  implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        boolean o1Even = (o1 % 2 == 0);
        boolean o2Even = (o2 % 2 == 0);
    
        int res;
    
        if (o1Even == o2Even) {
            if (o1Even && o2Even) {
                res = o1.compareTo(o2);
            } else {
                res = o2.compareTo(o1);
            }
        } else {
            res = o1Even ? -1 : 1;
        }
    
        return res;
    }
}