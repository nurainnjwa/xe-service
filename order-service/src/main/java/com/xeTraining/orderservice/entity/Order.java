package com.xeTraining.orderservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_service")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long order_id;

    @Column(name = "wizard_id")
    private Long wizard_id;

    @Column(name = "wand_id")
    private Long wand_id;

    @Column(name = "wizard_name")
    private String wizard_name;

    @Column(name = "age")
    private int age;

    @Column(name = "magic_wand_name")
    private String magic_wand_name;

    @Column(name = "magic_wand_desc")
    private String magic_wand_desc;

    @Column(name = "magic_wand_stock")
    private int magic_wand_stock;

    @Column(name = "age_limit")
    private int age_limit;

    public Order(Long order_id, Long wizard_id, Long wand_id,
                 String wizard_name, int age,
                 String magic_wand_name, String magic_wand_desc,
                 int magic_wand_stock, int age_limit) {
        this.order_id = order_id;
        this.wizard_id = wizard_id;
        this.wand_id = wand_id;
        this.wizard_name = wizard_name;
        this.age = age;
        this.magic_wand_name = magic_wand_name;
        this.magic_wand_desc = magic_wand_desc;
        this.magic_wand_stock = magic_wand_stock;
        this.age_limit = age_limit;
    }
}
