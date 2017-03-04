package demo

import javaslang.control.Validation.Invalid
import javaslang.control.Validation.Valid
import spock.lang.Specification

class E7_Validation extends Specification {

    def userValidator = new UserValidator()

    void 'Validate a user with right parameters'() {
        given:
            def validator = userValidator.validate('Iván López', 37)

        expect:
            validator instanceof Valid
            validator.toString() == "Valid(demo.User(Iván López, 37))"
            def user = validator.get()
            user instanceof User
            user.name == 'Iván López'
            user.age == 37
    }

    void 'Validate a user with wrong parameters'() {
        given:
            def validator = userValidator.validate('Wrong ? ¿ !"%/()Characters', -50)

        expect:
            validator instanceof Invalid
            validator.error.size() == 2
            validator.error[0] == 'Invalid characters in name: ?¿!"%/()'
            validator.error[1] == 'Age must be at least 0'
    }
}
