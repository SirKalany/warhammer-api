package com.whencyclopedia.dto.spell;

import java.math.BigDecimal;

import com.whencyclopedia.domain.enums.AbilityType;

public record SpellDTO(
        Long id,
        Long loreId,
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
        BigDecimal movementSpeed,
        Integer cost,
        BigDecimal miscastChance,
        String overcastEffect,
        String overcastTarget,
        BigDecimal overcastRange,
        BigDecimal overcastRadius,
        BigDecimal overcastDuration,
        BigDecimal overcastCooldown,
        Integer overcastAffectedUnits,
        Integer overcastBaseDamage,
        Integer overcastExplosiveDamage,
        BigDecimal overcastDamagePerSecond,
        BigDecimal overcastMovementSpeed,
        Integer overcastCost,
        BigDecimal overcastMiscastChance
) {}