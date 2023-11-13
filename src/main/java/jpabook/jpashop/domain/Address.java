package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

//* 전체
@Embeddable
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;
}
