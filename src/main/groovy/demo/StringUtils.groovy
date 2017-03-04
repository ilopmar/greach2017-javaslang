package demo

import static javaslang.API.$
import static javaslang.API.Case
import static javaslang.API.Match
import static javaslang.Predicates.isIn

import groovy.transform.CompileStatic

import java.util.function.Predicate

@CompileStatic
class StringUtils {

    static String convertIf(Integer i) {
        String output
        if (i == 0) {
            output = 'zero'
        } else if (i == 1) {
            output = 'one'
        } else if (i == 2) {
            output = 'two'
        } else if (i == 3) {
            output = 'three'
        } else {
            output = 'unknown'
        }

        return output
    }

    static String convertSwitch(Integer i) {
        String output
        switch (i) {
            case 0:
                output = 'zero'
                break
            case 1:
                output = 'one'
                break
            case 2:
                output = 'two'
                break
            case 3:
                output = 'three'
                break
            default:
                output = 'unknown'
                break
        }

        return output
    }

    static String convert(Integer i) {
        return Match(i).of(
            Case($(0), 'zero'),
            Case($(1), 'one'),
            Case($(2), 'two'),
            Case($(3), 'three'),
            //Case({Integer x -> x > 2} as Predicate, 'greater than 2'),
            Case($(), 'unknown')
        )
    }

    static String convertCase2(Integer i) {
        switch (i) {
            case 0: return 'zero'
            case 1: return 'one'
            case 2: return 'two'
            case 3: return 'three'

            default: return 'unknown'
        }
    }

    static String parseArgs(String arg) {
        return Match(arg).of(
            Case(isIn('-h', '--help'), 'Help'),
            Case(isIn('-v', '--version'), 'Version')
        )
    }


    // From Mario's Blog:
    //     http://mariogarcia.github.io/blog/blog/2017/03/groovy-switch-case.html
    //
    static User User(String name, Integer age) {
        new User(name, age)
    }

    String example1(User user) {
        switch (user) {
            case User('carl', 22): return 'carl'
            case User('john', 34): return 'john'

            default: return 'nobody'
        }
    }

    String example2(User user) {
        switch (user) {
            case User(endsWith('arl'), gt(25)): return 'carl'
            case User(endsWith('hn'), lt(23)): return 'john'
            case User(any(), gt(60)): return 'maria'

            default: return 'nobody'
        }
    }

    static Closure<Boolean> User(Closure<Boolean> name, Closure<Boolean> age) {
        return { User u -> name(u.name) && age(u.age) }
    }

    static Closure<Boolean> any() {
        return { -> true }
    }

    static Closure<Boolean> endsWith(String ending) {
        return { String s -> s.endsWith(ending) }
    }

    static Closure<Boolean> gt(Integer lowerBound) {
        return { Integer n -> n > lowerBound }
    }

    static Closure<Boolean> lt(Integer upperBound) {
        return { Integer n -> n < upperBound }
    }
}
