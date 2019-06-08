package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEx9 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member = new Member();

        try {
            // 비영속 상태
            member.setName("영속");

            // 영속 상태
            em.persist(member);
            transaction.commit();

            transaction.begin();
            // 비영속 상태
            em.detach(member);

            // 병합
            member.setName("다시 비영속");
            em.merge(member);
            transaction.commit();

            transaction.begin();
            Member member1 = new Member();
            member1.setName("비영속 머지");

            em.merge(member1);
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
