package com.xeTraining.magicWandCatalogueService.repository;

import com.xeTraining.magicWandCatalogueService.entity.MagicWand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagicWandRepository extends JpaRepository<MagicWand, Long> {

}
