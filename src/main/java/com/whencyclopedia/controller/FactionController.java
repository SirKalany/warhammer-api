package com.whencyclopedia.controller;

import com.whencyclopedia.dto.faction.FactionDTO;
import com.whencyclopedia.dto.faction.FactionSummaryDTO;
import com.whencyclopedia.service.FactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factions")
@RequiredArgsConstructor
public class FactionController {

    private final FactionService factionService;

    @GetMapping
    public ResponseEntity<List<FactionSummaryDTO>> findAll() {
        return ResponseEntity.ok(factionService.findAll());
    }

    @GetMapping("/race/{raceId}")
    public ResponseEntity<List<FactionSummaryDTO>> findByRace(@PathVariable Long raceId) {
        return ResponseEntity.ok(factionService.findByRace(raceId));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<FactionDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(factionService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<FactionDTO> create(@RequestBody FactionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(factionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> update(@PathVariable Long id, @RequestBody FactionDTO dto) {
        return ResponseEntity.ok(factionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        factionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}