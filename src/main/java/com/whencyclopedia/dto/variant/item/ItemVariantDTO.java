package com.whencyclopedia.dto.variant.item;

import com.whencyclopedia.domain.enums.ItemCategory;
import com.whencyclopedia.domain.enums.ItemRarity;
import java.util.List;

public record ItemVariantDTO(
        Long id,
        Long itemId,
        String name,
        String slug,
        ItemCategory category,
        ItemRarity rarity,
        Long gameVersionId,
        String picture,
        String effect,
        Long raceId,
        List<Long> abilityVariantIds,
        List<Long> spellVariantIds
) {}