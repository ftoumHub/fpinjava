package libs.vavr;

import io.vavr.Function1;
import io.vavr.PartialFunction;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.function.Function;

import static io.vavr.API.Map;
import static io.vavr.API.println;
import static io.vavr.control.Option.none;

/**
 * https://blog.genuine.com/2019/12/composing-partial-functions-in-scala/
 */
public class PartialFunctions {

    Function1<Integer, Integer> f = x -> 100 / x;

    @Test
    public void partFunc() {
        println(f.apply(1));
        println(f.apply(2));
        //println(f.apply(0));

        PartialFunction<Integer, Integer> pf = f.partial(x -> x != 0);
        println(pf.isDefinedAt(1));
        println(pf.isDefinedAt(0));

        final Function1<Integer, Option<Integer>> liftedPf = pf.lift();
        println(liftedPf.apply(1)); // Some(100)
        println(pf.isDefinedAt(0) ? liftedPf.apply(0) : none()); // None normalement

        final PartialFunction<Integer, Integer> unlift = PartialFunction.unlift(liftedPf).partial(x -> x != 0);
        println(unlift.isDefinedAt(1));
        println(unlift.isDefinedAt(0));
    }

    @Test
    public void fonction_compositions() {
        Function1<Integer, Integer> multiplierPar2 = x -> x * 2;
        Function1<Integer, Integer> ajouter1 = x -> x + 1;

        final Function<Integer, Integer> multiplierPar2PuisAjouter1 = multiplierPar2.andThen(ajouter1);

        println(multiplierPar2PuisAjouter1.apply(10));

        // Remplacons la seconde fonction 'ajouter1' par une fonction partielle 'inverser'

        Function1<Integer, Double> inverserFunc = x -> 1.0 / x;
        println(inverserFunc.apply(0));

        final PartialFunction<Integer, Double> inverser = inverserFunc.partial(x -> x != 0);

        final Function1<Integer, Double> multiplierPar2PuisInverser = multiplierPar2.andThen(inverser);

        println(multiplierPar2PuisInverser.apply(10));
        println(multiplierPar2PuisInverser.apply(0));
    }

    @Test
    public void partial_fonction_compositions() {
        Function1<Integer, Double> inverserFunc = x -> 1.0 / x;

        final PartialFunction<Integer, Double> inverser = inverserFunc.partial(x -> x != 0);

        final PartialFunction<Double, String> pfMap = Map(0.1, "a", 0.2, "b").asPartialFunction();

        final Function1<Integer, String> inverserPuisPfMap = inverser.andThen(pfMap);

        println(inverserPuisPfMap.apply(10));

        println(inverserPuisPfMap.apply(5));

        println(inverserPuisPfMap.apply(2));

    }
}
