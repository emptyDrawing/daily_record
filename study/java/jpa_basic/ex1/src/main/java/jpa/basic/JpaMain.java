/*
 * Decompiled with CFR 0.152.
 */
package jpa.basic;

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
            // 비영속
            Member member = new Member();
            member.setUsername("야2");
            // member.setRoleType(RoleType.ADMIN);
            
            // 영속
            em.persist(member);
            // 준영속
            //em.detach(member);
            // 삭제
            //em.remove(member);

            // 플러시 모드 셋팅
            // em.setFlushMode(FlushModeType.AUTO); // 커밋이나 JPQL 실행시 자동으로 ( 기본값 )
            // em.setFlushMode(FlushModeType.COMMIT); // 커밋시에만

            // JPQL 맛보기
            // List<Member> resultList = em.createQuery("select m from Member as m", Member.class)
            //     .setFirstResult(5)    
            //     .setMaxResults(8)
            //     .getResultList();

            // for (Member member : resultList) {
            //     System.out.println(member);
            // }    
            
            // Update
            // Member findTest = em.find(Member.class, 1L);
            // System.out.println(findTest); // Member [id=1, name=TEST]
            // findTest.setName("changeName");

            // Insert
            // Member member = new Member();
            // member.setId(2L);
            // member.setName("TEST2");
            // em.persist(member);
            
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}