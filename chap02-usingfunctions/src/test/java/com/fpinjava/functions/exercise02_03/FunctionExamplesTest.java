package com.fpinjava.functions.exercise02_03;

import libs.BinaryOperator;
import libs.Function;
import org.junit.Test;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;

public class FunctionExamplesTest {

    private static final Function<Integer, Function<Integer, Integer>> add = x -> y -> x + y;

    private static final BinaryOperator addOp = x -> y -> x + y;
    private static final BinaryOperator multOp = x -> y -> x * y;

    @Test
    public void applying_curried_functions() {

        assertEquals(valueOf(8),  add.apply(3).apply(5));
        // Here, you’re again missing some syntactic sugar. It would be great if you could apply a
        // function just by writing its name followed by its argument. It would allow coding, as in Scala:
        // add(3)(5)
        assertEquals(valueOf(8),  addOp.apply(3).apply(5));
        assertEquals(valueOf(15), multOp.apply(3).apply(5));
    }

}