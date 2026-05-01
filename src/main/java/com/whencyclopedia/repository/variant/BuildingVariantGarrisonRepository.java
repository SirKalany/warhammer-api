package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.BuildingVariantGarrison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuildingVariantGarrisonRepository extends JpaRepository<BuildingVariantGarrison, Long> {
    List<BuildingVariantGarrison> findByBuildingVariantId(Long buildingVariantId);
}