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
            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

            // 
            Member findMember = em.getReference(Member.class, member.getId());
            emf.getPersistenceUnitUtil().isLoaded(findMember);

            

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }

    private static void printMember(Member member) {
        System.out.println("member: " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username: " + username);

        Team team = member.getTeam();
        System.out.println("team: " + team.getName());
        
    }
}