package com.whencyclopedia.dto.variant.item;

import com.whencyclopedia.domain.enums.ItemCategory;
import com.whencyclopedia.domain.enums.ItemRarity;

public record ItemVariantSummaryDTO(
        Long id,
        Long itemId,
        String name,
        String slug,
        ItemCategory category,
        ItemRarity rarity,
        Long gameVersionId,
        Long raceId
) {}