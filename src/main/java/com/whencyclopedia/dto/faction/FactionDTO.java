package com.whencyclopedia.dto.faction;

import com.whencyclopedia.domain.ClimateStatus;

public record FactionDTO(
        Long id,
        Long raceId,
        String name,
        String slug,
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