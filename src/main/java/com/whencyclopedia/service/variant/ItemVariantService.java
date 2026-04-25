package com.whencyclopedia.service.variant;

import com.whencyclopedia.domain.identity.GameVersion;
import com.whencyclopedia.domain.identity.Item;
import com.whencyclopedia.domain.identity.Race;
import com.whencyclopedia.domain.variant.AbilityVariant;
import com.whencyclopedia.domain.variant.ItemVariant;
import com.whencyclopedia.domain.variant.SpellVariant;
import com.whencyclopedia.dto.variant.item.ItemVariantDTO;
import com.whencyclopedia.dto.variant.item.ItemVariantSummaryDTO;
import com.whencyclopedia.repository.identity.GameVersionRepository;
import com.whencyclopedia.repository.identity.ItemRepository;
import com.whencyclopedia.repository.identity.RaceRepository;
import com.whencyclopedia.repository.variant.AbilityVariantRepository;
import com.whencyclopedia.repository.variant.ItemVariantRepository;
import com.whencyclopedia.repository.variant.SpellVariantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemVariantService {

    private final ItemVariantRepository itemVariantRepository;
    private final ItemRepository itemRepository;
    private final GameVersionRepository gameVersionRepository;
    private final RaceRepository raceRepository;
    private final AbilityVariantRepository abilityVariantRepository;
    private final SpellVariantRepository spellVariantRepository;

    public List<ItemVariantSummaryDTO> findByVersion(Long versionId) {
        return itemVariantRepository.findByGameVersionId(versionId)
                .stream()
                .map(iv -> toSummaryDTO(iv))
                .toList();
    }

    public List<ItemVariantSummaryDTO> findByRaceAndVersion(Long raceId, Long versionId) {
        return itemVariantRepository.findByRaceIdAndGameVersionId(raceId, versionId)
                .stream()
                .map(iv -> toSummaryDTO(iv))
                .toList();
    }

    public Optional<ItemVariantDTO> findByItemAndVersion(Long itemId, Long versionId) {
        return itemVariantRepository.findByItemIdAndGameVersionId(itemId, versionId)
                .map(iv -> toDTO(iv));
    }

    public ItemVariantDTO create(ItemVariantDTO dto) {
        Item item = itemRepository.findById(dto.itemId())
                .orElseThrow(() -> new EntityNotFoundException("Item not found: " + dto.itemId()));
        GameVersion version = gameVersionRepository.findById(dto.gameVersionId())
                .orElseThrow(() -> new EntityNotFoundException("Version not found: " + dto.gameVersionId()));
        Race race = dto.raceId() != null
                ? raceRepository.findById(dto.raceId())
                        .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()))
                : null;
        List<AbilityVariant> abilities = abilityVariantRepository.findAllById(dto.abilityVariantIds());
        List<SpellVariant> spells = spellVariantRepository.findAllById(dto.spellVariantIds());
        ItemVariant variant = ItemVariant.builder()
                .item(item)
                .gameVersion(version)
                .picture(dto.picture())
                .effect(dto.effect())
                .race(race)
                .abilities(abilities)
                .spells(spells)
                .build();
        return toDTO(itemVariantRepository.save(variant));
    }

    public ItemVariantDTO update(Long id, ItemVariantDTO dto) {
        ItemVariant variant = itemVariantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item variant not found: " + id));
        Race race = dto.raceId() != null
                ? raceRepository.findById(dto.raceId())
                        .orElseThrow(() -> new EntityNotFoundException("Race not found: " + dto.raceId()))
                : null;
        List<AbilityVariant> abilities = abilityVariantRepository.findAllById(dto.abilityVariantIds());
        List<SpellVariant> spells = spellVariantRepository.findAllById(dto.spellVariantIds());
        variant.setPicture(dto.picture());
        variant.setEffect(dto.effect());
        variant.setRace(race);
        variant.setAbilities(abilities);
        variant.setSpells(spells);
        return toDTO(itemVariantRepository.save(variant));
    }

    public void delete(Long id) {
        itemVariantRepository.deleteById(id);
    }

    private ItemVariantDTO toDTO(@NonNull ItemVariant iv) {
        return new ItemVariantDTO(
                iv.getId(), iv.getItem().getId(), iv.getItem().getName(),
                iv.getItem().getSlug(), iv.getItem().getCategory(), iv.getItem().getRarity(),
                iv.getGameVersion().getId(), iv.getPicture(), iv.getEffect(),
                iv.getRace() != null ? iv.getRace().getId() : null,
                iv.getAbilities().stream().map(AbilityVariant::getId).toList(),
                iv.getSpells().stream().map(SpellVariant::getId).toList()
        );
    }

    private ItemVariantSummaryDTO toSummaryDTO(@NonNull ItemVariant iv) {
        return new ItemVariantSummaryDTO(
                iv.getId(), iv.getItem().getId(), iv.getItem().getName(),
                iv.getItem().getSlug(), iv.getItem().getCategory(), iv.getItem().getRarity(),
                iv.getGameVersion().getId(),
                iv.getRace() != null ? iv.getRace().getId() : null
        );
    }
}