package hello2.core;

import hello2.core.member.Grade;
import hello2.core.member.Member;
import hello2.core.member.MemberService;
import hello2.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {


        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //MemberService memberService = new MemberServiceImpl();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = "+ findMember.getName());

    }
}
