package com.whencyclopedia.dto.identity.building;

import com.whencyclopedia.domain.enums.BuildingCategory;

public record BuildingDTO(
        Long id,
        String name,
        String slug,
        Long raceId,
        Long buildingChainId,
        BuildingCategory category
) {}