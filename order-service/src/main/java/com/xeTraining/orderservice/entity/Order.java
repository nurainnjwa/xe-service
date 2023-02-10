package com.xeTraining.orderservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_service")
@Getter
@Setter
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



}
