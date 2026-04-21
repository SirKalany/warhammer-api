package com.whencyclopedia.controller;

import com.whencyclopedia.dto.race.RaceDTO;
import com.whencyclopedia.dto.race.RaceSummaryDTO;
import com.whencyclopedia.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/races")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @GetMapping
    public ResponseEntity<List<RaceSummaryDTO>> findAll() {
        return ResponseEntity.ok(raceService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<RaceDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(raceService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<RaceDTO> create(@RequestBody RaceDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(raceService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RaceDTO> update(@PathVariable Long id, @RequestBody RaceDTO dto) {
        return ResponseEntity.ok(raceService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        raceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}