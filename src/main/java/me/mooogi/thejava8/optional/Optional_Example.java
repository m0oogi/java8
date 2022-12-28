package me.mooogi.thejava8.optional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import me.mooogi.thejava8.stream.OnlineClass;

public class Optional_Example {

    public static void optional() {
        /* NPE가 발생하는 이유
         *  null을 리턴한다 && null 체크를 안한다 끗
         *  메소드내에서 값을 리턴할 수 없는경우 선택지
         *  - 예외를 던진다 (비싸다, stacktrace를 만드니까)
         *  - null을 리턴한다 (클라이언트 코드에서 검증이 꼭 필요하다)
         *  - Optional을 리턴한다 (명시적으로 빈값일 수 있음을 알리고, 빈값인 경우에 대한 처리를 강제한다)
         *
         *  Optional
         *  오직 값 하나가 들어있을수도 없을 수도 있는 컨테이너
         *
         *  !! 주의사항 !!
         *  리턴값으로만 사용하기를 권장
         *  Optional을 리턴하는 메소드에서 null을 리턴하지 않기
         *  프리미티브 타입을 리턴하는 경우 OptionalInt, OptionalLong ... 사용
         */

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        //Duration studyDuration = spring_boot.getProgress().getStudyDuration(); //NPE

        //지금까지 이렇게해왔고 오류가 생길확률이 다분
        /* getProgress의 리턴타입을 Optional<Progress>로 변경했음
         * return Optional.ofNullable(this.progress); <- 요건 null 가능
         * return Optional.of(this.progress); <- 이건 progress가 null이면 터짐, 널이아님을 가정
         * */
        Optional<Progress> progress = spring_boot.getProgress();
        progress.ifPresent(value -> System.out.println(value.getStudyDuration()));
        /* Bad Cases
         * - Map의 키로 Optional 사용 - Map의 기본! key는 null이 아니다
         * - 클래스 필드에 Optional 사용
         * - primitive 타입을 직접 넣는것 - 들어는 가지만 boxing unboxing이 발생한다(성능저하)
         * - Optional을 리턴하기로 했으면 null을 리턴하지 말자 - 받는쪽은 지옥임(정 넣을게 없으면 Optional.empty())
         * - 다른 컨테이너 타입을 Optional로 다시 감싸지 마라 - collections, maps, streams and optionals (이미 비어있음을 판단가능한 것들)
         * */

        //Optional 만들기
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> spring = springClasses.stream().filter(oc -> oc.getTitle().startsWith("spring")).findFirst();

        boolean present = spring.isPresent();
        boolean present2 = spring.isEmpty(); // 11부턴 이런것도 있다
        System.out.println(present);

        //        OnlineClass onlineClass = spring.get(); //있으면 상관없는데 없는경우 NoSuchException
        //        System.out.println(onlineClass.getTitle());

        spring.ifPresent(oc -> System.out.println(oc.getTitle()));

        //있으면 있는걸로 없으면 넣어준 인스턴스로(근데 있어도 createNewSpringClass가 동작하긴 함)
        OnlineClass onlineClass = spring.orElse(createNewSpringClass()); //Functional이아니라 제네릭의 인스턴스를 넣는다
        //동작안하게하려면
        OnlineClass onlineClass2 = spring.orElseGet(() -> createNewSpringClass()); //Functional이아니라 제네릭의 인스턴스를 넣는다
        OnlineClass onlineClass3 = spring.orElseGet(Optional_Example::createNewSpringClass); //Functional이아니라 제네릭의 인스턴스를 넣는다
        //상수일때는 orElse를 뭔가 동작을해서 만들어야되면 orElseGet을 쓰자

        //정 못만드는 경우
        OnlineClass onlineClass4 = spring.orElseThrow(IllegalArgumentException::new);
        //필터에 해당이 되든 안되는 optional로 나옴
        Optional<OnlineClass> onlineClass5 = spring.filter(oc -> oc.getId() > 10);
        Optional<Integer> integer = spring.map(OnlineClass::getId);
        System.out.println(integer.isPresent());

        //문제경우( getProgress를 Optional을 리턴하도록 해뒀는데 이때는..?
        Optional<Optional<Progress>> progress1 = spring.map(OnlineClass::getProgress);
        Optional<Progress> progress2 = progress1.orElseThrow();
        Progress progress3 = progress2.orElseThrow();

        //이건
        Optional<Progress> progress4 = spring.flatMap(OnlineClass::getProgress);
        //아래 두줄과 같다
        Optional<Optional<Progress>> progress5 = spring.map(OnlineClass::getProgress);
        Optional<Progress> progress6 = progress5.orElse(Optional.empty());
        //optional의 flatMap과 stream의 flatMap은 다르다

    }

    private static OnlineClass createNewSpringClass() {
        return new OnlineClass(10, "New spring class", false);
    }
}
