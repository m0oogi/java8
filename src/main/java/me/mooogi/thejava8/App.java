package me.mooogi.thejava8;

import me.mooogi.thejava8.changed_interface.ChangedInterface_Example;
import me.mooogi.thejava8.functional_interface.FE_Example;
import me.mooogi.thejava8.optional.Optional_Example;
import me.mooogi.thejava8.stream.Streams_Example;

public class App {

    public static void main(String[] args) {
        //        FE_Example.feAndLambda(); //함수형 힌터페이스 & 람다
        //        FE_Example.scopes(); //변수 캡처(로컬, 임시, 람다의 스코프 관계)
        //        ChangedInterface_Example.changedInterface(); //인터페이스 변경점 default, static
        //        Streams_Example.streams(); //stream
        Optional_Example.optional(); //optional
    }
}
