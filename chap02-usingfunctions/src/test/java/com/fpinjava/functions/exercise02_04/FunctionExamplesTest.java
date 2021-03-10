package com.fpinjava.functions.exercise02_04;

import libs.Function;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FunctionExamplesTest {

    private final Function<Integer, Integer> triple = x -> x * 3;
    private final Function<Integer, Integer> square = x -> x * x;

    private final Function<Function<Integer, Integer>,
                           Function<Function<Integer, Integer>,
                                    Function<Integer, Integer>>> compose = x -> y -> z -> x.apply(y.apply(z));

    @Test
    public void higher_order_functions() {
        Function<Integer, Integer> f = compose.apply(square).apply(triple);

        assertEquals(Integer.valueOf(36), f.apply(2));

        final Integer x = Function.<Integer, Integer, Integer>higherCompose().apply(square).apply(triple).apply(2);

        assertEquals(Integer.valueOf(36), x);
    }

}