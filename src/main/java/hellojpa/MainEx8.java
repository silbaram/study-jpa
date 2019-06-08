package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEx8 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Member member = new Member();

        try {
            // 비영속 상태
            member.setName("맴버");

            // 영속 상태
            em.persist(member);
            member.setName("맴버2");

            // 삭제
            em.remove(member);

            transaction.commit();
            
        } catch(Exception e) {
            System.out.println("Exception 발생");
            System.out.println(e);
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
