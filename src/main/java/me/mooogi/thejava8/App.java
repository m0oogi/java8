package me.mooogi.thejava8;

import me.mooogi.thejava8.changed_interface.ChangedInterface;
import me.mooogi.thejava8.functional_interface.FE;
import me.mooogi.thejava8.stream.Streams;

public class App {

    public static void main(String[] args) {
        //FE.feAndLambda(); //함수형 힌터페이스 & 람다
        //FE.scopes(); //변수 캡처(로컬, 임시, 람다의 스코프 관계)
        //ChangedInterface.changedInterface(); //인터페이스 변경점 default, static
        Streams.streams(); //stream
    }
}
