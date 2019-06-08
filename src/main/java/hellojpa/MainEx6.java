package hellojpa;

import hellojpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEx6 {
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

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("member : " + findMember.getName());

            // 영속 상태이므로 UPDATE 실행
            findMember.setName("맴버2");

            Member findMember2 = em.find(Member.class, findMember.getId());
            System.out.println("member : " + findMember2.getName());

        } catch(Exception e) {
            transaction.rollback();
        } finally {
            transaction.commit();
            em.close();
            emf.close();
        }
    }
}
