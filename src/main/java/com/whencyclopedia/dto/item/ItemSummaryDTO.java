package com.whencyclopedia.dto.item;

import com.whencyclopedia.domain.ItemCategory;
import com.whencyclopedia.domain.ItemRarity;

public record ItemSummaryDTO(
        Long id,
        String name,
        String slug,
        ItemCategory category,
        ItemRarity rarity,
        Long raceId
) {}