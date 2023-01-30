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
//
//    public Long getWizard_id() {
//        return wizard_id;
//    }
//
//    public void setWizard_id(Long wizard_id) {
//        this.wizard_id = wizard_id;
//    }
//
//    public String getWizard_name() {
//        return wizard_name;
//    }
//
//    public void setWizard_name(String wizard_name) {
//        this.wizard_name = wizard_name;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public LocalDate getJoined_date() {
//        return joined_date;
//    }
//
//    public void setJoined_date(LocalDate joined_date) {
//        this.joined_date = joined_date;
//    }
//
//    public String getActive_wizard() {
//        return active_wizard;
//    }
//
//    public void setActive_wizard(String active_wizard) {
//        this.active_wizard = active_wizard;
//    }
}
