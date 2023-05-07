package yong.hellospring.service;

import yong.hellospring.domain.Member;
import yong.hellospring.repository.MemberRepository;
import yong.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member){
        validateDuplicateMember(member); // 중복회원검증
        memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m->{
           throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
