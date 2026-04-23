package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.UnitVariant;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.domain.enums.UnitCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnitVariantRepository extends JpaRepository<UnitVariant, Long> {
    List<UnitVariant> findByGameVersionId(Long gameVersionId);
    List<UnitVariant> findByUnit_RaceIdAndGameVersionId(Long raceId, Long gameVersionId);
    List<UnitVariant> findByUnit_RaceIdAndRoleAndGameVersionId(Long raceId, UnitRole role, Long gameVersionId);
    List<UnitVariant> findByUnit_RaceIdAndCategoryTypeAndGameVersionId(Long raceId, UnitCategoryType categoryType, Long gameVersionId);
    Optional<UnitVariant> findByUnitIdAndGameVersionId(Long unitId, Long gameVersionId);
    List<UnitVariant> findByUnlockBuildingId(Long buildingVariantId);
    List<UnitVariant> findByAllowBuildingId(Long buildingVariantId);
}