package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.FactionVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FactionVariantRepository extends JpaRepository<FactionVariant, Long> {
    List<FactionVariant> findByGameVersionId(Long gameVersionId);
    List<FactionVariant> findByFaction_RaceIdAndGameVersionId(Long raceId, Long gameVersionId);
    Optional<FactionVariant> findByFactionIdAndGameVersionId(Long factionId, Long gameVersionId);
    List<FactionVariant> findByIsHordeAndGameVersionId(Boolean isHorde, Long gameVersionId);
}