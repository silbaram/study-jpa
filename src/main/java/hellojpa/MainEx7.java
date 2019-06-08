package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEx7 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // 비영속 상태
            Member member = new Member();
            member.setName("맴버");

            // 영속 상태
            em.persist(member);
            member.setName("맴버2");

            transaction.commit();

            transaction.begin();

            // 준영속 상태
            em.detach(member);
            member.setName("맴버3");

            transaction.commit();
            
        } catch(Exception e) {
            System.out.println("Exception 발생");
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
