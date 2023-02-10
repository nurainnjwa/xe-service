package com.xeTraining.wizardInfoservice;

import com.xeTraining.wizardInfoservice.entity.WizardInfo;
import com.xeTraining.wizardInfoservice.repository.WizardInfoRepository;
import com.xeTraining.wizardInfoservice.service.WizardInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WizardInfoTest {

    @Mock
    private WizardInfoRepository wizardInfoRepository;

    @InjectMocks
    private WizardInfoService wizardInfoService;

    @Test
    public void testGetWizardInfo() {
        List<WizardInfo> wizards = new ArrayList<WizardInfo>();
        wizards.add(new WizardInfo(1L, "Harry Potter",
                17, LocalDate.of(2020,07,07),
                "Y"));
        wizards.add(new WizardInfo(2L, "Ron Weasley", 17,
                LocalDate.of(2020,07,07), "Y"));
        wizards.add(new WizardInfo(3L, "Hermione Granger", 17,
                LocalDate.of(2020,07,07), "Y"));

        when(wizardInfoRepository.findAll()).thenReturn(wizards);

        List<WizardInfo> result = wizardInfoService.getWizardInfo();
        assertEquals(3, result.size());
    }

    @Test
    public void testAddWizardInfo(){
        WizardInfo wizardInfo = new WizardInfo (4L, "Harry Potter",
                17, LocalDate.of(2022,07,07),
                "Y");
        when(wizardInfoRepository.save(wizardInfo)).thenReturn(wizardInfo);
        String result = wizardInfoService.addWizardInfo(wizardInfo);
        assertEquals("Wizard added successfully", result);
    }

    @Test
    public void testDeleteWizardInfo(){
        WizardInfo wizardInfo = new WizardInfo (4L, "Harry Potter",
                17, LocalDate.of(2022,07,07),
                "Y");
        when(wizardInfoRepository.findById(4L)).thenReturn(Optional.of(wizardInfo));
        String result = wizardInfoService.deleteWizardInfo(4L);
        assertEquals("Wizard ID: 4 successfully deleted", result);
    }

    @Test
    public void testDeleteWizardInfo_WizardIDNotFound(){
        WizardInfoTest wizardInfoTest = new WizardInfoTest();

        try{
            WizardInfo wizardInfo = new WizardInfo (4L, "Harry Potter",
                    17, LocalDate.of(2022,07,07),
                    "Y");
            when(wizardInfoRepository.findById(1L)).thenReturn(Optional.of(wizardInfo));
            String result = wizardInfoService.deleteWizardInfo(4L);
        } catch (Exception e){
            assertEquals(e.getMessage(), "ID Not found. Please enter valid ID");
        }
    }

    @Test
    public void testUpdateWizardInfo() {
        WizardInfo wizardInfo = new WizardInfo (4L, "Harry Potter",
                17, LocalDate.of(2022,07,07),
                "Y");
        when(wizardInfoRepository.findById(1L)).thenReturn(Optional.of(wizardInfo));
        when(wizardInfoRepository.save(wizardInfo)).thenReturn(wizardInfo);
        String result = wizardInfoService.updateWizardInfo(1L, wizardInfo);
        assertEquals("Wizard updated successfully", result);
    }

    @Test
    public void testUpdateWizardInfo_WizardIDNotFound(){

        try{
            WizardInfo wizardInfo = new WizardInfo (10L, "Harry Potter",
                    17, LocalDate.of(2022,07,07),
                    "Y");

            when(wizardInfoRepository.findById(1L)).thenReturn(Optional.of(wizardInfo));
            when(wizardInfoRepository.save(wizardInfo)).thenReturn(wizardInfo);
            String result = wizardInfoService.updateWizardInfo(1L, wizardInfo);

        } catch (Exception e){
            assertEquals(e.getMessage(), "Wizard ID: 1 Not found. Please enter valid ID");
        }
    }
}
