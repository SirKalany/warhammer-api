package com.whencyclopedia.controller;

import com.whencyclopedia.domain.ItemCategory;
import com.whencyclopedia.dto.item.ItemDTO;
import com.whencyclopedia.dto.item.ItemSummaryDTO;
import com.whencyclopedia.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemSummaryDTO>> findAll(
            @RequestParam(required = false) ItemCategory category,
            @RequestParam(required = false) Long raceId) {
        if (category != null) {
            return ResponseEntity.ok(itemService.findByCategory(category));
        }
        if (raceId != null) {
            return ResponseEntity.ok(itemService.findByRace(raceId));
        }
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ItemDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(itemService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<ItemDTO> create(@RequestBody ItemDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> update(@PathVariable Long id, @RequestBody ItemDTO dto) {
        return ResponseEntity.ok(itemService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}