package com.whencyclopedia.dto.identity.spell;

import com.whencyclopedia.domain.enums.AbilityType;

public record SpellSummaryDTO(
        Long id,
        String name,
        String slug,
        AbilityType type,
        Long loreId
) {}