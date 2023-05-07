package hello.jdbc.repository;


import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.util.Comparator;


/**
 *  JdbcTemplate 사용
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository{

    private final JdbcTemplate template;

    public MemberRepositoryV5(DataSource dataSource){
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member){
        String sql = "INSERT INTO MEMBER(MEMBER_ID, MONEY) VALUES(?,?)";

        int update = template.update(sql,member.getMemberId(),member.getMoney());

        return member;
    }

    @Override
    public Member findById(String memberId){
        String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
        Member member = template.queryForObject(sql,memberRowMapper(),memberId);

        return member;
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) ->{
            Member member = new Member();
            member.setMemberId(rs.getString("member"));
            member.setMoney(rs.getInt("money"));

            return member;
        };
    }

    @Override
    public void update(String memberId, int money){
        String sql = "UPDATE MEMBER SET MONEY=? WHERE MEMBER_ID=?";

        int update = template.update(sql,money,memberId);
    }

    @Override
    public void delete(String memberId){
        String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

        int delete = template.update(sql,memberId);
    }
}
