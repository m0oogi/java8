package me.mooogi.thejava8;

import java.util.*;
import java.util.function.*;
import me.mooogi.thejava8.changed_interface.ChangedInterface;
import me.mooogi.thejava8.changed_interface.DefaultFoo;
import me.mooogi.thejava8.changed_interface.Foo;
import me.mooogi.thejava8.functional_interface.FE;
import me.mooogi.thejava8.functional_interface.Greeting;
import me.mooogi.thejava8.functional_interface.Plus10;
import me.mooogi.thejava8.functional_interface.RunSomething;

public class App {

    public static void main(String[] args) {
        FE.feAndLambda(false); //함수형 힌터페이스 & 람다
        FE.scopes(false); //변수 캡처(로컬, 임시, 람다의 스코프 관계)
        ChangedInterface.changedInterface(false); //인터페이스 변경점 default, static
        ChangedInterface.streams(false); //stream
    }
}
