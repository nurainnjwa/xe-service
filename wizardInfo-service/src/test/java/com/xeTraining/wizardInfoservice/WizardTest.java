package com.xeTraining.wizardInfoservice;

import com.xeTraining.wizardInfoservice.entity.WizardInfo;
import com.xeTraining.wizardInfoservice.repository.WizardInfoRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WizardTest {

    @Autowired
    private WizardInfoRepository wizardInfoRepository;

    private static final Logger log = LoggerFactory.getLogger(WizardTest.class);


    @Test
    public void testCreateWizard() {
        WizardInfo wizard = new WizardInfo();
        wizard.setWizard_id(2L);
        wizard.setWizard_name("Fatin");
        wizard.setAge(40);
        wizard.setJoined_date(LocalDate.parse("2022-07-08"));
        wizard.setActive_wizard("Y");
        wizardInfoRepository.save(wizard);
        assertNotNull(wizardInfoRepository.findById(2L).get());
    }

    @Test
    public void testGetWizard() {
        List<WizardInfo> list = wizardInfoRepository.findAll();
        assertThat(list).size().isGreaterThan(0);
    }


    @Test
    public void testUpdateWizard() {
        WizardInfo wizard = wizardInfoRepository.findById(2L).get();
        wizard.setWizard_name("Jay Lee");
        wizardInfoRepository.save(wizard);
        assertEquals("Jay Lee", wizardInfoRepository.findById(2L).get().getWizard_name());
    }

    @Test
    public void testDeleteWizard() {
        wizardInfoRepository.deleteById(16L);
        assertThat(wizardInfoRepository.existsById(3L)).isFalse();
    }
}
