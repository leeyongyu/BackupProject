package hello2.core.order;

import hello2.core.discount.DiscountPolicy;
import hello2.core.discount.FixDiscountPolicy;
import hello2.core.discount.RateDiscountPolicy;
import hello2.core.member.Member;
import hello2.core.member.MemberReopsitory;
import hello2.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final MemberReopsitory memberReopsitory;
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존

    //@Autowired  // @RequiredArgsConstructor로 생성 가능
//    public OrderServiceImpl(MemberReopsitory memberReopsitory, DiscountPolicy discountPolicy) {
//        this.memberReopsitory = memberReopsitory;
//        this.discountPolicy = discountPolicy;
//    }
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberReopsitory.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberReopsitory getMemberReopsitory(){
        return memberReopsitory;
    }
}
