package me.mooogi.thejava8.functional_interface;

@FunctionalInterface
public interface RunSomething {
    int doIt(int input);

    static void printName(){
        System.out.println("aaa");
    }

    default void printAge() {
        System.out.println("40");
    }
}
