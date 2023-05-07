package hello;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hello.entity.Member;
import hello.entity.MemberType;
import hello.entity.Team;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		try {
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);
			
			Member member = new Member();
			//member.setId(101L);
			member.setName("member1");
			member.setTeam(team); // 단방향 매핑(올바른 예)
			//member.setMemberType(MemberType.USER);
			
			
			//역방향(주인이 아닌 방향)만 연관관계 설정(Member의 FK=NULL)
			// team.getMembers().add(member);
			
			em.persist(member); // 엔티티 저장
			
			em.flush(); // DB에 쿼리 보냄
			em.clear(); // 캐시 비움.(뒤에 조회 보려고씀)
			
			Member findMember = em.find(Member.class, member.getId());
			Team findTeam = findMember.getTeam();
			
			//역방향 조회
			//Team findTeam2 = em.find(Team.class, team.getId());
			//int memberSize = findTeam.getMembers().size();
			findTeam.getName();
			
			List<Member> members = findTeam.getMembers();
			for(Member member1 : members) {
				System.out.println("member1 = " + member1);
			}
			
			tx.commit(); // COMMIT;
		}catch (Exception e) {
			tx.rollback();
		}finally {
			em.close();
		}
		emf.close();
	}

}
