package com.whencyclopedia.controller.variant;

import com.whencyclopedia.dto.identity.race.RaceSummaryDTO;
import com.whencyclopedia.service.variant.RaceVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/races")
@RequiredArgsConstructor
public class RaceVariantController {

    private final RaceVariantService raceVariantService;

    @GetMapping
    public ResponseEntity<List<RaceSummaryDTO>> findByVersion(@RequestParam Long versionId) {
        return ResponseEntity.ok(raceVariantService.findByVersion(versionId));
    }

    @PostMapping("/{raceId}/versions/{versionId}")
    public ResponseEntity<Void> addToVersion(
            @PathVariable Long raceId,
            @PathVariable Long versionId) {
        raceVariantService.addToVersion(raceId, versionId);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{raceId}/versions/{versionId}")
    public ResponseEntity<Void> removeFromVersion(
            @PathVariable Long raceId,
            @PathVariable Long versionId) {
        raceVariantService.removeFromVersion(raceId, versionId);
        return ResponseEntity.noContent().build();
    }
}