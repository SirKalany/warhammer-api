package com.whencyclopedia.controller.variant;

import com.whencyclopedia.dto.variant.shared.ImbuementVariantDTO;
import com.whencyclopedia.service.variant.ImbuementVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/variants/imbuements")
@RequiredArgsConstructor
public class ImbuementVariantController {

    private final ImbuementVariantService imbuementVariantService;

    @GetMapping
    public ResponseEntity<List<ImbuementVariantDTO>> findByVersion(@RequestParam Long versionId) {
        return ResponseEntity.ok(imbuementVariantService.findByVersion(versionId));
    }

    @GetMapping("/imbuement/{imbuementId}")
    public ResponseEntity<ImbuementVariantDTO> findByImbuementAndVersion(
            @PathVariable Long imbuementId,
            @RequestParam Long versionId) {
        return imbuementVariantService.findByImbuementAndVersion(imbuementId, versionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ImbuementVariantDTO> create(@RequestBody ImbuementVariantDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imbuementVariantService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImbuementVariantDTO> update(@PathVariable Long id, @RequestBody ImbuementVariantDTO dto) {
        return ResponseEntity.ok(imbuementVariantService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        imbuementVariantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}