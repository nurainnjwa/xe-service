package com.xeTraining.magicWandCatalogueService.service;

import com.xeTraining.magicWandCatalogueService.entity.MagicWand;
import com.xeTraining.magicWandCatalogueService.repository.MagicWandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagicWandService {

    @Autowired
    MagicWandRepository repository;

    public List<MagicWand> getMagicWandCatalogue(){
        return repository.findAll();
    }

    public String addMagicWandCatalogue(MagicWand magicWand){
        repository.save(magicWand);
        return "Magic Wand added successfully";
    }

    public String deleteMagicWand(Long id){
        repository.findById(id).orElseThrow(
                () -> new RuntimeException("ID Not found")
        );
        repository.deleteById(id);
        return "Magic Wand ID: "+ id + " successfully deleted";
    }

    public String updateMagicWandCatalogue(Long wand_id, MagicWand magicWand){
        MagicWand foundWand = repository.findById(wand_id)
                .orElseThrow(() -> new RuntimeException(wand_id+" not found")
        );
        foundWand.setMagic_wand_name(magicWand.getMagic_wand_name());
        foundWand.setMagic_wand_desc(magicWand.getMagic_wand_desc());
        foundWand.setAge_limit(magicWand.getAge_limit());
        foundWand.setMagic_wand_stock(magicWand.getMagic_wand_stock());
         repository.save(foundWand);
         return "Magic Wand updated successfully";
    }
}
