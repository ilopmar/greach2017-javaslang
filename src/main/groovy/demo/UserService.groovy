package demo

import javaslang.control.Option

class UserService {

    static Option<User> findUserByName(String name) {
        name ?
            Option.of(new User('Iván', 37)) :
            Option.none()
    }

}
