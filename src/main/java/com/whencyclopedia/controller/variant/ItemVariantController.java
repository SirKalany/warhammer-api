package com.whencyclopedia.controller.variant;

import com.whencyclopedia.dto.variant.item.ItemVariantDTO;
import com.whencyclopedia.dto.variant.item.ItemVariantSummaryDTO;
import com.whencyclopedia.service.variant.ItemVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/items")
@RequiredArgsConstructor
public class ItemVariantController {

    private final ItemVariantService itemVariantService;

    @GetMapping
    public ResponseEntity<List<ItemVariantSummaryDTO>> findAll(
            @RequestParam Long versionId,
            @RequestParam(required = false) Long raceId) {
        if (raceId != null) {
            return ResponseEntity.ok(itemVariantService.findByRaceAndVersion(raceId, versionId));
        }
        return ResponseEntity.ok(itemVariantService.findByVersion(versionId));
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<ItemVariantDTO> findByItemAndVersion(
            @PathVariable Long itemId,
            @RequestParam Long versionId) {
        return itemVariantService.findByItemAndVersion(itemId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemVariantDTO> create(@RequestBody ItemVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVariantDTO> update(@PathVariable Long id, @RequestBody ItemVariantDTO dto) {
        return ResponseEntity.ok(itemVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}