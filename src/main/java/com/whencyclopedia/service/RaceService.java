package com.whencyclopedia.service;

import com.whencyclopedia.domain.Race;
import com.whencyclopedia.dto.race.RaceDTO;
import com.whencyclopedia.dto.race.RaceSummaryDTO;
import com.whencyclopedia.repository.RaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RaceService {

    private final RaceRepository raceRepository;

    public List<RaceSummaryDTO> findAll() {
        return raceRepository.findAll()
                .stream()
                .map(race -> toSummaryDTO(race))
                .toList();
    }

    public RaceDTO findBySlug(String slug) {
        Race race = raceRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + slug));
        return toDTO(race);
    }

    public RaceDTO create(RaceDTO dto) {
        Race race = Race.builder()
                .name(dto.name())
                .slug(dto.slug())
                .build();
        return toDTO(raceRepository.save(race));
    }

    public RaceDTO update(Long id, RaceDTO dto) {
        Race race = raceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + id));
        race.setName(dto.name());
        race.setSlug(dto.slug());
        return toDTO(raceRepository.save(race));
    }

    public void delete(Long id) {
        raceRepository.deleteById(id);
    }

    private RaceDTO toDTO(@NonNull Race race) {
        return new RaceDTO(race.getId(), race.getName(), race.getSlug());
    }

    private RaceSummaryDTO toSummaryDTO(@NonNull Race race) {
        return new RaceSummaryDTO(race.getId(), race.getName(), race.getSlug());
    }
}