package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.UnitVariant;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.domain.enums.UnitCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitVariantRepository extends JpaRepository<UnitVariant, Long> {

    List<UnitVariant> findByGameVersionId(Long gameVersionId);

    @Query("SELECT uv FROM UnitVariant uv JOIN uv.unit.races r WHERE r.id = :raceId AND uv.gameVersion.id = :gameVersionId")
    List<UnitVariant> findByUnit_RaceIdAndGameVersionId(@Param("raceId") Long raceId, @Param("gameVersionId") Long gameVersionId);

    @Query("SELECT uv FROM UnitVariant uv JOIN uv.unit.races r WHERE r.id = :raceId AND uv.role = :role AND uv.gameVersion.id = :gameVersionId")
    List<UnitVariant> findByUnit_RaceIdAndRoleAndGameVersionId(@Param("raceId") Long raceId, @Param("role") UnitRole role, @Param("gameVersionId") Long gameVersionId);

    @Query("SELECT uv FROM UnitVariant uv JOIN uv.unit.races r WHERE r.id = :raceId AND uv.categoryType = :categoryType AND uv.gameVersion.id = :gameVersionId")
    List<UnitVariant> findByUnit_RaceIdAndCategoryTypeAndGameVersionId(@Param("raceId") Long raceId, @Param("categoryType") UnitCategoryType categoryType, @Param("gameVersionId") Long gameVersionId);

    Optional<UnitVariant> findByUnitIdAndGameVersionId(Long unitId, Long gameVersionId);

    List<UnitVariant> findByUnlockBuildingId(Long buildingVariantId);

    List<UnitVariant> findByAllowBuildingId(Long buildingVariantId);
}