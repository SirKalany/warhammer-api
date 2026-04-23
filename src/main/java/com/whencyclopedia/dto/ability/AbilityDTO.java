package com.whencyclopedia.dto.ability;

import java.math.BigDecimal;

import com.whencyclopedia.domain.enums.AbilityType;

public record AbilityDTO(
        Long id,
        String name,
        String slug,
        AbilityType type,
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