package com.whencyclopedia.repository;

import com.whencyclopedia.domain.BuildingChain;
import com.whencyclopedia.domain.BuildingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingChainRepository extends JpaRepository<BuildingChain, Long> {
    Optional<BuildingChain> findBySlug(String slug);
    List<BuildingChain> findByRaceId(Long raceId);
    List<BuildingChain> findByRaceIdAndCategory(Long raceId, BuildingCategory category);
}