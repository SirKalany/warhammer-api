package com.whencyclopedia.dto.identity.ability;

import com.whencyclopedia.domain.enums.AbilityType;

public record AbilitySummaryDTO(
        Long id,
        String name,
        String slug,
        AbilityType type
) {}