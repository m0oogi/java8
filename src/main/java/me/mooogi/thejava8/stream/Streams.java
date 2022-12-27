package me.mooogi.thejava8.stream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void streams() {
        List<String> names = new ArrayList<>();
        names.add("foo");
        names.add("bar");
        names.add("apple");
        names.add("banana");
        names.add("orange");

        /** stream은 데이터를 담는 저장소가 아니다
         * 무제한일 수도 있다. (Short Circuit 메소드를 사용해 제한할 수 있다.)
         * 중계 오퍼레이터와 종료 오퍼레이터가 있음
         * 중계형은 Stream을 리턴 종료는 다른 타입을 리턴
         */

        //Functional 하다 -> 데이터를 건드리지 않는다.
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println);

        System.out.println("=====================");

        //중계 오퍼레이터들은 근본적으로 lazy 하다.
        //종료형 오퍼레이터가 없으면 동작하지 않음
        List<String> collect = names
            .stream()
            .map(s -> {
                System.out.println(s);
                return s.toUpperCase();
            })
            .collect(Collectors.toList());

        System.out.println("=====================");

        //병렬 스트림이 항상 빠르지는 않다
        //스레드 생성, 컨텍스트 체인징
        //데이터가 방대하게 큰경우만 유용, 대부분은 stream() 사용
        List<String> collect2 = names
            .parallelStream()
            .map(s -> {
                System.out.println(s + " " + Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .collect(Collectors.toList());
        collect2.forEach(System.out::println);

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream().filter(c -> c.getTitle().startsWith("spring")).forEach(System.out::println);

        System.out.println("closed 되지않은 수업");
        //springClasses.stream().filter(s -> !s.isClosed()).forEach(System.out::println);
        springClasses.stream().filter(Predicate.not(OnlineClass::isClosed)).forEach(System.out::println);

        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream().map(OnlineClass::getTitle).forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> mooogiEvents = new ArrayList<>();
        mooogiEvents.add(springClasses);
        mooogiEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        mooogiEvents.stream().flatMap(Collection::stream).forEach(c -> System.out.println(c.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 배고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1).skip(10).limit(10).forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean test = javaClasses.stream().anyMatch(c -> c.getTitle().contains("Test"));
        System.out.println(test);

        System.out.println("스프링 수업 중 제목에 spirng이 들어간 제목만 모아서 List로");
        List<String> classIncludesSpring = springClasses
            .stream()
            .map(OnlineClass::getTitle)
            .filter(title -> title.contains("spring"))
            .collect(Collectors.toList());
        classIncludesSpring.forEach(System.out::println);
    }
}
