package hello2.core.member;

public interface MemberReopsitory {

    void save(Member member);

    Member findById(Long memberId);
}
