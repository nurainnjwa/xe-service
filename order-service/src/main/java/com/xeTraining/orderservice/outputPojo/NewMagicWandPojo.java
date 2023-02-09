package com.xeTraining.orderservice.outputPojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMagicWandPojo {

    private Long wand_id;

    private String magic_wand_name;

    private int age_limit;

    private int magic_wand_stock;

    private String magic_wand_desc;
}
