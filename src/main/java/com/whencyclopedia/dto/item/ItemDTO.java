package com.whencyclopedia.dto.item;

import java.util.List;

import com.whencyclopedia.domain.enums.ItemCategory;
import com.whencyclopedia.domain.enums.ItemRarity;

public record ItemDTO(
        Long id,
        String name,
        String slug,
        String picture,
        ItemCategory category,
        ItemRarity rarity,
        String effect,
        Long raceId,
        List<Long> abilityIds,
        List<Long> spellIds
) {}