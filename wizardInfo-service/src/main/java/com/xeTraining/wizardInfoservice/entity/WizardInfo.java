package com.xeTraining.wizardInfoservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wizard_info")
@Getter
@Setter
public class WizardInfo {

    public WizardInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wizard_id",nullable = false)
    private Long wizard_id;


    @Column(name = "wizard_name")
    private String wizard_name;

    @Column(name = "age")
    private int age;

    @Column(name = "joined_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate joined_date;

    @Column(name = "active_wizard")
    private String active_wizard;

    public WizardInfo(Long wizard_id, String wizard_name,
                      int age, LocalDate joined_date,
                      String active_wizard) {
        this.wizard_id = wizard_id;
        this.wizard_name = wizard_name;
        this.age = age;
        this.joined_date = joined_date;
        this.active_wizard = active_wizard;
    }
}
