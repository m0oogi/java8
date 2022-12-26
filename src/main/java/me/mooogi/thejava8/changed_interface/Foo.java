package me.mooogi.thejava8.changed_interface;

public interface Foo {
    /**
     * 기본메소드(detault)
     * - 해당 인터페이스를 구현한 클래스를 깨트리지 않고 새 기능을 추가할 수 있다
     * - 리스크가 있는 동작이므로 반드시 @implSpec 을 작성하도록
     * 스태틱메소드(static)
     */
    void printName();

    // !!인터페이스에 메소드를 새로 선언하게 되면 구현하는 모든 클래스에서 해당 메소드를 구현해 줘야한다
    // 그러지말고 default를 주면됨
    // 컴파일 에러는 안나지만 구현체에 따라 에러가 발생할 수 있다
    // 구현하는 쪽에서 재정의도 가능
    /**
     * @implSpec
     * 이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    static void printAnything() {
        System.out.println("Foo");
    }

    //equals, hashcode 등 Object 메소드들은 default 불가
    // 추상메소드로 선언하는건 괜찮음 <- 여기서 toString은 추상메소드로 치지도 않음
    String toString();

    String getName();
}
