package demo

import javaslang.control.Try

class MathUtils {

    static BigDecimal divide(Integer x, Integer y) {
        return x / y
    }

    static Try<BigDecimal> divideWithTry(Integer x, Integer y) {
        Try.of { -> x / y }
    }

}
