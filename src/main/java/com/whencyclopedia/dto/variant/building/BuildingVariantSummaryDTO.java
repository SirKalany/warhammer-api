package com.whencyclopedia.dto.variant.building;

import com.whencyclopedia.domain.enums.BuildingCategory;

public record BuildingVariantSummaryDTO(
        Long id,
        Long buildingId,
        String name,
        String slug,
        BuildingCategory category,
        Long raceId,
        Long buildingChainId,
        Long gameVersionId,
        Short tier
) {}