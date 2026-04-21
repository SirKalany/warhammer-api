package com.whencyclopedia.controller;

import com.whencyclopedia.dto.shared.UnitAttributeDTO;
import com.whencyclopedia.service.UnitAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unit-attributes")
@RequiredArgsConstructor
public class UnitAttributeController {

    private final UnitAttributeService unitAttributeService;

    @GetMapping
    public ResponseEntity<List<UnitAttributeDTO>> findAll() {
        return ResponseEntity.ok(unitAttributeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitAttributeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(unitAttributeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UnitAttributeDTO> create(@RequestBody UnitAttributeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(unitAttributeService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnitAttributeDTO> update(@PathVariable Long id, @RequestBody UnitAttributeDTO dto) {
        return ResponseEntity.ok(unitAttributeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unitAttributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}