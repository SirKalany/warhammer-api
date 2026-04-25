package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.BuildingChain;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.domain.enums.BuildingCategory;
import com.whencyclopedia.dto.identity.building.BuildingChainDTO;
import com.whencyclopedia.dto.identity.building.BuildingChainSummaryDTO;
import com.whencyclopedia.repository.identity.BuildingChainRepository;
import com.whencyclopedia.repository.identity.RaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
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
                .map(c -> toSummaryDTO(c))
                .toList();
    }

    public List<BuildingChainSummaryDTO> findByRace(Long raceId) {
        return buildingChainRepository.findByRaceId(raceId)
                .stream()
                .map(c -> toSummaryDTO(c))
                .toList();
    }

    public List<BuildingChainSummaryDTO> findByRaceAndCategory(Long raceId, BuildingCategory category) {
        return buildingChainRepository.findByRaceIdAndCategory(raceId, category)
                .stream()
                .map(c -> toSummaryDTO(c))
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
                .name(dto.name())
                .slug(dto.slug())
                .race(race)
                .category(dto.category())
                .build();
        return toDTO(buildingChainRepository.save(chain));
    }

    public BuildingChainDTO update(Long id, BuildingChainDTO dto) {
        BuildingChain chain = buildingChainRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Building chain not found: " + id));
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        chain.setName(dto.name());
        chain.setSlug(dto.slug());
        chain.setRace(race);
        chain.setCategory(dto.category());
        return toDTO(buildingChainRepository.save(chain));
    }

    public void delete(Long id) {
        buildingChainRepository.deleteById(id);
    }

    private BuildingChainDTO toDTO(@NonNull BuildingChain c) {
        return new BuildingChainDTO(
                c.getId(), c.getName(), c.getSlug(),
                c.getRace().getId(), c.getCategory()
        );
    }

    private BuildingChainSummaryDTO toSummaryDTO(@NonNull BuildingChain c) {
        return new BuildingChainSummaryDTO(
                c.getId(), c.getName(), c.getSlug(),
                c.getRace().getId(), c.getCategory()
        );
    }
}