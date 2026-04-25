package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.domain.identity.Unit;
import com.whencyclopedia.dto.identity.unit.UnitDTO;
import com.whencyclopedia.dto.identity.unit.UnitSummaryDTO;
import com.whencyclopedia.repository.identity.RaceRepository;
import com.whencyclopedia.repository.identity.UnitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final RaceRepository raceRepository;

    public List<UnitSummaryDTO> findAll() {
        return unitRepository.findAll()
                .stream()
                .map(u -> toSummaryDTO(u))
                .toList();
    }

    public List<UnitSummaryDTO> findByRace(Long raceId) {
        return unitRepository.findByRaces_Id(raceId)
                .stream()
                .map(u -> toSummaryDTO(u))
                .toList();
    }

    public UnitDTO findBySlug(String slug) {
        Unit unit = unitRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + slug));
        return toDTO(unit);
    }

    public UnitDTO create(UnitDTO dto) {
        List<Race> races = raceRepository.findAllById(dto.raceIds());
        Unit unit = Unit.builder()
                .name(dto.name())
                .slug(dto.slug())
                .races(races)
                .build();
        return toDTO(unitRepository.save(unit));
    }

    public UnitDTO update(Long id, UnitDTO dto) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unit not found: " + id));
        List<Race> races = raceRepository.findAllById(dto.raceIds());
        unit.setName(dto.name());
        unit.setSlug(dto.slug());
        unit.setRaces(races);
        return toDTO(unitRepository.save(unit));
    }

    public void delete(Long id) {
        unitRepository.deleteById(id);
    }

    private UnitDTO toDTO(@NonNull Unit u) {
        return new UnitDTO(
                u.getId(), u.getName(), u.getSlug(),
                u.getRaces().stream().map(Race::getId).toList()
        );
    }

    private UnitSummaryDTO toSummaryDTO(@NonNull Unit u) {
        return new UnitSummaryDTO(
                u.getId(), u.getName(), u.getSlug(),
                u.getRaces().stream().map(Race::getId).toList()
        );
    }
}