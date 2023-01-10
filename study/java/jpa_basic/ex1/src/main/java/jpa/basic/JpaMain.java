package jpa.basic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] stringArray) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        
        // Trasaction 단위마다 생성
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            
            em.persist(team);

            Member member = new Member();
            member.setUsername("가나다");
            member.changeTeam(team);

            Member member3 = new Member();
            member3.setUsername("라마바");
            member3.changeTeam(team);
            
            // member.setTeamId(team.getId());
            em.persist(member3);

            em.flush();
            em.clear();

            Member findMember = em.find(Member.class, member.getId());
            List<Member> findMembers = findMember.getTeam().getMembers();

            for (Member member2 : findMembers) {
                System.out.println(member2);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}