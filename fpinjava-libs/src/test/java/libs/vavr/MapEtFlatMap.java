package libs.vavr;

import io.vavr.control.Option;
import org.junit.Test;

import java.util.Objects;
import java.util.function.Function;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Some;
import static java.lang.Integer.toHexString;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

/**
 * https://blog.raph.tech/article/scala/explications-map-flatmap-scala/
 *
 * map et flatMap sont des méthodes indispensables. Bien que leur utilisation semble simple,
 * la différenciation des deux peut être confuse. De plus, les mécanismes qu’elles utilisent
 * ne sont pas toujours très connus. Je vais tenter, à travers cet article, d’expliquer
 * de manière simple map et flatMap, tout en essayant d’éclaircir leur fonctionnement.
 */
public class MapEtFlatMap {

    @Test
    public void la_methode_map() {
        //assertEquals("nop", toEachChar("abc", rot13));
        assertEquals("klm", toEachChar("xyz", rot13));
    }

    @Test
    public void isPairTest() {
        final Option<Integer> opt1 = Some(3);
        final Option<Integer> opt2 = Some(4);
        final Option<Integer> opt3 = None();

        println("========= Sans flatMap");
        println(opt1.map(o -> isPair(o))); // Some(None)
        println(opt2.map(o -> isPair(o))); // Some(Some(4))
        println(opt3.map(o -> isPair(o))); // None

        println("========= Avec flatMap");
        println(opt1.flatMap(o -> isPair(o))); // None
        println(opt2.flatMap(o -> isPair(o))); // Some(4)
        println(opt3.flatMap(o -> isPair(o))); // None

        println("========= Avec flatMap");
        println(opt1.flatMap(o -> isPairToHex(o))); // None
        println(opt2.flatMap(o -> isPairToHex(o))); // Some(4)
        println(opt3.flatMap(o -> isPairToHex(o))); // None
        println(Some(10).flatMap(o -> isPairToHex(o))); // Some(a)
    }

    /**
     * Encodes the string using Rot13. Rot13 works by replacing each upper
     * and lower case letters with the letter 13 positions ahead or behind
     * it in the alphabet. The encryption algorithm is symmetric - applying
     * the same algorithm a second time recovers the original message.
     */
    private final Function<Character, Character> rot13 = character -> {
        Objects.requireNonNull(character);
        char c = character;
        return Match(c).of(
                Case($(n -> n >= 'a' && n >= 'n' && n <= 'z'), c -= 13),
                Case($(n -> n >= 'a' && n <= 'z'), c += 13),
                Case($(n -> n >= 'A' && n >= 'N' && n <= 'Z'), c -= 13),
                Case($(n -> n >= 'A' && n <= 'Z'), c += 13));
    };

    private String toEachChar(String str, Function<Character, Character> f) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(f);
        return str.chars()
                .mapToObj(x -> (char) x)
                .map(f)
                .map(String::valueOf)
                .collect(joining());
    }

    private Option<Integer> isPair(Integer e) {
        return Match(Option.of(e)).of(
                Case($Some($((Integer x) -> x % 2 == 0)), x -> Some(x)),
                Case($Some($()), () -> None()));
    }

    private Option<String> isPairToHex(Integer e) {
        return Match(Option.of(e)).of(
                Case($Some($((Integer x) -> x % 2 == 0)), x -> Some(toHexString(x))),
                Case($Some($()), () -> None()));
    }
}
