package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //private DiscountPolicy discountPolicy; //구체화에 의존하지 않고 추상화에만 의존

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자 주입 -롬복으로 수정
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy
//            discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //주문요청이 오면 회원조회후
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인가 측정

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용도 -> MemberServiceIpl 호출할때 생성된 memberRepository랑 같은객체?
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
