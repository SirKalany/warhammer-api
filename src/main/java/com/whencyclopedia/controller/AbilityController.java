package com.whencyclopedia.controller;

import com.whencyclopedia.domain.enums.AbilityType;
import com.whencyclopedia.dto.ability.AbilityDTO;
import com.whencyclopedia.dto.ability.AbilitySummaryDTO;
import com.whencyclopedia.service.AbilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/abilities")
@RequiredArgsConstructor
public class AbilityController {

    private final AbilityService abilityService;

    @GetMapping
    public ResponseEntity<List<AbilitySummaryDTO>> findAll(
            @RequestParam(required = false) AbilityType type) {
        if (type != null) {
            return ResponseEntity.ok(abilityService.findByType(type));
        }
        return ResponseEntity.ok(abilityService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<AbilityDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(abilityService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<AbilityDTO> create(@RequestBody AbilityDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(abilityService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbilityDTO> update(@PathVariable Long id, @RequestBody AbilityDTO dto) {
        return ResponseEntity.ok(abilityService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        abilityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}