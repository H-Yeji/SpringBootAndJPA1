package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //*
@DiscriminatorColumn(name = "dtype") //*
@Getter @Setter
public abstract class Item { //*abstract 추가 - 추상 클래스

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비지니스 로직==//
    /**
     * stock 증가하는 로직
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    /**
     * stock 감소하는 로직
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) { //재고가 0보다 적을 시 예외 발생
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock; //통과하면 남은 수량으로 등록
    }



}
