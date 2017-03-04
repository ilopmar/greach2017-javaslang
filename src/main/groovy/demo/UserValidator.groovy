package demo

import javaslang.control.Validation

class UserValidator {
    private static final String ERROR_NAME_MSG = 'Invalid characters in name: '
    private static final String ERROR_AGE_MSG = 'Age must be at least 0'

    Validation<List<String>, User> validate(String name, int age) {
        Validation
            .combine(validateName(name), validateAge(age))
            .ap({ n, a -> new User(name: n, age: a) })
    }

    private static Validation<String, String> validateName(String name) {
        String invalidChars = name.replaceAll('[a-zA-Z áéíóúÁÉÍÓÚ]', '')

        return invalidChars ?
            Validation.invalid(ERROR_NAME_MSG + invalidChars) :
            Validation.valid(name)
    }

    private static Validation<String, Integer> validateAge(int age) {
        return age < 0 ?
            Validation.invalid(ERROR_AGE_MSG) :
            Validation.valid(age)
    }
}
