package com.whencyclopedia.controller.identity;

import com.whencyclopedia.dto.identity.shared.ImbuementDTO;
import com.whencyclopedia.service.identity.ImbuementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imbuements")
@RequiredArgsConstructor
public class ImbuementController {

    private final ImbuementService imbuementService;

    @GetMapping
    public ResponseEntity<List<ImbuementDTO>> findAll() {
        return ResponseEntity.ok(imbuementService.findAll());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<ImbuementDTO> findBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(imbuementService.findBySlug(slug));
    }

    @PostMapping
    public ResponseEntity<ImbuementDTO> create(@RequestBody ImbuementDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imbuementService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImbuementDTO> update(@PathVariable Long id, @RequestBody ImbuementDTO dto) {
        return ResponseEntity.ok(imbuementService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imbuementService.delete(id);
        return ResponseEntity.noContent().build();
    }
}