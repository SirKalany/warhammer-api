package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.BuildingVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingVariantRepository extends JpaRepository<BuildingVariant, Long> {
    List<BuildingVariant> findByGameVersionId(Long gameVersionId);
    List<BuildingVariant> findByBuilding_RaceIdAndGameVersionId(Long raceId, Long gameVersionId);
    List<BuildingVariant> findByBuilding_BuildingChainIdAndGameVersionId(Long chainId, Long gameVersionId);
    Optional<BuildingVariant> findByBuildingIdAndGameVersionId(Long buildingId, Long gameVersionId);
}