package com.xeTraining.magicWandCatalogueService;

import com.xeTraining.magicWandCatalogueService.entity.MagicWand;
import com.xeTraining.magicWandCatalogueService.repository.MagicWandRepository;
import com.xeTraining.magicWandCatalogueService.service.MagicWandService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MagicWandTest {

    @Mock
    private MagicWandRepository magicWandRepository;

    @InjectMocks
    private MagicWandService magicWandService;

    @Test
    public void testGetMagicWand() {
        List<MagicWand> magicWands = new ArrayList<MagicWand>();
        magicWands.add(new MagicWand(1L, "Cute Magic Wand",
                "A cute wand with cartoon theme", 25, 50));
        magicWands.add(new MagicWand(2L, "Harry Potter Wand",
                "A harry potter's theme wand", 45, 100));

        when(magicWandRepository.findAll()).thenReturn(magicWands);

        List<MagicWand> result = magicWandService.getMagicWandCatalogue();
        assertEquals(2, result.size());
    }

    @Test
    public void testAddMagicWand(){
        MagicWand magicWand = (new MagicWand(1L, "Cute Magic Wand",
                "A cute wand with cartoon theme", 25, 50));
        when(magicWandRepository.save(magicWand)).thenReturn(magicWand);
        String result = magicWandService.addMagicWandCatalogue(magicWand);
        assertEquals("Magic Wand added successfully", result);
    }

    @Test
    public void testDeleteWizardInfo(){
        MagicWand magicWand = (new MagicWand(1L, "Cute Magic Wand",
                "A cute wand with cartoon theme", 25, 50));
        when(magicWandRepository.findById(1L)).thenReturn(Optional.of(magicWand));
        String result = magicWandService.deleteMagicWand(1L);
        assertEquals("Magic Wand ID: 1 successfully deleted", result);
    }

    @Test
    public void testDeleteWizardInfo_WizardIDNotFound(){
        try{
            MagicWand magicWand = (new MagicWand(1L, "Cute Magic Wand",
                    "A cute wand with cartoon theme", 25, 50));
            when(magicWandRepository.findById(100L)).thenReturn(Optional.of(magicWand));
            String result = magicWandService.deleteMagicWand(100L);
        } catch (Exception e){
            assertEquals(e.getMessage(), "Wand ID: 100 not found");
        }
    }

    @Test
    public void testUpdateWizardInfo() {
        MagicWand magicWand = (new MagicWand(1L, "Cute Magic Wand",
                "A cute wand with cartoon theme", 25, 50));
        when(magicWandRepository.findById(1L)).thenReturn(Optional.of(magicWand));
        when(magicWandRepository.save(magicWand)).thenReturn(magicWand);
        String result = magicWandService.updateMagicWandCatalogue(1L, magicWand);
        assertEquals("Magic Wand updated successfully", result);
    }

    @Test
    public void testUpdateWizardInfo_WizardIDNotFound(){
        try{
            MagicWand magicWand = (new MagicWand(1L, "Cute Magic Wand",
                    "A cute wand with cartoon theme", 25, 50));
            when(magicWandRepository.findById(100L)).thenReturn(Optional.of(magicWand));
            when(magicWandRepository.save(magicWand)).thenReturn(magicWand);
            String result = magicWandService.updateMagicWandCatalogue(100L,magicWand);
        } catch (Exception e){
            assertEquals(e.getMessage(), "Wand ID: 100 not found");
        }
    }

}
