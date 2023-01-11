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

            Movie movie = new Movie();
            movie.setDirector("가");
            movie.setName("A");
            movie.setName("신규영화");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            System.out.println(em.find(Movie.class, movie.getId()).getDirector());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}