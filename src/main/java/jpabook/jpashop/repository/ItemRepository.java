package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    /**
     * 상품 등록
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item); //item을 신규로 저장(등록)
        } else { //이미 등록된 상품이면
            em.merge(item); //merge = update와 비슷
        }
    }

    /**
     * 상품 조회
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /**
     * 상품 전체 조회
     */
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
