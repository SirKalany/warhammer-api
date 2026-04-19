package com.whencyclopedia.repository;

import com.whencyclopedia.domain.BuildingGarrison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BuildingGarrisonRepository extends JpaRepository<BuildingGarrison, Long> {
    List<BuildingGarrison> findByBuildingId(Long buildingId);
}