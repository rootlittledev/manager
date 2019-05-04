package com.project.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long Id;

    @JsonProperty("product_name")
    String name;
    Double price;
    String currency;

    @JsonProperty("order_date")
    String orderDate;

}
