package libs.vavr;

import io.vavr.*;
import io.vavr.control.Option;

import java.util.function.Function;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Some;
import static io.vavr.control.Option.none;
import static java.util.Objects.isNull;

public class Options {

    public static <A> Option<A> ensureOption(Option<A> opt) {
        return isNull(opt) ? none() : opt;
    }

    public static <T> Option<T> mapIfNotEmpty(String value, Function<String, T> f) {
        return Option(value).filter(s -> !s.isEmpty()).map(f);
    }

    public static <A, B> Option<Tuple2<A, B>> zip(Option<A> optA, Option<B> optB) {
        return optA.flatMap(a ->
                optB.map(b ->
                        Tuple.of(a, b)
                )
        );
    }

    public static <A, B, C> Option<Tuple3<A, B, C>> zip(Option<A> optA, Option<B> optB, Option<C> optC) {
        return zip(optA, optB).flatMap(t ->
                optC.map(c ->
                        Tuple.of(t._1, t._2, c)
                )
        );
    }

    public static <A, B, C, D> Option<Tuple4<A, B, C, D>> zip(Option<A> optA, Option<B> optB, Option<C> optC, Option<D> optD) {
        return zip(optA, optB, optC).flatMap(t ->
                optD.map(d ->
                        Tuple.of(t._1, t._2, t._3, d)
                )
        );
    }

    public static <A, B, C, D, E> Option<Tuple5<A, B, C, D, E>> zip(Option<A> optA, Option<B> optB, Option<C> optC, Option<D> optD, Option<E> optE) {
        return zip(optA, optB, optC, optD).flatMap(t ->
                optE.map(e ->
                        Tuple.of(t._1, t._2, t._3, t._4, e)
                )
        );
    }

}
