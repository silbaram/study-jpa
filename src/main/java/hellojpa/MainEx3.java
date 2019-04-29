package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainEx3 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        try {
            Member member = new Member();
            member.setAge(1);
            member.setName("맴버 1");
            em.persist(member);

            Team team = new Team();
            team.setName("팀 1");
            em.persist(team);

            member.setTeam(team);

            transaction.commit();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("member id : " + findMember.getId() + " member age :  " + findMember.getAge() + " member name :  " + findMember.getName());

            Team findTeam = findMember.getTeam();
            System.out.println("team id : " + findTeam.getId() + " team name :  " + findTeam.getName());

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
