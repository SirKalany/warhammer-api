package com.whencyclopedia.dto.variant.faction;

import com.whencyclopedia.domain.enums.ClimateStatus;

public record FactionVariantDTO(
        Long id,
        Long factionId,
        Long gameVersionId,
        String banner,
        String leader,
        String factionEffect,
        Boolean isHorde,
        ClimateStatus climateChaoticWasteland,
        ClimateStatus climateFrozen,
        ClimateStatus climateMountain,
        ClimateStatus climateTemperate,
        ClimateStatus climateTemperateIsland,
        ClimateStatus climateMagicalForest,
        ClimateStatus climateJungle,
        ClimateStatus climateSavannah,
        ClimateStatus climateDesert,
        ClimateStatus climateWasteland,
        ClimateStatus climateOcean
) {}