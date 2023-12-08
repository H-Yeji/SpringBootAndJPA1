package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 상품 등록
     */
    @Transactional //등록이라 "쓰기"가 가능해야하므로
    public void saveItem(Item item) {

        itemRepository.save(item);
    }

    @Transactional //변경감지
    public void updateItem(Long itemId, String name, int price, int stockQuantity, String author, String isbn) {

        Book book = (Book) itemRepository.findOne(itemId);
        book.change(name, price, stockQuantity, author, isbn);

    }

    /**
     * 상품 전체 조회
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 상품 하나 조회
     */
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
