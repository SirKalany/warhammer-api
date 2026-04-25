package com.whencyclopedia.dto.variant.ability;

import com.whencyclopedia.domain.enums.AbilityType;

public record AbilityVariantSummaryDTO(
        Long id,
        Long abilityId,
        String name,
        String slug,
        AbilityType type,
        Long gameVersionId
) {}