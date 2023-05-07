package hello2.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberReopsitory memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberReopsitory memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberReopsitory getMemberReopsitory(){
        return memberRepository;
    }
}
