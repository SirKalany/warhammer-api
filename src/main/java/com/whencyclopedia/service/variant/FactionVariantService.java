package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.Faction;
import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.variant.FactionVariant;
import com.whencyclopedia.dto.variant.faction.FactionVariantDTO;
import com.whencyclopedia.repository.identity.FactionRepository;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.variant.FactionVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FactionVariantService {

    private final FactionVariantRepository factionVariantRepository;
    private final FactionRepository factionRepository;
    private final GameVersionRepository gameVersionRepository;

    public List<FactionVariantDTO> findByVersion(Long versionId) {
        return factionVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(fv -> toDTO(fv))
                .toList();
    }

    public List<FactionVariantDTO> findByRaceAndVersion(Long raceId, Long versionId) {
        return factionVariantRepository.findByFaction_RaceIdAndGameVersionId(raceId, versionId)
                .stream()
                .map(fv -> toDTO(fv))
                .toList();
    }

    public Optional<FactionVariantDTO> findByFactionAndVersion(Long factionId, Long versionId) {
        return factionVariantRepository.findByFactionIdAndGameVersionId(factionId, versionId)
                .map(fv -> toDTO(fv));
    }

    public FactionVariantDTO create(FactionVariantDTO dto) {
        Faction faction = factionRepository.findById(dto.factionId())
                .orElseThrow(() -> new EntityNotFoundException("Faction not found: " + dto.factionId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        FactionVariant variant = buildVariant(new FactionVariant(), dto, faction, version);
        return toDTO(factionVariantRepository.save(variant));
    }

    public FactionVariantDTO update(Long id, FactionVariantDTO dto) {
        FactionVariant variant = factionVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faction variant not found: " + id));
        Faction faction = factionRepository.findById(dto.factionId())
                .orElseThrow(() -> new EntityNotFoundException("Faction not found: " + dto.factionId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        buildVariant(variant, dto, faction, version);
        return toDTO(factionVariantRepository.save(variant));
    }

    public void delete(Long id) {
        factionVariantRepository.deleteById(id);
    }

    private FactionVariant buildVariant(
            @NonNull FactionVariant variant,
            @NonNull FactionVariantDTO dto,
            @NonNull Faction faction,
            @NonNull GameVersion version) {
        variant.setFaction(faction);
        variant.setGameVersion(version);
        variant.setBanner(dto.banner());
        variant.setLeader(dto.leader());
        variant.setFactionEffect(dto.factionEffect());
        variant.setIsHorde(dto.isHorde());
        variant.setClimateChaoticWasteland(dto.climateChaoticWasteland());
        variant.setClimateFrozen(dto.climateFrozen());
        variant.setClimateMountain(dto.climateMountain());
        variant.setClimateTemperate(dto.climateTemperate());
        variant.setClimateTemperateIsland(dto.climateTemperateIsland());
        variant.setClimateMagicalForest(dto.climateMagicalForest());
        variant.setClimateJungle(dto.climateJungle());
        variant.setClimateSavannah(dto.climateSavannah());
        variant.setClimateDesert(dto.climateDesert());
        variant.setClimateWasteland(dto.climateWasteland());
        variant.setClimateOcean(dto.climateOcean());
        return variant;
    }

    private FactionVariantDTO toDTO(@NonNull FactionVariant fv) {
        return new FactionVariantDTO(
                fv.getId(), fv.getFaction().getId(), fv.getGameVersion().getId(),
                fv.getBanner(), fv.getLeader(), fv.getFactionEffect(), fv.getIsHorde(),
                fv.getClimateChaoticWasteland(), fv.getClimateFrozen(), fv.getClimateMountain(),
                fv.getClimateTemperate(), fv.getClimateTemperateIsland(), fv.getClimateMagicalForest(),
                fv.getClimateJungle(), fv.getClimateSavannah(), fv.getClimateDesert(),
                fv.getClimateWasteland(), fv.getClimateOcean()
        );
    }
}