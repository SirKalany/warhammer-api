package com.whencyclopedia.dto.building;

import com.whencyclopedia.domain.enums.BuildingCategory;

public record BuildingSummaryDTO(
        Long id,
        Long raceId,
        Long buildingChainId,
        String name,
        String slug,
        Short tier,
        BuildingCategory category
) {}