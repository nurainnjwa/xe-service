package com.xeTraining.wizardInfoservice.service;

import com.xeTraining.wizardInfoservice.entity.WizardInfo;
import com.xeTraining.wizardInfoservice.repository.WizardInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WizardInfoService {

    @Autowired
    WizardInfoRepository repository;

    public List<WizardInfo> getWizardInfo(){
        return repository.findAll();
    }

    public String addWizardInfo(WizardInfo wizardInfo){
        repository.save(wizardInfo);
        return "Wizard added successfully";
    }

    public String deleteWizardInfo(Long id){
        repository.findById(id).orElseThrow(
                () -> new RuntimeException("ID Not found. Please enter valid ID")
        );
        repository.deleteById(id);
        return "Wizard ID:" + id +"successfully deleted";
    }

    public String updateWizardInfo(WizardInfo wizardInfo){
        repository.findById(wizardInfo.getWizard_id()).orElseThrow(
                () -> new RuntimeException("ID Not found. Please enter valid ID")
        );
        repository.save(wizardInfo);
        return "Wizard updated successfully";
    }
}
