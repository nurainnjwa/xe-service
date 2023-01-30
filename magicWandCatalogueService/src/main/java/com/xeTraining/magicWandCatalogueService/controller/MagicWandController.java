package com.xeTraining.magicWandCatalogueService.controller;

import com.xeTraining.magicWandCatalogueService.entity.MagicWand;
import com.xeTraining.magicWandCatalogueService.service.MagicWandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demo")
public class MagicWandController {

    @Autowired
    MagicWandService service;

    private MagicWand magicWand = new MagicWand();

    @GetMapping("/magicWandList")
    public List<MagicWand> getList(){
        return service.getMagicWandCatalogue();
    }

    @PostMapping("/addMagicWand")
    public String createMagicWand(@RequestBody MagicWand magicWand){
        return service.addMagicWandCatalogue(magicWand);
    }

    @DeleteMapping("/deleteMagicWand/{id}")
    public String deleteMagicWand(@PathVariable(value = "id")long id){
        return service.deleteMagicWand(id);
        //wizardInfoRepository.deleteById(id);
    }

    @PutMapping("/updateMagicWand/{wand_id}")
    public String updateWizardInfo(@PathVariable Long wand_id, @RequestBody MagicWand magicWand){
        return service.updateMagicWandCatalogue(wand_id,magicWand);
    }
}
