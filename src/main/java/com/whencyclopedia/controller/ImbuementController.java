package com.whencyclopedia.controller;

import com.whencyclopedia.dto.shared.ImbuementDTO;
import com.whencyclopedia.service.ImbuementService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ImbuementDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(imbuementService.findById(id));
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