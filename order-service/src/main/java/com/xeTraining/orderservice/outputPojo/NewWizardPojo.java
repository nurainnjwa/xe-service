package com.xeTraining.orderservice.outputPojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewWizardPojo {

    @JsonProperty("wizard_id")
    private Long wizard_id;

    @JsonProperty("wizard_name")
    private String wizard_name;

    @JsonProperty("age")
    private int age;

    @JsonProperty("active_wizard")
    private String active_wizard;


}
