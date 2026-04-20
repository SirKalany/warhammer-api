package com.whencyclopedia.dto.ability;

import com.whencyclopedia.domain.AbilityType;

public record AbilitySummaryDTO(
        Long id,
        String name,
        String slug,
        AbilityType type
) {}