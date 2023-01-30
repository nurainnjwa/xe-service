package com.xeTraining.wizardInfoservice.repository;

import com.xeTraining.wizardInfoservice.entity.WizardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WizardInfoRepository extends JpaRepository<WizardInfo, Long> {
   // WizardInfo findByName(String inputName);
}
