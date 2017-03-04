package demo

import javaslang.Tuple
import javaslang.Tuple3

class MultipleValues {

    static Tuple3<String, Integer, Boolean> someUserInfo(String name, Integer age) {
        Tuple.of(name.toUpperCase(), age - 10, true)
    }

}
