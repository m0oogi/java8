import me.mooogi.thejava8.covariance.Covariance;
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

        Covariance.test();
    }
}
