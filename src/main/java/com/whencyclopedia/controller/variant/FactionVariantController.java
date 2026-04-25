package com.whencyclopedia.controller.variant;

import com.whencyclopedia.dto.variant.faction.FactionVariantDTO;
import com.whencyclopedia.service.variant.FactionVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/factions")
@RequiredArgsConstructor
public class FactionVariantController {

    private final FactionVariantService factionVariantService;

    @GetMapping
    public ResponseEntity<List<FactionVariantDTO>> findAll(
            @RequestParam Long versionId,
            @RequestParam(required = false) Long raceId) {
        if (raceId != null) {
            return ResponseEntity.ok(factionVariantService.findByRaceAndVersion(raceId, versionId));
        }
        return ResponseEntity.ok(factionVariantService.findByVersion(versionId));
    }

    @GetMapping("/faction/{factionId}")
    public ResponseEntity<FactionVariantDTO> findByFactionAndVersion(
            @PathVariable Long factionId,
            @RequestParam Long versionId) {
        return factionVariantService.findByFactionAndVersion(factionId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FactionVariantDTO> create(@RequestBody FactionVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(factionVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionVariantDTO> update(@PathVariable Long id, @RequestBody FactionVariantDTO dto) {
        return ResponseEntity.ok(factionVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        factionVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}