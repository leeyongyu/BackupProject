package yong.hellospring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yong.hellospring.domain.Member;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();
    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId=memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}