package com.whencyclopedia.dto.spell;

import com.whencyclopedia.domain.enums.AbilityType;

public record SpellSummaryDTO(
        Long id,
        Long loreId,
        String name,
        String slug,
        AbilityType type
) {}