package demo

import javaslang.control.Try
import javaslang.control.Try.Failure
import spock.lang.Specification

class E2_Try extends Specification {

    void 'Traditional try-catch'() {
        expect:
            MathUtils.divide(40, 10) == BigDecimal.valueOf(4)
    }

    void 'Using Javaslang Try'() {
        when:
            Try<BigDecimal> result = MathUtils.divideWithTry(50, 0)

        then:
            result instanceof Failure
            result.isFailure()
            !result.isSuccess()
            result.cause

        and:
            result.getOrElse(-1) == BigDecimal.valueOf(-1)

    }

    void 'Transforming the result'() {
        when:
            Try<BigDecimal> result = MathUtils.divideWithTry(50, 0)

        then:
            result
                .map { n -> n * 2 }
                .getOrElse(0) == BigDecimal.valueOf(0)
    }

    void 'Recover from error'() {
        when:
            Try<BigDecimal> result = MathUtils.divideWithTry(50, 10)

        then:
            result
                .map { n -> this.saveInDatabase(n) }
                .recover { e -> this.emailError(e) }
    }

    private void saveInDatabase(BigDecimal number) {
        println "Number ${number} saved correctly"
    }

    private void emailError(Throwable t) {
        println "There was an error: ${t}"
    }
}
