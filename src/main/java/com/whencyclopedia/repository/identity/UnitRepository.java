package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.domain.identity.Unit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findBySlug(String slug);
    List<Unit> findByRaceId(Long raceId);
    List<Unit> findByRaceIdAndRole(Long raceId, UnitRole role);
    List<Unit> findByRaceIdAndCategoryType(Long raceId, UnitCategoryType categoryType);
    List<Unit> findByUnlockBuildingId(Long buildingId);
    List<Unit> findByAllowBuildingId(Long buildingId);
}