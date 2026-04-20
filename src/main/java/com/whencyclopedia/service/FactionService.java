package com.whencyclopedia.service;

import com.whencyclopedia.domain.Faction;
import com.whencyclopedia.domain.Race;
import com.whencyclopedia.dto.faction.FactionDTO;
import com.whencyclopedia.dto.faction.FactionSummaryDTO;
import com.whencyclopedia.repository.FactionRepository;
import com.whencyclopedia.repository.RaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FactionService {

    private final FactionRepository factionRepository;
    private final RaceRepository raceRepository;

    public List<FactionSummaryDTO> findAll() {
        return factionRepository.findAll()
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<FactionSummaryDTO> findByRace(Long raceId) {
        return factionRepository.findByRaceId(raceId)
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public FactionDTO findBySlug(String slug) {
        Faction faction = factionRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Faction not found: " + slug));
        return toDTO(faction);
    }

    public FactionDTO create(FactionDTO dto) {
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        Faction faction = Faction.builder()
                .race(race)
                .name(dto.name())
                .slug(dto.slug())
                .banner(dto.banner())
                .leader(dto.leader())
                .factionEffect(dto.factionEffect())
                .isHorde(dto.isHorde())
                .climateChaoticWasteland(dto.climateChaoticWasteland())
                .climateFrozen(dto.climateFrozen())
                .climateMountain(dto.climateMountain())
                .climateTemperate(dto.climateTemperate())
                .climateTemperateIsland(dto.climateTemperateIsland())
                .climateMagicalForest(dto.climateMagicalForest())
                .climateJungle(dto.climateJungle())
                .climateSavannah(dto.climateSavannah())
                .climateDesert(dto.climateDesert())
                .climateWasteland(dto.climateWasteland())
                .climateOcean(dto.climateOcean())
                .build();
        return toDTO(factionRepository.save(faction));
    }

    public FactionDTO update(Long id, FactionDTO dto) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faction not found: " + id));
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        faction.setRace(race);
        faction.setName(dto.name());
        faction.setSlug(dto.slug());
        faction.setBanner(dto.banner());
        faction.setLeader(dto.leader());
        faction.setFactionEffect(dto.factionEffect());
        faction.setIsHorde(dto.isHorde());
        faction.setClimateChaoticWasteland(dto.climateChaoticWasteland());
        faction.setClimateFrozen(dto.climateFrozen());
        faction.setClimateMountain(dto.climateMountain());
        faction.setClimateTemperate(dto.climateTemperate());
        faction.setClimateTemperateIsland(dto.climateTemperateIsland());
        faction.setClimateMagicalForest(dto.climateMagicalForest());
        faction.setClimateJungle(dto.climateJungle());
        faction.setClimateSavannah(dto.climateSavannah());
        faction.setClimateDesert(dto.climateDesert());
        faction.setClimateWasteland(dto.climateWasteland());
        faction.setClimateOcean(dto.climateOcean());
        return toDTO(factionRepository.save(faction));
    }

    public void delete(Long id) {
        factionRepository.deleteById(id);
    }

    private FactionDTO toDTO(Faction f) {
        return new FactionDTO(
                f.getId(), f.getRace().getId(), f.getName(), f.getSlug(),
                f.getBanner(), f.getLeader(), f.getFactionEffect(), f.getIsHorde(),
                f.getClimateChaoticWasteland(), f.getClimateFrozen(), f.getClimateMountain(),
                f.getClimateTemperate(), f.getClimateTemperateIsland(), f.getClimateMagicalForest(),
                f.getClimateJungle(), f.getClimateSavannah(), f.getClimateDesert(),
                f.getClimateWasteland(), f.getClimateOcean()
        );
    }

    private FactionSummaryDTO toSummaryDTO(Faction f) {
        return new FactionSummaryDTO(f.getId(), f.getRace().getId(), f.getName(), f.getSlug(), f.getBanner());
    }
}