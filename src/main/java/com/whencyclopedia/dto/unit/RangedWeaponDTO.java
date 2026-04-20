package com.whencyclopedia.dto.unit;

import java.math.BigDecimal;

public record RangedWeaponDTO(
        Long id,
        String weaponSlot,
        Long imbuementId,
        Integer ammunition,
        Integer range,
        Integer missileBaseDamage,
        Integer missileApDamage,
        Integer missileBonusVsLarge,
        Integer missileBonusVsInfantry,
        Integer explosionDamage,
        Integer explosionApDamage,
        BigDecimal detonationRadius,
        Integer shotsPerVolley,
        Integer projectileNumber,
        String projectileCategory,
        BigDecimal reloadTime,
        BigDecimal totalAccuracy,
        BigDecimal calibrationDistance,
        BigDecimal calibrationArea,
        String penetrationSizeCap,
        Integer maxPenetration
) {}