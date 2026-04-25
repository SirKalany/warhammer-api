package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.Building;
import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.variant.BuildingVariant;
import com.whencyclopedia.dto.variant.building.BuildingVariantDTO;
import com.whencyclopedia.dto.variant.building.BuildingVariantSummaryDTO;
import com.whencyclopedia.repository.identity.BuildingRepository;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.variant.BuildingVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuildingVariantService {

    private final BuildingVariantRepository buildingVariantRepository;
    private final BuildingRepository buildingRepository;
    private final GameVersionRepository gameVersionRepository;

    public List<BuildingVariantSummaryDTO> findByVersion(Long versionId) {
        return buildingVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(bv -> toSummaryDTO(bv))
                .toList();
    }

    public List<BuildingVariantSummaryDTO> findByRaceAndVersion(Long raceId, Long versionId) {
        return buildingVariantRepository.findByBuilding_RaceIdAndGameVersionId(raceId, versionId)
                .stream()
                .map(bv -> toSummaryDTO(bv))
                .toList();
    }

    public List<BuildingVariantSummaryDTO> findByChainAndVersion(Long chainId, Long versionId) {
        return buildingVariantRepository.findByBuilding_BuildingChainIdAndGameVersionId(chainId, versionId)
                .stream()
                .map(bv -> toSummaryDTO(bv))
                .toList();
    }

    public Optional<BuildingVariantDTO> findByBuildingAndVersion(Long buildingId, Long versionId) {
        return buildingVariantRepository.findByBuildingIdAndGameVersionId(buildingId, versionId)
                .map(bv -> toDTO(bv));
    }

    public BuildingVariantDTO create(BuildingVariantDTO dto) {
        Building building = buildingRepository.findById(dto.buildingId())
                .orElseThrow(() -> new EntityNotFoundException("Building not found: " + dto.buildingId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        BuildingVariant variant = BuildingVariant.builder()
                .building(building)
                .gameVersion(version)
                .picture(dto.picture())
                .tier(dto.tier())
                .effect(dto.effect())
                .cost(dto.cost())
                .requirements(dto.requirements())
                .build();
        return toDTO(buildingVariantRepository.save(variant));
    }

    public BuildingVariantDTO update(Long id, BuildingVariantDTO dto) {
        BuildingVariant variant = buildingVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building variant not found: " + id));
        variant.setPicture(dto.picture());
        variant.setTier(dto.tier());
        variant.setEffect(dto.effect());
        variant.setCost(dto.cost());
        variant.setRequirements(dto.requirements());
        return toDTO(buildingVariantRepository.save(variant));
    }

    public void delete(Long id) {
        buildingVariantRepository.deleteById(id);
    }

    private BuildingVariantDTO toDTO(@NonNull BuildingVariant bv) {
        return new BuildingVariantDTO(
                bv.getId(), bv.getBuilding().getId(), bv.getBuilding().getName(),
                bv.getBuilding().getSlug(), bv.getBuilding().getCategory(),
                bv.getBuilding().getRace().getId(),
                bv.getBuilding().getBuildingChain() != null ? bv.getBuilding().getBuildingChain().getId() : null,
                bv.getGameVersion().getId(), bv.getPicture(), bv.getTier(),
                bv.getEffect(), bv.getCost(), bv.getRequirements()
        );
    }

    private BuildingVariantSummaryDTO toSummaryDTO(@NonNull BuildingVariant bv) {
        return new BuildingVariantSummaryDTO(
                bv.getId(), bv.getBuilding().getId(), bv.getBuilding().getName(),
                bv.getBuilding().getSlug(), bv.getBuilding().getCategory(),
                bv.getBuilding().getRace().getId(),
                bv.getBuilding().getBuildingChain() != null ? bv.getBuilding().getBuildingChain().getId() : null,
                bv.getGameVersion().getId(), bv.getTier()
        );
    }
}