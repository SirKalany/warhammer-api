package com.whencyclopedia.controller.identity;

import com.whencyclopedia.dto.identity.spell.SpellDTO;
import com.whencyclopedia.dto.identity.spell.SpellSummaryDTO;
import com.whencyclopedia.service.identity.SpellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spells")
@RequiredArgsConstructor
public class SpellController {

    private final SpellService spellService;

    @GetMapping
    public ResponseEntity<List<SpellSummaryDTO>> findAll(
            @RequestParam(required = false) Long loreId) {
        if (loreId != null) {
            return ResponseEntity.ok(spellService.findByLore(loreId));
        }
        return ResponseEntity.ok(spellService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<SpellDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(spellService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<SpellDTO> create(@RequestBody SpellDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(spellService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpellDTO> update(@PathVariable Long id, @RequestBody SpellDTO dto) {
        return ResponseEntity.ok(spellService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        spellService.delete(id);
        return ResponseEntity.noContent().build();
    }
}