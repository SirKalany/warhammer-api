package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.LoreOfMagic;
import com.whencyclopedia.dto.identity.lore.LoreOfMagicDTO;
import com.whencyclopedia.dto.identity.lore.LoreOfMagicSummaryDTO;
import com.whencyclopedia.repository.identity.LoreOfMagicRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoreOfMagicService {

    private final LoreOfMagicRepository loreOfMagicRepository;

    public List<LoreOfMagicSummaryDTO> findAll() {
        return loreOfMagicRepository.findAll()
                .stream()
                .map(l -> toSummaryDTO(l))
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
                .build();
        return toDTO(loreOfMagicRepository.save(lore));
    }

    public LoreOfMagicDTO update(Long id, LoreOfMagicDTO dto) {
        LoreOfMagic lore = loreOfMagicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lore not found: " + id));
        lore.setName(dto.name());
        lore.setSlug(dto.slug());
        return toDTO(loreOfMagicRepository.save(lore));
    }

    public void delete(Long id) {
        loreOfMagicRepository.deleteById(id);
    }

    private LoreOfMagicDTO toDTO(@NonNull LoreOfMagic l) {
        return new LoreOfMagicDTO(l.getId(), l.getName(), l.getSlug());
    }

    private LoreOfMagicSummaryDTO toSummaryDTO(@NonNull LoreOfMagic l) {
        return new LoreOfMagicSummaryDTO(l.getId(), l.getName(), l.getSlug());
    }
}