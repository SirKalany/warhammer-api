package com.whencyclopedia.controller.variant;

import com.whencyclopedia.dto.variant.spell.SpellVariantDTO;
import com.whencyclopedia.dto.variant.spell.SpellVariantSummaryDTO;
import com.whencyclopedia.service.variant.SpellVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/spells")
@RequiredArgsConstructor
public class SpellVariantController {

    private final SpellVariantService spellVariantService;

    @GetMapping
    public ResponseEntity<List<SpellVariantSummaryDTO>> findAll(
            @RequestParam Long versionId,
            @RequestParam(required = false) Long loreId) {
        if (loreId != null) {
            return ResponseEntity.ok(spellVariantService.findByLoreAndVersion(loreId, versionId));
        }
        return ResponseEntity.ok(spellVariantService.findByVersion(versionId));
    }

    @GetMapping("/spell/{spellId}")
    public ResponseEntity<SpellVariantDTO> findBySpellAndVersion(
            @PathVariable Long spellId,
            @RequestParam Long versionId) {
        return spellVariantService.findBySpellAndVersion(spellId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SpellVariantDTO> create(@RequestBody SpellVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spellVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellVariantDTO> update(@PathVariable Long id, @RequestBody SpellVariantDTO dto) {
        return ResponseEntity.ok(spellVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        spellVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}