package com.whencyclopedia.service;

import com.whencyclopedia.domain.*;
import com.whencyclopedia.domain.identity.Building;
import com.whencyclopedia.domain.identity.BuildingChain;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.dto.building.BuildingDTO;
import com.whencyclopedia.dto.building.BuildingSummaryDTO;
import com.whencyclopedia.repository.*;
import com.whencyclopedia.repository.identity.BuildingChainRepository;
import com.whencyclopedia.repository.identity.BuildingRepository;
import com.whencyclopedia.repository.identity.RaceRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<BuildingSummaryDTO> findByRace(Long raceId) {
        return buildingRepository.findByRaceId(raceId)
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<BuildingSummaryDTO> findByChain(Long chainId) {
        return buildingRepository.findByBuildingChainId(chainId)
                .stream()
                .map(this::toSummaryDTO)
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
                .race(race)
                .buildingChain(chain)
                .name(dto.name())
                .slug(dto.slug())
                .picture(dto.picture())
                .tier(dto.tier())
                .category(dto.category())
                .effect(dto.effect())
                .cost(dto.cost())
                .requirements(dto.requirements())
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
        building.setRace(race);
        building.setBuildingChain(chain);
        building.setName(dto.name());
        building.setSlug(dto.slug());
        building.setPicture(dto.picture());
        building.setTier(dto.tier());
        building.setCategory(dto.category());
        building.setEffect(dto.effect());
        building.setCost(dto.cost());
        building.setRequirements(dto.requirements());
        return toDTO(buildingRepository.save(building));
    }

    public void delete(Long id) {
        buildingRepository.deleteById(id);
    }

    private BuildingDTO toDTO(Building b) {
        return new BuildingDTO(
                b.getId(), b.getRace().getId(),
                b.getBuildingChain() != null ? b.getBuildingChain().getId() : null,
                b.getName(), b.getSlug(), b.getPicture(), b.getTier(),
                b.getCategory(), b.getEffect(), b.getCost(), b.getRequirements()
        );
    }

    private BuildingSummaryDTO toSummaryDTO(Building b) {
        return new BuildingSummaryDTO(
                b.getId(), b.getRace().getId(),
                b.getBuildingChain() != null ? b.getBuildingChain().getId() : null,
                b.getName(), b.getSlug(), b.getTier(), b.getCategory()
        );
    }
}