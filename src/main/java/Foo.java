import java.util.function.Function;
import me.mooogi.thejava8.covariance.Covariance;
import me.mooogi.thejava8.functional_interface.Plus10;
import me.mooogi.thejava8.functional_interface.RunSomething;

public class Foo {

    public static void main(String[] args) {
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

        System.out.println();
    }
}
