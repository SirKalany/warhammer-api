package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.domain.variant.RaceVariant;
import com.whencyclopedia.dto.identity.race.RaceSummaryDTO;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.identity.RaceRepository;
import com.whencyclopedia.repository.variant.RaceVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceVariantService {

    private final RaceVariantRepository raceVariantRepository;
    private final RaceRepository raceRepository;
    private final GameVersionRepository gameVersionRepository;

    // Returns races available in a specific version
    public List<RaceSummaryDTO> findByVersion(Long versionId) {
        return raceVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(rv -> toSummaryDTO(rv.getRace()))
                .toList();
    }

    public boolean existsForVersion(Long raceId, Long versionId) {
        return raceVariantRepository.existsByRaceIdAndGameVersionId(raceId, versionId);
    }

    public void addToVersion(Long raceId, Long versionId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + raceId));
        GameVersion version = gameVersionRepository.findById(versionId)
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + versionId));
        RaceVariant variant = RaceVariant.builder()
                .race(race)
                .gameVersion(version)
                .build();
        raceVariantRepository.save(variant);
    }

    public void removeFromVersion(Long raceId, Long versionId) {
        raceVariantRepository.findByRaceIdAndGameVersionId(raceId, versionId)
                .ifPresent(raceVariantRepository::delete);
    }

    private RaceSummaryDTO toSummaryDTO(@NonNull Race r) {
        return new RaceSummaryDTO(r.getId(), r.getName(), r.getSlug());
    }
}