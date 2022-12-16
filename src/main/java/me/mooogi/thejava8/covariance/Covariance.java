package me.mooogi.thejava8.covariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Covariance {

    public static void test() {
        /* Covariance : 클래스의 상속관계를 Generics 에서도 유지시킨다
         * get() 은 가능하나 add() 는 컴파일 에러
         */

        //Number < Double
        Number number = new Double(1.1);

        List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);

        //!!! Error !!! 불가능
        //List<Number> numbers = doubles;

        // <-- 이렇게하면 할수있다(상한 경계 와일드카드)
        List<? extends Number> numbers = doubles;

        //get()은 가능하지만 add()는 불가능하다( Number 를 상속받는 아무거나 들어갈수있어 타입이 깨진다 )
        System.out.println(numbers.get(1));

        //!!! Error !!! 불가능
        //numbers.add(5.5);

        /*===================== Wildcard <?> ===========================*/
        //Object < String
        Object strObj = "오브젝트";

        //와일드카드 활용
        List<String> list = Arrays.asList("abc", "aa", "bb");

        //함수인자가 제네릭으로 잡혀있을때는 사용 불가능
        //print(list); //print(List<Object>)

        //함수인자가 와일드카드일때는 사용가능 <?> === <? extends Object>
        print2(list); //print(List<?>)
        /* 상한 경계 와일드카드 ( ? extends T )
         * - T와 T의 하위타입만 인자로 올 수 있다
         * - get() - 요소들의 공통 조상으로 읽어야 함, T 혹은 T의 상위타입으로 읽기 가능
         * - add() - null만 추가 가능
         *
         * 하한 경계 와일드카드 ( ? super T )
         * - T와 T의 상위타입만 인자로 올 수 있다
         * - get() - 요소들의 공통 조상으로 읽어야 함, Object 타입으로 읽기 가능
         * - add() - T 혹은 T의 하위 타입만 추가할 수 있다
         * */
    }

    static void print(List<Object> list) {
        for (Object val : list) {
            System.out.println(val);
        }
    }

    static void print2(List<?> list) {
        for (Object val : list) {
            System.out.println(val);
        }
    }
}
