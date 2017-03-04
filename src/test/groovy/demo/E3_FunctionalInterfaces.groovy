package demo

import javaslang.Function1
import javaslang.Function2
import javaslang.Function4
import spock.lang.Specification

import java.util.function.BiFunction
import java.util.function.Function

class E3_FunctionalInterfaces extends Specification {

    void 'Java 8 Function'() {
        given:
            Function<Integer, Integer> square = { num -> num * num }

        expect:
            square.apply(5) == 25
    }

    void 'Java 8 BiFunction'() {
        given:
            BiFunction<Integer, Integer, Integer> sum = { num1, num2 -> num1 + num2 }

        expect:
            sum.apply(5, 7) == 12
    }

    void 'Java 8 Composing functions'() {
        given:
            Function<Integer, Integer> square = { num -> num * num }
            Function<Integer, Integer> plus2 = { num -> num + 2 }

        expect:
            square.andThen(plus2).apply(10) == 102
    }

    void 'Groovy Closure'() {
        given:
            Closure<Integer> square = { Integer num -> num * num }
            Closure<Integer> plus2 = { Integer num -> num + 2 }

        expect:
            plus2 << square << 10 == 102
            (square >> plus2).call(10) == 102
    }

    void 'Javaslang Function'() {
        given:
            Function1<Integer, Integer> square = { num -> num * num }
            Function2<Integer, Integer, Integer> sum = { a, b -> a + b }

        expect:
            sum.andThen(square).apply(10, 20) == 900
    }

    void 'Javaslang Function4'() {
        given:
            Function4<String, String, Integer, String, Integer> stringsSize = { a, b, c, d ->
                a.size() + b.size() + c.toString().size() + d.size()
            }

        expect:
            stringsSize.apply('foo', 'bar', 10, 'qux') == 11
    }

    void 'Javaslang Function memoization'() {
        given:
            Function1<Integer, Integer> square = { num -> num * num }

        expect:
            def squareMemoized = square.memoized()
            squareMemoized.apply(10) == 100
    }

    void 'Groovy Closure memoization'() {
        given:
            Closure<Integer> square = { Integer num -> num * num }

        expect:
            def squareMemoized = square.memoize()
            squareMemoized(10) == 100
    }
}
