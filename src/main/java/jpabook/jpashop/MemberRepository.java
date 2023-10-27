package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository //내부에 component가 있으므로 component의 대상이 되어 스프링 빈에 등록해줌
public class MemberRepository {

    @PersistenceContext
    private EntityManager em; //persistenceContext 어노테이션이 있으면 스프링 부트가 entityManager를 주입해줌

    public Long save(Member member) { //회원 등록
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) { //회원 조회
        return em.find(Member.class, id);
    }

}

