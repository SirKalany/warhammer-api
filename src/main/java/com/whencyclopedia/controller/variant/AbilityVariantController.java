package com.whencyclopedia.controller.variant;

import com.whencyclopedia.domain.enums.AbilityType;
import com.whencyclopedia.dto.variant.ability.AbilityVariantDTO;
import com.whencyclopedia.dto.variant.ability.AbilityVariantSummaryDTO;
import com.whencyclopedia.service.variant.AbilityVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/abilities")
@RequiredArgsConstructor
public class AbilityVariantController {

    private final AbilityVariantService abilityVariantService;

    @GetMapping
    public ResponseEntity<List<AbilityVariantSummaryDTO>> findAll(
            @RequestParam Long versionId,
            @RequestParam(required = false) AbilityType type) {
        if (type != null) {
            return ResponseEntity.ok(abilityVariantService.findByTypeAndVersion(type, versionId));
        }
        return ResponseEntity.ok(abilityVariantService.findByVersion(versionId));
    }

    @GetMapping("/ability/{abilityId}")
    public ResponseEntity<AbilityVariantDTO> findByAbilityAndVersion(
            @PathVariable Long abilityId,
            @RequestParam Long versionId) {
        return abilityVariantService.findByAbilityAndVersion(abilityId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AbilityVariantDTO> create(@RequestBody AbilityVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(abilityVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbilityVariantDTO> update(@PathVariable Long id, @RequestBody AbilityVariantDTO dto) {
        return ResponseEntity.ok(abilityVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        abilityVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}