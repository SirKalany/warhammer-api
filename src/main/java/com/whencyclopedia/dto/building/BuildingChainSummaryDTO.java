package com.whencyclopedia.dto.building;

import com.whencyclopedia.domain.enums.BuildingCategory;

public record BuildingChainSummaryDTO(
        Long id,
        Long raceId,
        String name,
        String slug,
        BuildingCategory category
) {}