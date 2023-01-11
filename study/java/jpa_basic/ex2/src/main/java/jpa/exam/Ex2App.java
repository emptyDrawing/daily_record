package jpa.exam;

import java.lang.reflect.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import jpa.exam.domain.Book;
import jpa.exam.domain.Order;
import jpa.exam.domain.OrderItem;

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
            Book book = new Book();
            book.setAuthor("가");
            book.setName("JPA");
            
            em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();    }
}
