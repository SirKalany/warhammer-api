package com.whencyclopedia.controller.variant;

import com.whencyclopedia.dto.variant.building.BuildingVariantDTO;
import com.whencyclopedia.dto.variant.building.BuildingVariantSummaryDTO;
import com.whencyclopedia.service.variant.BuildingVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/buildings")
@RequiredArgsConstructor
public class BuildingVariantController {

    private final BuildingVariantService buildingVariantService;

    @GetMapping
    public ResponseEntity<List<BuildingVariantSummaryDTO>> findAll(
            @RequestParam Long versionId,
            @RequestParam(required = false) Long raceId,
            @RequestParam(required = false) Long chainId) {
        if (raceId != null) {
            return ResponseEntity.ok(buildingVariantService.findByRaceAndVersion(raceId, versionId));
        }
        if (chainId != null) {
            return ResponseEntity.ok(buildingVariantService.findByChainAndVersion(chainId, versionId));
        }
        return ResponseEntity.ok(buildingVariantService.findByVersion(versionId));
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<BuildingVariantDTO> findByBuildingAndVersion(
            @PathVariable Long buildingId,
            @RequestParam Long versionId) {
        return buildingVariantService.findByBuildingAndVersion(buildingId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BuildingVariantDTO> create(@RequestBody BuildingVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingVariantDTO> update(@PathVariable Long id, @RequestBody BuildingVariantDTO dto) {
        return ResponseEntity.ok(buildingVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        buildingVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}