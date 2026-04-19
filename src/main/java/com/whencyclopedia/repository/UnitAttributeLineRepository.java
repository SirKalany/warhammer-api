package com.whencyclopedia.repository;

import com.whencyclopedia.domain.UnitAttributeLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UnitAttributeLineRepository extends JpaRepository<UnitAttributeLine, Long> {
    List<UnitAttributeLine> findByUnitIdOrderByPositionAsc(Long unitId);
}