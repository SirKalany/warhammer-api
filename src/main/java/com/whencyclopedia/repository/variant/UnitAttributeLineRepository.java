package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.UnitVariantAttributeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UnitAttributeLineRepository extends JpaRepository<UnitVariantAttributeLine, Long> {
    List<UnitVariantAttributeLine> findByUnitIdOrderByPositionAsc(Long unitId);
}