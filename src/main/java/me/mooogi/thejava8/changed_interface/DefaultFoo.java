package me.mooogi.thejava8.changed_interface;

public class DefaultFoo implements Foo, Bar {

    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println(this.name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    // default도 Override 할 수 있다
    // + 두가지의 인터페이스를 구현할때 default 메소드가 겹치는경우 해당 메소드를 Override 해줘야함
    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }
}
