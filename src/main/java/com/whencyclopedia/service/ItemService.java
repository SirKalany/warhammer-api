package com.whencyclopedia.service;

import com.whencyclopedia.domain.*;
import com.whencyclopedia.dto.item.ItemDTO;
import com.whencyclopedia.dto.item.ItemSummaryDTO;
import com.whencyclopedia.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final RaceRepository raceRepository;
    private final AbilityRepository abilityRepository;
    private final SpellRepository spellRepository;

    public List<ItemSummaryDTO> findAll() {
        return itemRepository.findAll()
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<ItemSummaryDTO> findByCategory(ItemCategory category) {
        return itemRepository.findByCategory(category)
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public List<ItemSummaryDTO> findByRace(Long raceId) {
        return itemRepository.findByRaceId(raceId)
                .stream()
                .map(this::toSummaryDTO)
                .toList();
    }

    public ItemDTO findBySlug(String slug) {
        Item item = itemRepository.findBySlug(slug)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + slug));
        return toDTO(item);
    }

    public ItemDTO create(ItemDTO dto) {
        Race race = dto.raceId() != null
                ? raceRepository.findById(dto.raceId())
                        .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()))
                : null;
        List<Ability> abilities = abilityRepository.findAllById(dto.abilityIds());
        List<Spell> spells = spellRepository.findAllById(dto.spellIds());
        Item item = Item.builder()
                .name(dto.name())
                .slug(dto.slug())
                .picture(dto.picture())
                .category(dto.category())
                .rarity(dto.rarity())
                .effect(dto.effect())
                .race(race)
                .abilities(abilities)
                .spells(spells)
                .build();
        return toDTO(itemRepository.save(item));
    }

    public ItemDTO update(Long id, ItemDTO dto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + id));
        Race race = dto.raceId() != null
                ? raceRepository.findById(dto.raceId())
                        .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()))
                : null;
        List<Ability> abilities = abilityRepository.findAllById(dto.abilityIds());
        List<Spell> spells = spellRepository.findAllById(dto.spellIds());
        item.setName(dto.name());
        item.setSlug(dto.slug());
        item.setPicture(dto.picture());
        item.setCategory(dto.category());
        item.setRarity(dto.rarity());
        item.setEffect(dto.effect());
        item.setRace(race);
        item.setAbilities(abilities);
        item.setSpells(spells);
        return toDTO(itemRepository.save(item));
    }

    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    private ItemDTO toDTO(Item i) {
        return new ItemDTO(
                i.getId(), i.getName(), i.getSlug(), i.getPicture(),
                i.getCategory(), i.getRarity(), i.getEffect(),
                i.getRace() != null ? i.getRace().getId() : null,
                i.getAbilities().stream().map(Ability::getId).toList(),
                i.getSpells().stream().map(Spell::getId).toList()
        );
    }

    private ItemSummaryDTO toSummaryDTO(Item i) {
        return new ItemSummaryDTO(
                i.getId(), i.getName(), i.getSlug(),
                i.getCategory(), i.getRarity(),
                i.getRace() != null ? i.getRace().getId() : null
        );
    }
}