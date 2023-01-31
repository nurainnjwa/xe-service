//package com.xeTraining.wizardInfoservice;
//
//import com.xeTraining.wizardInfoservice.entity.WizardInfo;
//import com.xeTraining.wizardInfoservice.repository.WizardInfoRepository;
//import com.xeTraining.wizardInfoservice.service.WizardInfoService;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class WizardInfoServiceTest {
//
//    @Autowired
//    private WizardInfoService service;
//
//    @Autowired
//    private WizardInfoRepository repository;
//
//    private WizardInfo wizardInfo;
//
//    @Test
//    public void setup() {
////        wizardInfo = new WizardInfo(2, "Fatin", 40,
////                LocalDate.parse(2022-07-08), "Y");
//        wizardInfo = new WizardInfo();
//        wizardInfo.setWizard_id(2L);
//        wizardInfo.setWizard_name("Fatin");
//        wizardInfo.setAge(40);
//        wizardInfo.setJoined_date(LocalDate.parse("2022-07-08"));
//        wizardInfo.setActive_wizard("Y");
//        repository.save(wizardInfo);
//        assertNotNull(repository.findById(2L).get());
//    }
//
//    @Test
//    public void getWizardInfoTest() {
//        List<WizardInfo> wizardInfos = Arrays.asList(wizardInfo);
//        when(repository.findAll()).thenReturn(wizardInfos);
//        List<WizardInfo> result = service.getWizardInfo();
//        assertEquals(wizardInfos, result);
//    }
//
//    @Test
//    public void addWizardInfoTest() {
//        when(repository.save(wizardInfo)).thenReturn(wizardInfo);
//        String result = service.addWizardInfo(wizardInfo);
//        assertEquals("Wizard added successfully", result);
//    }
//
//    @Test
//    public void deleteWizardInfoTest() {
//        when(repository.findById(1L)).thenReturn(Optional.of(wizardInfo));
//        doNothing().when(repository).deleteById(1L);
//        String result = service.deleteWizardInfo(1L);
//        assertEquals("Wizard ID: 1 successfully deleted", result);
//    }
//
//    @Test
//    public void updateWizardInfoTest() {
//        when(repository.findById(1L)).thenReturn(Optional.of(wizardInfo));
//        when(repository.save(wizardInfo)).thenReturn(wizardInfo);
//        String result = service.updateWizardInfo(1L, wizardInfo);
//        assertEquals("Wizard updated successfully", result);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void deleteWizardInfoNotFoundTest() {
//        when(repository.findById(1L)).thenReturn(Optional.empty());
//        service.deleteWizardInfo(1L);
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void updateWizardInfoNotFoundTest() {
//        when(repository.findById(1L)).thenReturn(Optional.empty());
//        service.updateWizardInfo(1L, wizardInfo);
//    }
//}
//
