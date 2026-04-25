package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.Building;
import com.whencyclopedia.domain.identity.BuildingChain;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.dto.identity.building.BuildingDTO;
import com.whencyclopedia.dto.identity.building.BuildingSummaryDTO;
import com.whencyclopedia.repository.identity.BuildingChainRepository;
import com.whencyclopedia.repository.identity.BuildingRepository;
import com.whencyclopedia.repository.identity.RaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingChainRepository buildingChainRepository;
    private final RaceRepository raceRepository;

    public List<BuildingSummaryDTO> findAll() {
        return buildingRepository.findAll()
                .stream()
                .map(b -> toSummaryDTO(b))
                .toList();
    }

    public List<BuildingSummaryDTO> findByRace(Long raceId) {
        return buildingRepository.findByRaceId(raceId)
                .stream()
                .map(b -> toSummaryDTO(b))
                .toList();
    }

    public List<BuildingSummaryDTO> findByChain(Long chainId) {
        return buildingRepository.findByBuildingChainId(chainId)
                .stream()
                .map(b -> toSummaryDTO(b))
                .toList();
    }

    public BuildingDTO findBySlug(String slug) {
        Building building = buildingRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Building not found: " + slug));
        return toDTO(building);
    }

    public BuildingDTO create(BuildingDTO dto) {
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        BuildingChain chain = dto.buildingChainId() != null
                ? buildingChainRepository.findById(dto.buildingChainId())
                        .orElseThrow(() -> new EntityNotFoundException("Chain not found: " + dto.buildingChainId()))
                : null;
        Building building = Building.builder()
                .name(dto.name())
                .slug(dto.slug())
                .race(race)
                .buildingChain(chain)
                .category(dto.category())
                .build();
        return toDTO(buildingRepository.save(building));
    }

    public BuildingDTO update(Long id, BuildingDTO dto) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building not found: " + id));
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        BuildingChain chain = dto.buildingChainId() != null
                ? buildingChainRepository.findById(dto.buildingChainId())
                        .orElseThrow(() -> new EntityNotFoundException("Chain not found: " + dto.buildingChainId()))
                : null;
        building.setName(dto.name());
        building.setSlug(dto.slug());
        building.setRace(race);
        building.setBuildingChain(chain);
        building.setCategory(dto.category());
        return toDTO(buildingRepository.save(building));
    }

    public void delete(Long id) {
        buildingRepository.deleteById(id);
    }

    private BuildingDTO toDTO(@NonNull Building b) {
        return new BuildingDTO(
                b.getId(), b.getName(), b.getSlug(), b.getRace().getId(),
                b.getBuildingChain() != null ? b.getBuildingChain().getId() : null,
                b.getCategory()
        );
    }

    private BuildingSummaryDTO toSummaryDTO(@NonNull Building b) {
        return new BuildingSummaryDTO(
                b.getId(), b.getName(), b.getSlug(), b.getRace().getId(),
                b.getBuildingChain() != null ? b.getBuildingChain().getId() : null,
                b.getCategory()
        );
    }
}