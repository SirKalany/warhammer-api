package com.whencyclopedia.controller.identity;

import com.whencyclopedia.dto.identity.lore.LoreOfMagicDTO;
import com.whencyclopedia.dto.identity.lore.LoreOfMagicSummaryDTO;
import com.whencyclopedia.service.identity.LoreOfMagicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lores")
@RequiredArgsConstructor
public class LoreOfMagicController {

    private final LoreOfMagicService loreOfMagicService;

    @GetMapping
    public ResponseEntity<List<LoreOfMagicSummaryDTO>> findAll() {
        return ResponseEntity.ok(loreOfMagicService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<LoreOfMagicDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(loreOfMagicService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<LoreOfMagicDTO> create(@RequestBody LoreOfMagicDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(loreOfMagicService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoreOfMagicDTO> update(@PathVariable Long id, @RequestBody LoreOfMagicDTO dto) {
        return ResponseEntity.ok(loreOfMagicService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        loreOfMagicService.delete(id);
        return ResponseEntity.noContent().build();
    }
}