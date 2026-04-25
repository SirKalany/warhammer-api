package com.whencyclopedia.dto.identity.ability;

import com.whencyclopedia.domain.enums.AbilityType;

public record AbilityDTO(
        Long id,
        String name,
        String slug,
        AbilityType type
) {}