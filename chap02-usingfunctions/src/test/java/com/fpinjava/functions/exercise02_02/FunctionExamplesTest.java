package com.fpinjava.functions.exercise02_02;

import static java.lang.Integer.valueOf;
import static org.junit.Assert.*;

import libs.Function;
import org.junit.Test;

/**
 * Composing functions
 *
 * If you think about functions as methods, composing them seems simple:
 * <code>System.out.println(square.apply(triple.apply(2)));</code>
 * <code>36</code>
 *
 * But this isn’t function composition. In this example, you’re composing function applications.
 * <b>Function composition is a binary operation on functions</b>, just as addition is a
 * binary operation on numbers. So you can compose functions programmatically, using a method:
 *
 * <code>
 * Function compose(final Function f1, final Function f2) {
 *     return new Function() {
 *         @Override
 *         public int apply(int arg) {
 *             return f1.apply(f2.apply(arg));
 *         }
 *     };
 * }
 * System.out.println(compose(triple, square).apply(3));
 * 27
 * </code>
 */
public class FunctionExamplesTest {

    // On peut simplifier l'écriture d'une fonction grâce à une lambda.
    // Une lambda ne change pas la définition de l'interface Function, mais permet de l'implémenter beaucoup plus simplement.
    private static final Function<Integer, Integer> triple = x -> x * 3;
    private static final Function<Integer, Integer> square = x -> x * x;

    // Les lambdas ne sont pas qu'une simplification syntaxique. Elles ont également des conséquences en termes de
    // compilation de code. Une des principales différences entre les lambdas et l'écriture d'une classe anonyme consiste
    // dans la possibilité d'omettre les types à droite du signe égal.

    private static Function<Integer, Integer> compose(final Function<Integer, Integer> f1,
                                                      final Function<Integer, Integer> f2) {
        return arg -> f1.apply(f2.apply(arg));
    }

    @Test
    public void composing_functions() {
        assertEquals(valueOf(6), triple.apply(2));
        assertEquals(valueOf(4), square.apply(2));
        assertEquals(valueOf(36), square.apply(triple.apply(2))); // not composition !

        // Attentions à l'ordre d'exécution des fonctions, la seconde fonction est appliquée en premier : (4 * 4) * 3
        assertEquals(valueOf(48), compose(triple, square).apply(4));
    }

}