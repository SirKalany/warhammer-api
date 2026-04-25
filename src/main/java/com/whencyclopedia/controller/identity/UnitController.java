package com.whencyclopedia.controller.identity;

import com.whencyclopedia.dto.identity.unit.UnitDTO;
import com.whencyclopedia.dto.identity.unit.UnitSummaryDTO;
import com.whencyclopedia.service.identity.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/units")
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping
    public ResponseEntity<List<UnitSummaryDTO>> findAll(
            @RequestParam(required = false) Long raceId) {
        if (raceId != null) {
            return ResponseEntity.ok(unitService.findByRace(raceId));
        }
        return ResponseEntity.ok(unitService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<UnitDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(unitService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<UnitDTO> create(@RequestBody UnitDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitDTO> update(@PathVariable Long id, @RequestBody UnitDTO dto) {
        return ResponseEntity.ok(unitService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unitService.delete(id);
        return ResponseEntity.noContent().build();
    }
}