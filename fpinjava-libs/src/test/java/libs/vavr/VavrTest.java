package libs.vavr;

import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * https://blog.j-labs.pl/2019/10/Functional-programming-in-Java-introduction-to-Vavr.io
 */
public class VavrTest {

    //@Test
    void persistenceAndImmutability() {
        List<Integer> list1 = List.of(1, 2, 3);
        //list1 cannot be changed after creation
        List<Integer> list2 = list1.prepend(0).append(4);
        //prepend and append operations return a "copy" of list1

        //assertThat(list1).containsExactly(1, 2, 3);
        //assertThat(list2).containsExactly(0, 1, 2, 3, 4);
    }

    //@Test
    void methodWith3Parameters() {
        Function3<Integer, Integer, Integer, Integer> sum
                = (a, b, c) -> a + b + c;

        Integer result = sum.apply(1, 2, 3);

        //assertThat(result).isEqualTo(6);
    }

    @Test
    public void lifting() {
        Function2<Integer, Integer, Integer> divide = (x, y) -> x / y;

        //ArithmeticException will be thrown
        //Integer result = divide.apply(5, 0);

        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        Option<Integer> result = safeDivide.apply(10, 5);
        Option<Integer> noResult = safeDivide.apply(10, 0);

        assertTrue(result.isDefined());
        assertTrue(noResult.isEmpty());
    }
}
