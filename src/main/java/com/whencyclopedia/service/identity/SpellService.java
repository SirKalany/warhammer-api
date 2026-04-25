package com.whencyclopedia.service.identity;

import com.whencyclopedia.domain.identity.LoreOfMagic;
import com.whencyclopedia.domain.identity.Spell;
import com.whencyclopedia.dto.identity.spell.SpellDTO;
import com.whencyclopedia.dto.identity.spell.SpellSummaryDTO;
import com.whencyclopedia.repository.identity.LoreOfMagicRepository;
import com.whencyclopedia.repository.identity.SpellRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpellService {

    private final SpellRepository spellRepository;
    private final LoreOfMagicRepository loreOfMagicRepository;

    public List<SpellSummaryDTO> findAll() {
        return spellRepository.findAll()
                .stream()
                .map(s -> toSummaryDTO(s))
                .toList();
    }

    public List<SpellSummaryDTO> findByLore(Long loreId) {
        return spellRepository.findByLoreId(loreId)
                .stream()
                .map(s -> toSummaryDTO(s))
                .toList();
    }

    public SpellDTO findBySlug(String slug) {
        Spell spell = spellRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Spell not found: " + slug));
        return toDTO(spell);
    }

    public SpellDTO create(SpellDTO dto) {
        LoreOfMagic lore = loreOfMagicRepository.findById(dto.loreId())
                .orElseThrow(() -> new EntityNotFoundException("Lore not found: " + dto.loreId()));
        Spell spell = Spell.builder()
                .name(dto.name())
                .slug(dto.slug())
                .type(dto.type())
                .lore(lore)
                .build();
        return toDTO(spellRepository.save(spell));
    }

    public SpellDTO update(Long id, SpellDTO dto) {
        Spell spell = spellRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Spell not found: " + id));
        LoreOfMagic lore = loreOfMagicRepository.findById(dto.loreId())
                .orElseThrow(() -> new EntityNotFoundException("Lore not found: " + dto.loreId()));
        spell.setName(dto.name());
        spell.setSlug(dto.slug());
        spell.setType(dto.type());
        spell.setLore(lore);
        return toDTO(spellRepository.save(spell));
    }

    public void delete(Long id) {
        spellRepository.deleteById(id);
    }

    private SpellDTO toDTO(@NonNull Spell s) {
        return new SpellDTO(s.getId(), s.getName(), s.getSlug(), s.getType(), s.getLore().getId());
    }

    private SpellSummaryDTO toSummaryDTO(@NonNull Spell s) {
        return new SpellSummaryDTO(s.getId(), s.getName(), s.getSlug(), s.getType(), s.getLore().getId());
    }
}