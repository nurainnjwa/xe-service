package com.xeTraining.magicWandCatalogueService.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "magicWand")
@Getter
@Setter
@NoArgsConstructor
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

    public MagicWand(Long wand_id, String magic_wand_name,
                     String magic_wand_desc, int age_limit,
                     int magic_wand_stock) {
        this.wand_id = wand_id;
        this.magic_wand_name = magic_wand_name;
        this.magic_wand_desc = magic_wand_desc;
        this.age_limit = age_limit;
        this.magic_wand_stock = magic_wand_stock;
    }
}
