package com.whencyclopedia.repository.identity;

import com.whencyclopedia.domain.identity.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    Optional<Building> findBySlug(String slug);

    List<Building> findByRaceId(Long raceId);

    List<Building> findByBuildingChainId(Long chainId);
}