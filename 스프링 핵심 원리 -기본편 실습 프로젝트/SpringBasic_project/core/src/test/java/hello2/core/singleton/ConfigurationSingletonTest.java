package hello2.core.singleton;

import hello2.core.AppConfig;
import hello2.core.member.MemberReopsitory;
import hello2.core.member.MemberServiceImpl;
import hello2.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberReopsitory memberReopsitory = ac.getBean("memberRepository" , MemberReopsitory.class);

        MemberReopsitory memberReopsitory1 = memberService.getMemberReopsitory();
        MemberReopsitory memberReopsitory2 = orderService.getMemberReopsitory();

        System.out.println("memberService -> memberRepository = " + memberReopsitory1);
        System.out.println("orderService -> memberRepository = " + memberReopsitory2);
        System.out.println("memberRepository = "+ memberReopsitory);

        assertThat(memberService.getMemberReopsitory()).isSameAs(memberReopsitory);
        assertThat(orderService.getMemberReopsitory()).isSameAs(memberReopsitory);
    }
    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = "+ bean.getClass());
    }
}
