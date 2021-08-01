package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl();

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();//memberService에는 memberServiceImpl이 들어가있음

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // @Bean 붙인애들  스프링 빈에 넣어 관리해줌
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //config 에서 memberService 이름 메소드 찾기 , 타입은 memberService


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("findMember = " + findMember.getName());
        System.out.println("new findMember = " + member.getName());

    }

}
