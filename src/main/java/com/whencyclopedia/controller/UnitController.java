package com.whencyclopedia.controller;

import com.whencyclopedia.domain.UnitCategoryType;
import com.whencyclopedia.domain.UnitRole;
import com.whencyclopedia.dto.unit.UnitDTO;
import com.whencyclopedia.dto.unit.UnitSummaryDTO;
import com.whencyclopedia.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<List<UnitSummaryDTO>> findAll(
            @RequestParam(required = false) Long raceId,
            @RequestParam(required = false) UnitRole role,
            @RequestParam(required = false) UnitCategoryType categoryType) {
        if (raceId != null && role != null) {
            return ResponseEntity.ok(unitService.findByRaceAndRole(raceId, role));
        }
        if (raceId != null && categoryType != null) {
            return ResponseEntity.ok(unitService.findByRaceAndCategoryType(raceId, categoryType));
        }
        if (raceId != null) {
            return ResponseEntity.ok(unitService.findByRace(raceId));
        }
        return ResponseEntity.ok(unitService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<UnitDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(unitService.findBySlug(slug));
    }

    @GetMapping("/unlock-building/{buildingId}")
    public ResponseEntity<List<UnitSummaryDTO>> findByUnlockBuilding(@PathVariable Long buildingId) {
        return ResponseEntity.ok(unitService.findByUnlockBuilding(buildingId));
    }

    @GetMapping("/allow-building/{buildingId}")
    public ResponseEntity<List<UnitSummaryDTO>> findByAllowBuilding(@PathVariable Long buildingId) {
        return ResponseEntity.ok(unitService.findByAllowBuilding(buildingId));
    }

    @PostMapping
    public ResponseEntity<UnitDTO> create(@RequestBody UnitDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitDTO> update(@PathVariable Long id, @RequestBody UnitDTO dto) {
        return ResponseEntity.ok(unitService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}