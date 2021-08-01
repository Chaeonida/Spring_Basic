package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration //설정 정보 , bean 등록시 스프링 컨테이너에 등록된다
public class AppConfig {
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    } //생성자 주입
//    //MemberServiceImpl을 만들고 이 인터페이서는 구현체로서 memoryMamberRepository쓴다고 주입
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }

    //문제점
    //@Bean memberService -> new MemoryMemberRepository() 호출
    //@Bean orderService-> new MemoryMemberRepository() 호출
    // -> new MemoryMemberRepository() 를 두번 호출하게댐 -> 싱글톤 깨지나?
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy(); //할인정책 바꾸고싶으면 이부분만 수정 하면 됨
    }
}

