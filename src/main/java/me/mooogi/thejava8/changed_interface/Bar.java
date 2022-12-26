package me.mooogi.thejava8.changed_interface;

public interface Bar {
    //Foo 에서 제공하는걸 제공하고 싶지 않다면 extends Foo 후
    //void printNameUpperCase();

    default void printNameUpperCase() {
        System.out.println("BAR");
    }
}
