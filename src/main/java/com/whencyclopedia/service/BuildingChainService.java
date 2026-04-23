package com.whencyclopedia.service;

import com.whencyclopedia.domain.enums.BuildingCategory;
import com.whencyclopedia.domain.identity.BuildingChain;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.dto.building.BuildingChainDTO;
import com.whencyclopedia.dto.building.BuildingChainSummaryDTO;
import com.whencyclopedia.repository.identity.BuildingChainRepository;
import com.whencyclopedia.repository.identity.RaceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingChainService {

    private final BuildingChainRepository buildingChainRepository;
    private final RaceRepository raceRepository;

    public List<BuildingChainSummaryDTO> findAll() {
        return buildingChainRepository.findAll()
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<BuildingChainSummaryDTO> findByRace(Long raceId) {
        return buildingChainRepository.findByRaceId(raceId)
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<BuildingChainSummaryDTO> findByRaceAndCategory(Long raceId, BuildingCategory category) {
        return buildingChainRepository.findByRaceIdAndCategory(raceId, category)
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public BuildingChainDTO findBySlug(String slug) {
        BuildingChain chain = buildingChainRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Building chain not found: " + slug));
        return toDTO(chain);
    }

    public BuildingChainDTO create(BuildingChainDTO dto) {
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        BuildingChain chain = BuildingChain.builder()
                .race(race)
                .name(dto.name())
                .slug(dto.slug())
                .category(dto.category())
                .description(dto.description())
                .build();
        return toDTO(buildingChainRepository.save(chain));
    }

    public BuildingChainDTO update(Long id, BuildingChainDTO dto) {
        BuildingChain chain = buildingChainRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building chain not found: " + id));
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        chain.setRace(race);
        chain.setName(dto.name());
        chain.setSlug(dto.slug());
        chain.setCategory(dto.category());
        chain.setDescription(dto.description());
        return toDTO(buildingChainRepository.save(chain));
    }

    public void delete(Long id) {
        buildingChainRepository.deleteById(id);
    }

    private BuildingChainDTO toDTO(BuildingChain c) {
        return new BuildingChainDTO(
                c.getId(), c.getRace().getId(), c.getName(),
                c.getSlug(), c.getCategory(), c.getDescription()
        );
    }

    private BuildingChainSummaryDTO toSummaryDTO(BuildingChain c) {
        return new BuildingChainSummaryDTO(
                c.getId(), c.getRace().getId(), c.getName(), c.getSlug(), c.getCategory()
        );
    }
}