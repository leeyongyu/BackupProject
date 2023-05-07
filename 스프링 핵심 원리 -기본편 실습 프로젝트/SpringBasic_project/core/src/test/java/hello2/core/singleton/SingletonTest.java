package hello2.core.singleton;

import hello2.core.AppConfig;
import hello2.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //조회1
        MemberService memberService1 = appConfig.memberService();
        //조회2
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = "+ memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonSerivce1 = SingletonService.getInstance();
        SingletonService singletonSerivce2 = SingletonService.getInstance();

        System.out.println("singleton1 = " + singletonSerivce1);
        System.out.println("singleton2 = " + singletonSerivce2);

        //equal = 값이 같아야 함
        //same = 인스턴스가 같아야함
        assertThat(singletonSerivce1).isSameAs(singletonSerivce2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //조회1
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        //조회2
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = "+ memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
