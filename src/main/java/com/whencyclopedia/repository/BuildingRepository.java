package com.whencyclopedia.repository;

import com.whencyclopedia.domain.Building;
import com.whencyclopedia.domain.BuildingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findBySlug(String slug);
    List<Building> findByRaceId(Long raceId);
    List<Building> findByBuildingChainId(Long chainId);
    List<Building> findByRaceIdAndCategory(Long raceId, BuildingCategory category);
    // For building detail page — units this building unlocks or allows
    List<Building> findByUnlockBuildingIdOrAllowBuildingId(Long unlockId, Long allowId);
}