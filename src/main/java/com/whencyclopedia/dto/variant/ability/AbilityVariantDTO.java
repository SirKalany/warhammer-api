package com.whencyclopedia.dto.variant.ability;

import com.whencyclopedia.domain.enums.AbilityType;
import java.math.BigDecimal;

public record AbilityVariantDTO(
        Long id,
        Long abilityId,
        String name,
        String slug,
        AbilityType type,
        Long gameVersionId,
        String description,
        String effect,
        String target,
        BigDecimal range,
        BigDecimal radius,
        BigDecimal duration,
        BigDecimal cooldown,
        Integer affectedUnits,
        Integer uses,
        String conditions,
        Integer baseDamage,
        Integer explosiveDamage,
        BigDecimal damagePerSecond,
        BigDecimal movementSpeed
) {}