package hellojpa;

import hellojpa.entity.Member;
import hellojpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class MainEx4 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction =  em.getTransaction();

        try {
            transaction.begin();

            Member m1 = new Member();
            m1.setAge(10);
            m1.setName("멤버 1");
            em.persist(m1);

            Member m2 = new Member();
            m2.setAge(15);
            m2.setName("맴버 2");
            em.persist(m2);

            Team team = new Team();
            team.setName("팀");

            List<Member> memberList = new ArrayList<Member>();
            memberList.add(m1);
            memberList.add(m2);
            team.setMemberList(memberList);
            em.persist(team);

            m1.setTeam(team);
            m2.setTeam(team);

            transaction.commit();

            Member findMember1 = em.find(Member.class, m1.getId());
            System.out.println("findMember1 id : " +  findMember1.getId() + " findMember1 name : " + findMember1.getName());

            Team findTeam = findMember1.getTeam();
            System.out.println("findTeam id : " +  findTeam.getId() + " findTeam name : " + findTeam.getName());

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
