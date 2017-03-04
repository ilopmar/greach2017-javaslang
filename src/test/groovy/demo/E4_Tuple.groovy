package demo

import javaslang.Tuple2
import spock.lang.Specification

class E4_Tuple extends Specification {

    void 'Get some data from a Tuple'() {
        given:
            def userInfo = MultipleValues.someUserInfo('Iván', 37)

        expect:
            userInfo._1 == 'IVÁN'
            userInfo._2 == 27
            userInfo._3 == true
    }

    void 'Some methods on Tuple'() {
        given:
            def userInfo = MultipleValues.someUserInfo('Iván', 37)

        expect:
            userInfo.map1 { name -> name.toLowerCase().reverse() }._1 == 'návi'
            userInfo.apply { name, age, isMarried -> "${name}-${age}-${isMarried}" } == 'IVÁN-27-true'
    }

    void 'Compare tuples'() {
        given:
            def point1 = new Tuple2<Integer, Integer>(10, 5)
            def point2 = new Tuple2<Integer, Integer>(10, 7)
            def point3 = new Tuple2<Integer, Integer>(11, 5)
            def point4 = new Tuple2<Integer, Integer>(11, 5)

        expect:
            point1 < point2
            point3 > point2
            point3 == point4
    }

}
