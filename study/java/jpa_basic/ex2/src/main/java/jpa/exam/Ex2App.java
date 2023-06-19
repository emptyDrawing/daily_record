package jpa.exam;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.exam.domain.Address;
import jpa.exam.domain.Member;


/**
 * Hello world!
 *
 */
public class Ex2App 
{
    public static void main( String[] args )
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        
        // Trasaction 단위마다 생성
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("test2");
            member.setHomeAddress(new Address("서울", "갈현로33길 8-8", "33012"));

            member.getFavorivateFooos().add("라면");
            member.getFavorivateFooos().add("우동");
            member.getFavorivateFooos().add("냉면");

            member.getAddressHistory().add(new Address("서울", "명륜길 97", "12345"));
            member.getAddressHistory().add(new Address("서울", "몰라", "00000"));

            em.persist(member);

            em.flush();
            em.clear();
            
            System.out.println("================================================");
            System.out.println("================================================");
            System.out.println("================================================");

            Member findMember = em.find(Member.class, member.getId());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();    }
}
