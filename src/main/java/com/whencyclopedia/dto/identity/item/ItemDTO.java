package com.whencyclopedia.dto.identity.item;

import com.whencyclopedia.domain.enums.ItemCategory;
import com.whencyclopedia.domain.enums.ItemRarity;

public record ItemDTO(
        Long id,
        String name,
        String slug,
        ItemCategory category,
        ItemRarity rarity
) {}