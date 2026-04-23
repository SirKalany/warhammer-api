package com.whencyclopedia.service;

import com.whencyclopedia.domain.identity.LoreOfMagic;
import com.whencyclopedia.dto.lore.LoreOfMagicDTO;
import com.whencyclopedia.dto.lore.LoreOfMagicSummaryDTO;
import com.whencyclopedia.repository.identity.LoreOfMagicRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoreOfMagicService {

    private final LoreOfMagicRepository loreOfMagicRepository;

    public List<LoreOfMagicSummaryDTO> findAll() {
        return loreOfMagicRepository.findAll()
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public LoreOfMagicDTO findBySlug(String slug) {
        LoreOfMagic lore = loreOfMagicRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Lore not found: " + slug));
        return toDTO(lore);
    }

    public LoreOfMagicDTO create(LoreOfMagicDTO dto) {
        LoreOfMagic lore = LoreOfMagic.builder()
                .name(dto.name())
                .slug(dto.slug())
                .description(dto.description())
                .build();
        return toDTO(loreOfMagicRepository.save(lore));
    }

    public LoreOfMagicDTO update(Long id, LoreOfMagicDTO dto) {
        LoreOfMagic lore = loreOfMagicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lore not found: " + id));
        lore.setName(dto.name());
        lore.setSlug(dto.slug());
        lore.setDescription(dto.description());
        return toDTO(loreOfMagicRepository.save(lore));
    }

    public void delete(Long id) {
        loreOfMagicRepository.deleteById(id);
    }

    private LoreOfMagicDTO toDTO(LoreOfMagic l) {
        return new LoreOfMagicDTO(l.getId(), l.getName(), l.getSlug(), l.getDescription());
    }

    private LoreOfMagicSummaryDTO toSummaryDTO(LoreOfMagic l) {
        return new LoreOfMagicSummaryDTO(l.getId(), l.getName(), l.getSlug());
    }
}