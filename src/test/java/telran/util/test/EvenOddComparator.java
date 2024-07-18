package telran.util.test;

import java.util.Comparator;


public class EvenOddComparator  implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        boolean o1Even = (o1 % 2 == 0);
        boolean o2Even = (o2 % 2 == 0);


        // if (o1Even && o2Even) {
        //     return o1.compareTo(o2);
        // } else if (!o1Even && !o2Even){
        //     return o2.compareTo(o1);
        // } else {
        //     return o1Even ? -1 : 1;
        // }

        return (o1Even==o2Even) ?
               (o1Even && o2Even ? o1.compareTo(o2): o2.compareTo(o1)):
               (o1Even ? -1 : 1);
    }
}