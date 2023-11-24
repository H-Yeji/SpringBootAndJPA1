package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 스캔에서 자동으로 스프링 빈으로 등록해줌
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    /*
    @PersistenceContext //스프링이 엔티티 매니저를 만들어서 주입시켜줌 -> database와 연결
    private EntityManager em;
    */

    public void save(Member member) { //저장
        em.persist(member);
    }

    public Member findOne(Long id) { //조회
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {

        //jpql
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("selet m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
