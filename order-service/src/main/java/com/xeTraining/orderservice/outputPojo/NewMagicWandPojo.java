package com.xeTraining.orderservice.outputPojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMagicWandPojo {

    @JsonProperty("wand_id")
    private Long wand_id;

    @JsonProperty("magic_wand_name")
    private String magic_wand_name;

    @JsonProperty("age_limit")
    private int age_limit;

    @JsonProperty("magic_wand_stock")
    private int magic_wand_stock;

    @JsonProperty("magic_wand_desc")
    private String magic_wand_desc;
}
