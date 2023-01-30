package com.xeTraining.wizardInfoservice.controller;

import com.xeTraining.wizardInfoservice.entity.WizardInfo;
import com.xeTraining.wizardInfoservice.service.WizardInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class WizardInfoController {

//    @Autowired
//    private WizardInfoRepository wizardInfoRepository;

    @Autowired
    WizardInfoService service;

    private WizardInfo wizardInfo = new WizardInfo();


    @GetMapping("/wizardInfoList")
    public List<WizardInfo> getList(){
        return service.getWizardInfo();
    }

    @PostMapping("/add")
    public String createWizardInfo(@RequestBody WizardInfo wizardInfo) {
        return service.addWizardInfo(wizardInfo);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWizardInfo(@PathVariable(value = "id")long id){
        return service.deleteWizardInfo(id);
    }

    @PutMapping("/update")
    public String updateWizardInfo(@RequestBody WizardInfo wizardInfo){
        return service.updateWizardInfo(wizardInfo);
    }
}
