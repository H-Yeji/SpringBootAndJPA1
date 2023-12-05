package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    /**
     * 아이템 등록 폼 열기
     */
    @GetMapping("/items/new")
    public String createForm(Model model) {

        model.addAttribute("form", new BookForm());
        return "/items/createItemForm";
    }

    /**
     * 아이템 등록 진행
     */
    @PostMapping("/items/new")
    public String create(@Valid BookForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "items/createItemForm";
        }

        //실무에선 setter을 이렇게 사용하지 않고 엔티티에 생성 메서드 만들어서 사용하는게 좋음
        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items"; //저장된 책 목록으로 리다이렉트(url)
    }

    /**
     * 상품 목록 조회
     */
    @GetMapping("/items")
    public String itemList(Model model) {

        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);

        return "items/itemList";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/items/{itemId}/edit") // {}로 변경값 변수명 넣어 사용하는게 pathVariable
    public String updateItemForm(@PathVariable Long itemId, Model model) {

        Book item = (Book) itemService.findOne(itemId); //책만 사용한다는 가정

        //기존에 저장된 데이터들 그대로 가지고 와서 폼에서 보여줌
        BookForm form = new BookForm(); //수정을 위해 book 엔티티가 아니라 기존의 bookform 데이터를 보냄
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    /**
     * 상품 수정 실행
     */
    @PostMapping("items/{itemId}/edit")
    public String edit(@ModelAttribute("form") BookForm form) {

        Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items"; //수정  완료 후 리다이렉트로 목록 띄워줌
    }

}
