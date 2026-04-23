package com.whencyclopedia.repository.variant;

import com.whencyclopedia.domain.variant.RaceVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RaceVariantRepository extends JpaRepository<RaceVariant, Long> {
    List<RaceVariant> findByGameVersionId(Long gameVersionId);
    Optional<RaceVariant> findByRaceIdAndGameVersionId(Long raceId, Long gameVersionId);
    boolean existsByRaceIdAndGameVersionId(Long raceId, Long gameVersionId);
}