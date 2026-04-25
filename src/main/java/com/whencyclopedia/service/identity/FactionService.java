package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.Faction;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.dto.identity.faction.FactionDTO;
import com.whencyclopedia.dto.identity.faction.FactionSummaryDTO;
import com.whencyclopedia.repository.identity.FactionRepository;
import com.whencyclopedia.repository.identity.RaceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
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
                .map(f -> toSummaryDTO(f))
                .toList();
    }

    public List<FactionSummaryDTO> findByRace(Long raceId) {
        return factionRepository.findByRaceId(raceId)
                .stream()
                .map(f -> toSummaryDTO(f))
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
                .name(dto.name())
                .slug(dto.slug())
                .race(race)
                .build();
        return toDTO(factionRepository.save(faction));
    }

    public FactionDTO update(Long id, FactionDTO dto) {
        Faction faction = factionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Faction not found: " + id));
        Race race = raceRepository.findById(dto.raceId())
                .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()));
        faction.setName(dto.name());
        faction.setSlug(dto.slug());
        faction.setRace(race);
        return toDTO(factionRepository.save(faction));
    }

    public void delete(Long id) {
        factionRepository.deleteById(id);
    }

    private FactionDTO toDTO(@NonNull Faction f) {
        return new FactionDTO(f.getId(), f.getName(), f.getSlug(), f.getRace().getId());
    }

    private FactionSummaryDTO toSummaryDTO(@NonNull Faction f) {
        return new FactionSummaryDTO(f.getId(), f.getName(), f.getSlug(), f.getRace().getId());
    }
}