package com.whencyclopedia.dto.item;

import com.whencyclopedia.domain.ItemCategory;
import com.whencyclopedia.domain.ItemRarity;
import java.util.List;

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