package me.mooogi.thejava8.functional_interface;

import java.util.Arrays;
import java.util.function.*;

public class FE {

    public static void feAndLambda() {
        // 익명 내부 클래스 anonymous inner class
        /* RunSomething runSomething = new RunSomething() {
            @Override
            public int doIt(int input) {
                return 0;
            }
        };
        */

        RunSomething rs = number -> number + 10;
        //System.out.println(rs.doIt(20));
        Plus10 plusTen = new Plus10();
        System.out.println(plusTen.apply(1));

        Function<Integer, Integer> plus10 = n -> n + 10;
        Function<Integer, Integer> mulitply2 = n -> n * 2;

        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(mulitply2); //compose: 입력한 함수를 먼저 수행하고 결과가 나온걸 plus10의 입력값으로
        Function<Integer, Integer> plus10AndMultiply2 = plus10.andThen(mulitply2); //compose: plus10이 끝나고 안에꺼

        System.out.println(multiply2AndPlus10.apply(2));
        System.out.println(plus10AndMultiply2.apply(2));

        Predicate<String> startWithA = s -> s.startsWith("A");
        Predicate<Integer> isOdd = n -> n % 2 == 1;
        // and not or 연계 가능

        Supplier<Integer> get10 = () -> 10;
        UnaryOperator<Integer> power = n -> n * n;
        BiFunction<Integer, Integer, Integer> add = (n1, n2) -> n1 + n2;
        BinaryOperator<Integer> add2 = (n1, n2) -> n1 + n2;

        /* 메소드 래퍼런스 */
        // 1. 타입::스태틱 메소드
        UnaryOperator<String> hi = Greeting::hi;

        // 2. 객체래퍼런스::인스턴스 메소드
        Greeting greeting = new Greeting();
        UnaryOperator<String> hello = greeting::hello; //hi(static)은 안됨

        // 3. 타입::인스턴스 메소드
        String[] names = { "mooogi", "toby", "messi" };
        Arrays.sort(
            names,
            // 자바 8부터는 Comparator가 Functional Interface로 바뀜
            // 구현해야할게 compareTo 하나밖에없음( equals는 추상메소드가 아님, Object에 있으니까 )
            //            new Comparator<String>() {
            //                @Override
            //                public int compare(String o1, String o2) {
            //                    return 0;
            //                }
            //            }
            // 그러므로 람다가 들어갈수 있다
            // (o1, o2) -> 0
            // 람다를 넣을 수 있는 자리에는 메소드 래퍼런스를 쓸수 있다
            String::compareToIgnoreCase
        );
        System.out.println(Arrays.toString(names));

        // 4. 타입::new
        Supplier<Greeting> newGreeting = Greeting::new;
        Function<String, Greeting> mooogiGreeting = Greeting::new; //두개가 같아보이지만 다른 생성자를 참조한다
        Greeting mooogi = mooogiGreeting.apply("mooogi");
        System.out.println(mooogi.getName());
    }

    //변수 캡처(로컬, 임시, 람다의 스코프 관계)
    public static void scopes() {
        int baseNumber = 10; //자바8부터 final을 생략할수있다 (사실상 final인 경우 effective final(뒤에 수정안하면 됨))

        //로컬 클래스에서 local variable 사용
        class LocalClass {

            void printBaseNumber() {
                System.out.println(baseNumber);
            }
        }

        //익명 클래스(Consumer를 익명 클래스처럼 사용하는 케이스)에서 local variable (baseNumber) 참조
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println();
            }
        };

        /* 로컬 클래스, 익명 클래스 vs 람다
         * 클래스에 name이라고 변수를 만들고
         * 내부 메소드 안에 name을 정의하면(local variable)
         * 내부 메소드에서는 name이 local variable이 된다 ( 로컬 클래스, 익명 클래스에서도 동일 )
         * 이때 상위스코프의 name이 가려지는걸 쉐도잉이라 한다
         *
         * 로컬, 익명 클래스는 이렇게 동작하지만 ( 스코프를 가지기때문 )
         * 람다는 람다를 감싸고있는 메소드 또는 클래스와 스코프가 같다
         * */

        //람다
        IntConsumer printInt = n -> System.out.println(n + baseNumber);
        printInt.accept(baseNumber);
    }
}
