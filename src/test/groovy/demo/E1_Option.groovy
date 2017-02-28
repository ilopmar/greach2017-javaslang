package demo

import javaslang.control.Option
import javaslang.control.Option.None
import javaslang.control.Option.Some
import spock.lang.Specification

class E1_Option extends Specification {

    void 'Java 8 Optional of null'() {
        when:
            Optional.of(null)

        then:
            thrown NullPointerException
    }

    void 'Javaslang Option of null'() {
        when:
            def option = Option.of(null)

        then:
            notThrown NullPointerException
            option instanceof None
            option.toString() == 'None'
    }

    void 'Convert Optional to Option'() {
        given:
            def javaOptional = Optional.of('Hello World')

        when:
            def javaslangOption = Option.ofOptional(javaOptional)

        then:
            javaslangOption instanceof Some
    }

    void 'Use map with an Option with value'() {
        given:
            Option<String> optionName = UserService
                .findUserByName('Iván')
                .map { u -> u.name.toUpperCase() }

        expect:
            optionName.get() == 'IVÁN'
    }

    void 'Use map with an Option without value'() {
        given:
            Option<String> optionName = UserService
                .findUserByName('')
                .map { u -> u.name.toUpperCase() }

        expect:
            optionName instanceof None
    }

    void 'Get an alternative value'() {
        given:
            Option<String> optionName = UserService
                .findUserByName('')
                .map { u -> u.name.toUpperCase() }
                .orElse(Option.of('John'))

        expect:
            optionName.get() == 'John'
    }

    void 'Get an alternative value (II)'() {
        given:
            Option<String> optionName = UserService
                .findUserByName('')
                .map { u -> u.name.toUpperCase() }

        expect:
            optionName.getOrElse('John') == 'John'
    }
}
