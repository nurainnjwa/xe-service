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
        return "Wizard ID: " + id +" successfully deleted";
    }

    public String updateWizardInfo(Long wizard_id,WizardInfo wizardInfo){
        WizardInfo foundWizard = repository.findById(wizard_id).
                orElseThrow(() -> new RuntimeException("ID Not found. Please enter valid ID")
        );
        foundWizard.setWizard_name(wizardInfo.getWizard_name());
        foundWizard.setAge(wizardInfo.getAge());
        foundWizard.setJoined_date(wizardInfo.getJoined_date());
        foundWizard.setActive_wizard(wizardInfo.getActive_wizard());
        repository.save(foundWizard);
        return "Wizard updated successfully";
    }
}
