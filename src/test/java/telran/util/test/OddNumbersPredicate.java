package telran.util.test;

import java.util.function.Predicate;


public class OddNumbersPredicate implements Predicate<Integer> {

    @Override
    public boolean test(Integer t) {
        return t % 2 != 0;
    }


}
