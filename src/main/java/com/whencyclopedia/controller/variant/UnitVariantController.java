package com.whencyclopedia.controller.variant;

import com.whencyclopedia.domain.enums.UnitCategoryType;
import com.whencyclopedia.domain.enums.UnitRole;
import com.whencyclopedia.dto.variant.unit.UnitVariantDTO;
import com.whencyclopedia.dto.variant.unit.UnitVariantSummaryDTO;
import com.whencyclopedia.service.variant.UnitVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/units")
@RequiredArgsConstructor
public class UnitVariantController {

    private final UnitVariantService unitVariantService;

    @GetMapping
    public ResponseEntity<List<UnitVariantSummaryDTO>> findAll(
            @RequestParam Long versionId,
            @RequestParam(required = false) Long raceId,
            @RequestParam(required = false) UnitRole role,
            @RequestParam(required = false) UnitCategoryType categoryType) {
        if (raceId != null && role != null) {
            return ResponseEntity.ok(unitVariantService.findByRaceRoleAndVersion(raceId, role, versionId));
        }
        if (raceId != null && categoryType != null) {
            return ResponseEntity.ok(unitVariantService.findByRaceCategoryTypeAndVersion(raceId, categoryType, versionId));
        }
        if (raceId != null) {
            return ResponseEntity.ok(unitVariantService.findByRaceAndVersion(raceId, versionId));
        }
        return ResponseEntity.ok(unitVariantService.findByVersion(versionId));
    }

    @GetMapping("/unit/{unitId}")
    public ResponseEntity<UnitVariantDTO> findByUnitAndVersion(
            @PathVariable Long unitId,
            @RequestParam Long versionId) {
        return unitVariantService.findByUnitAndVersion(unitId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/unlock-building/{buildingVariantId}")
    public ResponseEntity<List<UnitVariantSummaryDTO>> findByUnlockBuilding(
            @PathVariable Long buildingVariantId) {
        return ResponseEntity.ok(unitVariantService.findByUnlockBuilding(buildingVariantId));
    }

    @GetMapping("/allow-building/{buildingVariantId}")
    public ResponseEntity<List<UnitVariantSummaryDTO>> findByAllowBuilding(
            @PathVariable Long buildingVariantId) {
        return ResponseEntity.ok(unitVariantService.findByAllowBuilding(buildingVariantId));
    }

    @PostMapping
    public ResponseEntity<UnitVariantDTO> create(@RequestBody UnitVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitVariantDTO> update(@PathVariable Long id, @RequestBody UnitVariantDTO dto) {
        return ResponseEntity.ok(unitVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unitVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}