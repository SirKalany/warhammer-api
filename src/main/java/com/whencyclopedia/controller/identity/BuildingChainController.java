package com.whencyclopedia.controller.identity;

import com.whencyclopedia.domain.enums.BuildingCategory;
import com.whencyclopedia.dto.identity.building.BuildingChainDTO;
import com.whencyclopedia.dto.identity.building.BuildingChainSummaryDTO;
import com.whencyclopedia.service.identity.BuildingChainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/building-chains")
@RequiredArgsConstructor
public class BuildingChainController {

    private final BuildingChainService buildingChainService;

    @GetMapping
    public ResponseEntity<List<BuildingChainSummaryDTO>> findAll(
            @RequestParam(required = false) Long raceId,
            @RequestParam(required = false) BuildingCategory category) {
        if (raceId != null && category != null) {
            return ResponseEntity.ok(buildingChainService.findByRaceAndCategory(raceId, category));
        }
        if (raceId != null) {
            return ResponseEntity.ok(buildingChainService.findByRace(raceId));
        }
        return ResponseEntity.ok(buildingChainService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BuildingChainDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(buildingChainService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<BuildingChainDTO> create(@RequestBody BuildingChainDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingChainService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingChainDTO> update(@PathVariable Long id, @RequestBody BuildingChainDTO dto) {
        return ResponseEntity.ok(buildingChainService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        buildingChainService.delete(id);
        return ResponseEntity.noContent().build();
    }
}