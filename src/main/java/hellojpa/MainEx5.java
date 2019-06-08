package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEx5 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // 비영속 상태
            Member member = new Member();
            member.setName("맴버");

            transaction.commit();
            member.setName("맴버2");
            em.find(Member.class, member.getId());

        } catch(Exception e) {
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
