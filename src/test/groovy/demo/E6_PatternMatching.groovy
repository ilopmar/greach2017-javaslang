package demo

import spock.lang.Specification

class E6_PatternMatching extends Specification {

    void 'Javaslang Pattern matching'() {
        expect:
            println StringUtils.convert(value)

        where:
            value << [0, 1, 2, 3, 4]
    }

    void 'Groovy "Pattern matching"'() {
        expect:
            println StringUtils.convertCase2(value)

        where:
            value << [0, 1, 2, 3, 4]
    }
}
