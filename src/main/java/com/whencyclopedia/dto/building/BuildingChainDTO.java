package com.whencyclopedia.dto.building;

import com.whencyclopedia.domain.BuildingCategory;

public record BuildingChainDTO(
        Long id,
        Long raceId,
        String name,
        String slug,
        BuildingCategory category,
        String description
) {}