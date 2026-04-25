package com.whencyclopedia.dto.variant.spell;

import com.whencyclopedia.domain.enums.AbilityType;

public record SpellVariantSummaryDTO(
        Long id,
        Long spellId,
        String name,
        String slug,
        AbilityType type,
        Long loreId,
        Long gameVersionId
) {}