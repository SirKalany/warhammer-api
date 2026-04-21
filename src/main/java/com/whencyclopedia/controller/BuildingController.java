package com.whencyclopedia.controller;

import com.whencyclopedia.dto.building.BuildingDTO;
import com.whencyclopedia.dto.building.BuildingSummaryDTO;
import com.whencyclopedia.service.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
@RequiredArgsConstructor
public class BuildingController {

    private final BuildingService buildingService;

    @GetMapping
    public ResponseEntity<List<BuildingSummaryDTO>> findAll(
            @RequestParam(required = false) Long raceId,
            @RequestParam(required = false) Long chainId) {
        if (raceId != null) {
            return ResponseEntity.ok(buildingService.findByRace(raceId));
        }
        if (chainId != null) {
            return ResponseEntity.ok(buildingService.findByChain(chainId));
        }
        return ResponseEntity.ok(buildingService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<BuildingDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(buildingService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<BuildingDTO> create(@RequestBody BuildingDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> update(@PathVariable Long id, @RequestBody BuildingDTO dto) {
        return ResponseEntity.ok(buildingService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        buildingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}