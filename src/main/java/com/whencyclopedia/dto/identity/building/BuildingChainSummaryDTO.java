package com.whencyclopedia.dto.identity.building;

import com.whencyclopedia.domain.enums.BuildingCategory;

public record BuildingChainSummaryDTO(
        Long id,
        String name,
        String slug,
        Long raceId,
        BuildingCategory category
) {}