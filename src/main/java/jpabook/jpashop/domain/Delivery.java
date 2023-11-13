package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    //order와 1:1 연관관계
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) //*옵션
    private Order order;

    @Embedded //*
    private Address address;

    @Enumerated(EnumType.STRING) //*
    private DeliveryStatus status;
}
