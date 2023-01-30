package com.xeTraining.magicWandCatalogueService.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "magicWand")
@Getter
@Setter
public class MagicWand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wand_id", nullable = false)
    private Long wand_id;

    @Column(name = "magic_wand_name")
    private String magic_wand_name;

    @Column(name = "magic_wand_desc")
    private String magic_wand_desc;

    @Column(name = "age_limit")
    private int age_limit;

    @Column(name = "magic_wand_stock")
    private int magic_wand_stock;

}
