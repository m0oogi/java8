package me.mooogi.thejava8.changed_interface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;

public class ChangedInterface_Example {

    public static void changedInterface() {
        Foo foo = new DefaultFoo("mooogi");
        //        foo.printName();
        //        foo.printNameUpperCase();

        //이로인해 변경된점 전부는 아님
        //Iterable
        List<String> names = new ArrayList<>();
        names.add("foo");
        names.add("bar");
        names.add("apple");
        names.add("banana");

        //names.forEach(System.out::println);
        //병렬처리할때 유용하게 쓸수있지않으까
        Spliterator<String> spliterator = names.spliterator(); //쪼갤수있는 기능을 가진 Iterator
        Spliterator<String> spliterator1 = spliterator.trySplit();
        System.out.println("==============================================");
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("==============================");
        while (spliterator1.tryAdvance(System.out::println));
        System.out.println("==============================================");

        names.stream(); //<- 얘도 spliterator 사용
        names.sort(String::compareToIgnoreCase);

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        Comparator<String> compareTo = String::compareTo;
        names.sort(compareToIgnoreCase.reversed());
        names.sort(compareToIgnoreCase.reversed().thenComparing(compareTo));
        names.forEach(System.out::println);
        /**
         * Interface 메소드 a(), b(), c()
         * 아래 abstract Class 메소드 a(), b(), c() <= 이파일 만드는 이유
         * 실제 구현 클래스에서 extends 해서 a만 구현하거나, b만 구현하거나 하는 편의성을 위해
         *
         * 이걸 java8부터는 인터페이스에서 가능
         * 메소드 들에서 default 선언을 해두고 implements 로 사용가능 (extends 안해도되서 이득)
         */
    }
}
