package com.whencyclopedia.controller.variant;

import com.whencyclopedia.dto.variant.shared.UnitAttributeVariantDTO;
import com.whencyclopedia.service.variant.UnitAttributeVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/unit-attributes")
@RequiredArgsConstructor
public class UnitAttributeVariantController {

    private final UnitAttributeVariantService unitAttributeVariantService;

    @GetMapping
    public ResponseEntity<List<UnitAttributeVariantDTO>> findByVersion(@RequestParam Long versionId) {
        return ResponseEntity.ok(unitAttributeVariantService.findByVersion(versionId));
    }

    @GetMapping("/attribute/{attributeId}")
    public ResponseEntity<UnitAttributeVariantDTO> findByAttributeAndVersion(
            @PathVariable Long attributeId,
            @RequestParam Long versionId) {
        return unitAttributeVariantService.findByAttributeAndVersion(attributeId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UnitAttributeVariantDTO> create(@RequestBody UnitAttributeVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitAttributeVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitAttributeVariantDTO> update(@PathVariable Long id, @RequestBody UnitAttributeVariantDTO dto) {
        return ResponseEntity.ok(unitAttributeVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unitAttributeVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}