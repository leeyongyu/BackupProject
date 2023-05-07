package hello.jdbc.repository;


import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 *  SQLExceptionTranslator 추가
 */
@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository{

    private final DataSource dataSource;
    private final SQLExceptionTranslator exTranslator;

    public MemberRepositoryV4_2(DataSource dataSource){
        this.dataSource = dataSource;
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    @Override
    public Member save(Member member){
        String sql = "INSERT INTO MEMBER(MEMBER_ID, MONEY) VALUES(?,?)";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,member.getMemberId());
            pstmt.setInt(2,member.getMoney());
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw exTranslator.translate("save",sql,e);
        }finally {
            close(con,pstmt,null);
        }

        return null;
    }

    @Override
    public Member findById( String memberId){
        String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);

            rs = pstmt.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("MEMBER_ID"));
                member.setMoney(rs.getInt("MONEY"));
                return member;
            }else{
                throw new NoSuchElementException("member not found memberId="+ memberId);
            }

        }catch(SQLException e){
            throw exTranslator.translate("findById",sql,e);
        }finally{
            //connection은 여기서 닫지 않는다.
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(pstmt);
            //JdbcUtils.closeConnection(con);
        }
    }

    @Override
    public void update(String memberId, int money){
        String sql = "UPDATE MEMBER SET MONEY=? WHERE MEMBER_ID=?";

        Connection con = null;
        PreparedStatement pstmt =null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,money);
            pstmt.setString(2,memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}",resultSize);
        }catch (SQLException e){
            throw exTranslator.translate("update",sql,e);
        }finally {
            //connection은 여기서 닫지 않는다.
            JdbcUtils.closeStatement(pstmt);
        }
    }

    @Override
    public void delete(String memberId){
        String sql = "DELETE FROM MEMBER WHERE MEMBER_ID = ?";

        Connection con = null;
        PreparedStatement pstmt =null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}",resultSize);
        }catch (SQLException e){
            throw exTranslator.translate("delete",sql,e);
        }finally {
            close(con,pstmt,null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){

        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
        DataSourceUtils.releaseConnection(con,dataSource);
        //JdbcUtils.closeConnection(con);
    }
    private Connection getConnection(){
        //트랜잭션 동기 주의!
        //트랜잭션 동기화를 사용하려면 DataSourceUtils.getConnection을 사용해야 한다.
        Connection con = DataSourceUtils.getConnection(dataSource);
        log.info("get Connection={}, class={}",con, con.getClass());
        return con;
    }
}
