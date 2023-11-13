package jpabook.jpashop.domain;

import jakarta.persistence.*;
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

    private List<Category> categories = new ArrayList<>();


}
